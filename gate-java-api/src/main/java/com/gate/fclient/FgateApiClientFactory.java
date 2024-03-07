package com.gate.fclient;

import com.gate.fclient.impl.FgateServiceImpl;

public class FgateApiClientFactory {
	/**
	 * apiKey
	 */
	private String apiKey;
	/**
	 * Secret.
	 */
	private String secret;

	private FgateApiClientFactory(String apiKey, String secret) {
		this.apiKey = apiKey;
		this.secret = secret;
	}

	public static FgateApiClientFactory newInstance(String apiKey, String secret) {
		return new FgateApiClientFactory(apiKey, secret);

	}
	
	public static FgateApiClientFactory newInstance() {
		return new FgateApiClientFactory(null, null);

	}

	public FgateServiceImpl newRestClient() {
		return new FgateServiceImpl(apiKey, secret);
	}

}
