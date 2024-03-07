package com.gate.client.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Order {
	/**
	 * 否 订单自定义信息，用户可以用该字段设置自定义 ID，用户自定义字段必须满足以下条件：
	 */
	private String text;
	/**
	 * 交易货币对
	 */
	private String currency_pair;
	/**
	 * 订单类型 限价单 limit 市价单 market
	 */
	private String type;

	/**
	 * 买单或者卖单 buy ,sell
	 */
	private String side;

	/**
	 * 否 账户类型，spot - 现货账户，margin - 杠杆账户，cross_margin - 全仓杠杆账户，unified - 统一账户
	 */
	private String account;
	/**
	 * 交易数量 type为limit时，指交易货币，即需要交易的货币，如BTC_USDT中指BTC。 type为market时，根据买卖不同指代不同
	 */
	private String amount;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * gtc: GoodTillCancelled ioc: ImmediateOrCancelled，立即成交或者取消，只吃单不挂单 poc:
	 * PendingOrCancelled，被动委托，只挂单不吃单 fok: FillOrKill，全部成交或者全部取消
	 */
	private String time_in_force;

}
