package com.gate.client.utils;

import org.apache.commons.lang3.StringUtils;

import com.lzt.vtrading.lib.exception.ExchangeErrorCode;
import com.lzt.vtrading.lib.exception.StrategyException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PairUtils {
	
	/**
	 * 币对拼接
	 * 
	 * @param counterCoin
	 * @param baseCoin
	 * @return
	 */
	public static String getPair(String counterCoin, String baseCoin) {

		if (StringUtils.isEmpty(counterCoin) || StringUtils.isEmpty(baseCoin)) {
			log.error("交易币：{}，本币：{}", counterCoin, baseCoin);

			throw new StrategyException(ExchangeErrorCode.INVALID_REQ_PARAMS);
		}

		return counterCoin.toUpperCase() + "_" + baseCoin.toUpperCase();

	}

}
