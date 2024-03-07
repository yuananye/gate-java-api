package com.gate.fclient.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.fclient.FgateApiRestClient;
import com.gate.fclient.param.FutherOrder;

public class FgateServiceImpl implements FgateApiRestClient {

	private FgateJavaApiService gateJavaApiService;

	public FgateServiceImpl(String apiKey, String secret) {
		gateJavaApiService = FgateApiServiceGenerator.createService(FgateJavaApiService.class, apiKey, secret);
	}


	@Override
	public JSONObject setLeverage(String settle, String contract,String leverage,String crossLeverage) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.setLeverage(settle, contract,leverage,crossLeverage));
	}
	
	@Override
	public JSONObject setMode(boolean dual_mode) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.setMode(dual_mode));
	}
	
	
	@Override
	public JSONArray getPrice(String contractId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getPrice(contractId));
	}
	
	
	@Override
	public JSONObject getDepth(String contractId, Integer limit) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getDepth(contractId,limit));
	}


	@Override
	public JSONObject getAccounts() {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getAccounts());
	}


	@Override
	public JSONArray getPositions(String contractId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getPositions(contractId));
	}


	@Override
	public JSONObject getContractInfo(String contractId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getContractInfo(contractId));
	}


	@Override
	public JSONObject getFee(String contractId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getFee(contractId));
	}


	@Override
	public JSONArray getKline(String contractId,String interval, Integer limit) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getKline(contractId,interval,limit));
	}


	@Override
	public JSONArray getOrderList(String contract, String status, Integer limit) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getOrderList(contract,status,limit));
	}


	@Override
	public JSONObject placeOrder(FutherOrder futherOrder) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.placeOrder(futherOrder));
	}


	@Override
	public JSONArray batchCancelOrder(String contract) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.batchCancelOrder(contract));
	}


	@Override
	public JSONObject cancelOrder(String orderId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.cancelOrder(orderId));
	}


	@Override
	public JSONObject getOrderDetail(String orderId) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getOrderDetail(orderId));
	}


	@Override
	public JSONArray getAccountRecord(String contract, Long startTime, Long endTime, String type) {
		return FgateApiServiceGenerator.executeSync(this.gateJavaApiService.getAccountRecord(contract,startTime,endTime,type));
	}
	
	


	


	


	




	
}
