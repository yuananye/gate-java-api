package com.gate.fclient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.fclient.param.FutherOrder;

public interface FgateApiRestClient {
	
	public JSONObject setLeverage(String settle,String contract,String leverage,String crossLeverage);
	
	public JSONObject setMode(boolean dual_mode);
	
	public JSONArray getPrice(String contractId);
	
	public JSONObject getDepth(String contractId,Integer limit);
	
	public  JSONObject getAccounts();
	
	public JSONArray getPositions(String contractId);
	
	public JSONObject getContractInfo(String contractId);
	
	public JSONObject getFee(String contractId);
	
	public JSONArray getKline(String contractId,String interval,Integer limit);
	
	public JSONArray getOrderList(String contract,String status,Integer limit);
	
	public JSONObject placeOrder(FutherOrder futherOrder);
	
	public JSONArray batchCancelOrder(String contract);
	
	public JSONObject cancelOrder(String orderId);
	
	public JSONObject getOrderDetail(String orderId);
	
	public JSONArray getAccountRecord(String contract,Long startTime,Long endTime,String type);
	
	

	
	
	
	
	

}
