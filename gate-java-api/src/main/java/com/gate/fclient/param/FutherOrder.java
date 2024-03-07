package com.gate.fclient.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FutherOrder {
	/**
	 * 合约标识
	 */
	private  String contract;
	/**
	 * 必选。交易数量，正数为买入，负数为卖出。平仓委托则设置为0。
	 */
	private Integer size;
	
	private String price;
	/**
	 * 设置为 true 的时候，为只减仓委托
	 */
	private boolean reduce_only;
	/**
	 * Time in force 策略，市价单当前只支持 ioc 模式
	 */
	private String tif;
	
	/**
	 * 订单自定义信息，用户可以用该字段设置自定义 ID，用户自定义字段必须满足以下条件：
	 */
	private String text;
	
	

}
