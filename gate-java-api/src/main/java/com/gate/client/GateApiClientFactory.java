package com.gate.client;

import com.gate.client.impl.GateServiceImpl;

public class GateApiClientFactory {
	/**
	 * apiKey
	 */
	private String apiKey;
	/**
	 * Secret.
	 */
	private String secret;

	private GateApiClientFactory(String apiKey, String secret) {
		this.apiKey = apiKey;
		this.secret = secret;
	}

	public static GateApiClientFactory newInstance(String apiKey, String secret) {
		return new GateApiClientFactory(apiKey, secret);

	}
	
	public static GateApiClientFactory newInstance() {
		return new GateApiClientFactory(null, null);

	}

	public GateApiRestClient newRestClient() {
		return new GateServiceImpl(apiKey, secret);
	}

}
