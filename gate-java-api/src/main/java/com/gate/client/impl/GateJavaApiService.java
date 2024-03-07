package com.gate.client.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.client.constants.GateApiConatants;
import com.gate.client.param.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GateJavaApiService {

	/**
	 * 获取深度
	 *
	 * @return
	 */
	@GET("spot/order_book")
	Call<JSONObject> getDepth(@Query("currency_pair") String pairs,@Query("limit") Integer limit);
	
	/**
	 * 获取价格
	 *
	 * @return
	 */
	@GET("spot/tickers")
	Call<JSONArray> getTickers(@Query("currency_pair") String pairs);
	
	/**
	 * 获取价格
	 *
	 * @return
	 */
	@GET("spot/candlesticks")
	Call<JSONArray> getKline(@Query("currency_pair") String pair,@Query("interval") String  interval);
	
	/**
	 * 获取交易对信息
	 *
	 * @return
	 */
	@GET("spot/currency_pairs/{currency_pair}")
	Call<JSONObject> getPairInfo(@Path("currency_pair") String pair);
	
	/**
	 * 获取费率
	 *
	 * @return
	 */
	@GET("wallet/fee")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> queryFee(@Query("currency_pair") String pair);
	
	/**
	 * 查询订单详情
	 *
	 * @return
	 */
	@GET("spot/orders/{order_id}")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> queryOrderDetail(@Path("order_id") String orderId,@Query("currency_pair") String pair);
	
	/**
	 * 查询订单详情
	 *
	 * @return
	 */
	@POST("spot/orders")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> placeOrder(@Body Order order);
	
	/**
	 * 撤销单个订单
	 *
	 * @return
	 */
	@DELETE("spot/orders/{order_id}")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> cancelOrder(@Path("order_id") String orderId,@Query("currency_pair") String pair);
	
	/**
	 * 查询账户详情
	 *
	 * @return
	 */
	@GET("spot/accounts")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONArray> queryAccounts();


}
