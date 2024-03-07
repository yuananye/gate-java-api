package com.gate.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.client.param.Order;

public interface GateApiRestClient {
	
	public JSONObject getDepth(String pair,Integer limit);
	
	public JSONArray getTickers(String pair);
	
	public JSONArray getKline(String pair,String interval);
	
	public JSONObject getPairInfo(String pair);
	
	public JSONObject queryFee(String pair);
	
	public JSONObject queryOrderDetail(String orderId,String pair);
	
	public JSONObject  placeOrder(Order order);
	
	public JSONObject cancelOrder(String orderId,String pair);
	
	public JSONArray queryAccounts();
	
	
	
	
	

}
