package com.gate.client.utils;


import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exception.ExchangeErrorCode;
import com.lzt.vtrading.lib.exception.StrategyException;
import com.lzt.vtrading.lib.exchange.model.TradeOrder;
import com.lzt.vtrading.lib.exchange.spot.Exchange;

public class OrderUtils {
	
	public static TradeOrder analysisOrder(JSONObject dataJson,String counterCoin,String baseCoin) {

		if (dataJson == null) {
			throw new StrategyException(ExchangeErrorCode.Order_Query_Failed);
		}
		
		TradeOrder order = new TradeOrder();
		order.setId(dataJson.getString("id"));

		String type = dataJson.getString("side");

		// 订单类型：买入或卖出
		order.setTradeType(getTradeType(type));

		// 挂单时间
		order.setOrderTime(dataJson.getLongValue("create_time_ms"));

		// 挂单数量
		order.setAmount(dataJson.getString("amount"));

		// 成交数量
		order.setDealAmount(dataJson.getString("filled_amount"));

		// 挂单价格
		order.setPrice(dataJson.getString("price"));

		// 交易币
		order.setCointerCoin(counterCoin);

		// 本币
		order.setBaseCoin(baseCoin);

		// 成交均价
		order.setDealPrice(dataJson.getString("avg_deal_price"));


		order.setTradeMoney(dataJson.getString("filled_total"));

		String status = dataJson.getString("status");

		//
		int states = getStatus(status);
		order.setStatus(states);

		return order;

	}

	/**
	 * 获取状态
	 */
	public static int getStatus(String status) {
		switch (status) {
		// 未成交
		case "open":
			return Exchange.Order_Status_Unfinished;
		// 已取消
		case "cancelled":
			return Exchange.Order_Status_Cancel;
		// 已成交
		case "closed":
			return Exchange.Order_Status_Finished;
		default:
			return Exchange.Order_Status_Cancel;
		}
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private static String getTradeType(String type) {
		if (type.contains("buy")) {
			return Exchange.Trade_Type_Buy;
		}

		return Exchange.Trade_Type_Sell;
	}

}
