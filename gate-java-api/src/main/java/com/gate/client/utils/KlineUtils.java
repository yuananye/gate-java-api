package com.gate.client.utils;

import com.lzt.vtrading.lib.exception.ExchangeErrorCode;
import com.lzt.vtrading.lib.exception.StrategyException;
import com.lzt.vtrading.lib.exchange.model.param.KlineParam;

public class KlineUtils {

	public static String getKlineType(String type) {
		switch (type) {
		case KlineParam.Type_1_Min:
			return "1m";
		case KlineParam.Type_5_Min:
			return "5m";
		case KlineParam.Type_15_Min:
			return "15m";
		case KlineParam.Type_30_Min:
			return "30m";
		case KlineParam.Type_1_Hour:
			return "1h";
		case KlineParam.Type_4_Hour:
			return "4h";
		case KlineParam.Type_1_Day:
			return "1d";
		default:
			throw new StrategyException(ExchangeErrorCode.Param_Invalid);
		}
	}

}
