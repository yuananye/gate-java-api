package com.gate.fclient.utils;

import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.TradeConstants;
import com.lzt.vtrading.lib.exchange.model.Contract;
import com.lzt.vtrading.lib.exchange.model.TradeOrder;
import com.lzt.vtrading.lib.exchange.spot.Exchange;

public class OrderUtils {

	public static BigDecimal getFrozenAmount(JSONArray orderList, String posSide) {

		BigDecimal frozenAmount = BigDecimal.ZERO;

		if (CollectionUtils.isEmpty(orderList)) {
			return frozenAmount;
		}

		// is_reduce_only true 表示平仓委托
		for (int index = 0; index < orderList.size(); index++) {
			JSONObject orderJson = orderList.getJSONObject(index);

			BigDecimal size = orderJson.getBigDecimal("size");
			// 平仓委托
			if (!orderJson.getBooleanValue("is_reduce_only")) {
				continue;
			}

			String side = size.compareTo(BigDecimal.ZERO) > 0 ? "dual_short" : "dual_long";

			if (!posSide.equals(side)) {
				continue;
			}

			frozenAmount = frozenAmount.add(size.abs());
		}

		return frozenAmount;

	}

	/**
	 * 解析订单
	 * 
	 * @param orderJson
	 */
	public static TradeOrder analysisOrder(JSONObject orderJson, Contract contract) {
		TradeOrder resultOrder = new TradeOrder();

		resultOrder.setContract(true);
		resultOrder.setContractId(contract.getContractId());

		BigDecimal amount = orderJson.getBigDecimal("size").abs();
		// 挂单数量
		resultOrder.setAmount(amount.toPlainString());
		// 挂单价格
		resultOrder.setPrice(orderJson.getString("price"));
		// 价格为0并且tif为ioc，代表市价委托。

		String price = orderJson.getString("price");
		String tif = orderJson.getString("tif");

		if (price.equals(0) && tif.equals("ioc")) {
			// 订单价格类型
			resultOrder.setMatchType(TradeConstants.Trade_MatchType_Opponent);
		} else {
			// 订单价格类型
			resultOrder.setMatchType(TradeConstants.Trade_MatchType_Limit);
		}

		// 交易数量，正数为买入，负数为卖出。平仓委托则设置为0。
		Integer size = orderJson.getInteger("size");

		resultOrder.setTradeType(size > 0 ? TradeConstants.Trade_Type_Buy : TradeConstants.Trade_Type_Sell);

		// isReduceOnly 如果是true就是平仓，否则的话就是开仓
		boolean isReduceOnly = orderJson.getBooleanValue("is_reduce_only");

		if (!isReduceOnly) {
			resultOrder.setTradeOffset(TradeConstants.Trade_Offset_Open);
		} else {
			resultOrder.setTradeOffset(TradeConstants.Trade_Offset_Close);
		}

		// 杠杆倍数
		resultOrder.setLeverRate(BigDecimal.ZERO.toPlainString());

		// 订单ID
		resultOrder.setId(orderJson.getString("id"));

		// 挂单时间
		resultOrder.setOrderTime(orderJson.getLongValue("create_time") * 1000L);

		// 成交数量
		BigDecimal oridAmount = orderJson.getBigDecimal("size").abs();
		BigDecimal left = orderJson.getBigDecimal("left");

		BigDecimal dealAmount = oridAmount.subtract(left);

		resultOrder.setDealAmount(dealAmount.toPlainString());

		// 成交额 = 成交均价 * 成交数量 * 合约面值

		BigDecimal fillPrice = orderJson.getBigDecimal("fill_price");
		fillPrice = fillPrice == null ? BigDecimal.ZERO : fillPrice;
		// 合约面值
		BigDecimal piece = contract.getPieceVal();
		piece = piece == null ? BigDecimal.ZERO : piece;

		BigDecimal cumQuote = fillPrice.multiply(dealAmount).multiply(piece);

		resultOrder.setTradeMoney(cumQuote.toPlainString());

		// 成交均价
		resultOrder.setDealPrice(orderJson.getString("fillPrice"));

		// 收益
		resultOrder.setProfit(orderJson.getString("pnl"));
		
		resultOrder.setFee(orderJson.getString("mkfr"));

		// 冻结保证金
		resultOrder.setMarginFrozen("0");

		// 交易币
		resultOrder.setCointerCoin(contract.getCounterCoin());

		// 本币
		resultOrder.setBaseCoin(contract.getBaseCoin());

		// 合约成本价
		resultOrder.setCostPrice(orderJson.getString("fillPrice"));

		// 订单状态
		String state = orderJson.getString("finish_as");
		String status = orderJson.getString("status");

		resultOrder.setStatus(getStatus(state, status));

		return resultOrder;

	}

	/**
	 * 获取对应的状态
	 * 
	 * @param state
	 */
	private static int getStatus(String state, String status) {

		if (StringUtils.equals(status, "open")) {
			return Exchange.Order_Status_Unfinished;
		}

		switch (state) {
		// 未成交
		case "0":
		case "4":
			return Exchange.Order_Status_Unfinished;

		// 已取消
		case "cancelled":
		case "position_closed":
		case "auto_deleveraged":
		case "liquidated":
			return Exchange.Order_Status_Cancel;
		// 已成交
		case "filled":
			return Exchange.Order_Status_Finished;
		// 部分成交&部分未成交
		case "ioc":

			return Exchange.Order_Status_Part_Finished;
		default:
			return Exchange.Order_Status_Cancel;
		}

	}

}
