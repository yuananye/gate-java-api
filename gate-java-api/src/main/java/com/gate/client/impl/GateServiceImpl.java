package com.gate.client.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gate.client.GateApiRestClient;
import com.gate.client.param.Order;

public class GateServiceImpl implements GateApiRestClient {

	private GateJavaApiService gateJavaApiService;

	public GateServiceImpl(String apiKey, String secret) {
		gateJavaApiService = GateApiServiceGenerator.createService(GateJavaApiService.class, apiKey, secret);
	}

	@Override
	public JSONObject getDepth(String pair, Integer limit) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.getDepth(pair, limit));
	}

	@Override
	public JSONArray getTickers(String pair) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.getTickers(pair));
	}

	@Override
	public JSONArray getKline(String pair, String interval) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.getKline(pair, interval));
	}

	@Override
	public JSONObject getPairInfo(String pair) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.getPairInfo(pair));
	}

	@Override
	public JSONObject queryFee(String pair) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.queryFee(pair));
	}

	@Override
	public JSONObject queryOrderDetail(String pair, String orderId) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.queryOrderDetail(pair, orderId));
	}

	@Override
	public JSONObject placeOrder(Order order) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.placeOrder(order));
	}

	@Override
	public JSONObject cancelOrder(String orderId, String pair) {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.cancelOrder(orderId, pair));
	}

	@Override
	public JSONArray queryAccounts() {
		return GateApiServiceGenerator.executeSync(this.gateJavaApiService.queryAccounts());
	}
}
