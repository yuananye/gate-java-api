package com.gate.fclient.impl;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.client.constants.GateApiConatants;
import com.gate.fclient.param.FutherOrder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FgateJavaApiService {

	/**
	 * 设置杠杆
	 */
	@POST("futures/{settle}/positions/{contract}/leverage")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> setLeverage(@Path("settle") String settle, @Path("contract") String contract,
			@Query("leverage") String leverage, @Query("cross_leverage_limit") String cross_leverage_limit);

	/**
	 * 设置持仓模式
	 */
	@POST("futures/usdt/dual_mode")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> setMode(@Query("dual_mode") boolean dual_mode);

	/**
	 * 获取价格
	 *
	 * @return
	 */
	@GET("futures/usdt/tickers")
	Call<JSONArray> getPrice(@Query("contract") String contractId);

	/**
	 * 获取深度
	 *
	 * @return
	 */
	@GET("futures/usdt/order_book")
	Call<JSONObject> getDepth(@Query("contract") String contractId, @Query("limit") Integer limit);
	
	/**
	 * 获取Kline数据
	 *
	 * @return
	 */
	@GET("futures/usdt/candlesticks")
	Call<JSONArray> getKline(@Query("contract") String contractId,@Query("interval") String interval, @Query("limit") Integer limit);

	/**
	 * 获取合约信息
	 *
	 * @return
	 */
	@GET("futures/usdt/contracts/{contract}")
	Call<JSONObject> getContractInfo(@Path("contract") String contractId);

	/**
	 * 获取合约账户信息 
	 */
	@GET("futures/usdt/accounts")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> getAccounts();

	/**
	 * 获取合约仓位
	 */
	@GET("futures/usdt/dual_comp/positions/{contract}")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONArray> getPositions(@Path("contract") String contractId);
	
	/**
	 * 获取合约手续费
	 */
	@GET("futures/usdt/fee")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> getFee(@Query("contract") String contract);
	
	
	/**
	 * 获取订单列表
	 */
	@GET("futures/usdt/orders")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONArray> getOrderList(@Query("contract") String contract,@Query("status") String status,@Query("limit") Integer limit);
	
	
	/**
	 * 下单
	 */
	@POST("futures/usdt/orders")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> placeOrder(@Body FutherOrder futherOrder);
	
	/**
	 * 批量撤销订单 
	 */
	@DELETE("futures/usdt/orders")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONArray> batchCancelOrder(@Query("contract") String contract);
	
	/**
	 *撤销单个订单 
	 */
	@DELETE("futures/usdt/orders/{order_id}")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> cancelOrder(@Path("order_id") String orderId);
	
	/**
	 *查询订单详情 
	 */
	@GET("futures/usdt/orders/{order_id}")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONObject> getOrderDetail(@Path("order_id") String orderId);
	
	/**
	 *查询账户变动详情
	 */
	@GET("futures/usdt/account_book")
	@Headers(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
	Call<JSONArray> getAccountRecord(@Query("contract") String contractId,@Query("from") Long startTime,@Query("to") Long endTime,@Query("type") String type);

}
