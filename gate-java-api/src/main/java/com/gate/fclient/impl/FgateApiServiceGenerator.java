package com.gate.fclient.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.gate.client.constants.GateApiConatants;
import com.gate.client.security.AuthenticationInterceptor;
import com.lzt.vtrading.lib.exception.ExchangeErrorCode;
import com.lzt.vtrading.lib.exception.StrategyException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
public class FgateApiServiceGenerator {

	private static final OkHttpClient sharedClient = new OkHttpClient.Builder().pingInterval(20, TimeUnit.SECONDS)
			.build();

	private static final Converter.Factory converterFactory = JacksonConverterFactory.create();

	/**
	 * 实例化
	 *
	 * @param serviceClass
	 * @return
	 */
	public static <S> S createService(Class<S> serviceClass) {
		return createService(serviceClass, null, null);
	}

	/**
	 * 实例化
	 *
	 * @param serviceClass
	 * @return
	 */
	public static <S> S createService(Class<S> serviceClass, String apiKey, String secret) {
		
		
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(GateApiConatants.FUTHER_BASE_URL)
				.addConverterFactory(converterFactory);
		

		if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(secret)) {
			retrofitBuilder.client(sharedClient);
		} else {

			AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret);
			OkHttpClient adaptedClient = sharedClient.newBuilder().addInterceptor(interceptor).build();
			retrofitBuilder.client(adaptedClient);
		}

		Retrofit retrofit = retrofitBuilder.build();
		return retrofit.create(serviceClass);
	}

	/**
	 * 解析响应参数
	 *
	 * @param call
	 * @return
	 * @throws Exception
	 */
	public static <T> T executeSync(Call<T> call) {
		try {
			Response<T> response = call.execute();

			if (response.isSuccessful()) {
				return response.body();
			} else {
				log.error("连接不上Gate交易所,错误信息：{},错误码：{}", response.message(), response.code());
				String errorMsg = response.errorBody().string();
				log.error("连接不上Gate交易所,错误信息：{}", errorMsg);
				
				throw new StrategyException(508,errorMsg);
			}
		} catch (IOException e) {
			log.error("连接不上Gate交易所，原地址是：{}", call.request());
			throw new StrategyException(ExchangeErrorCode.URL_ADDRESS_FAIL);
		}
	}

}
