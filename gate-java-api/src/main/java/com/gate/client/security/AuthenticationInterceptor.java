package com.gate.client.security;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.gate.client.constants.GateApiConatants;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
public class AuthenticationInterceptor implements Interceptor {

	/**
	 * apikey
	 */
	private final String apiKey;
	/**
	 * 用户私钥
	 */
	private final String secret;

	public AuthenticationInterceptor(String apiKey, String secret) {
		this.apiKey = apiKey;
		this.secret = secret;
	}


	@Override
	public Response intercept(Chain chain) throws IOException {

		Request request = chain.request();

		boolean isSignatureRequired = request.header(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED) != null;
		// 不加密
		if (!isSignatureRequired) {
			return chain.proceed(request);
		}

		// 加密
		String ts = String.valueOf(System.currentTimeMillis() / 1000);
		String bodyString = this.bodyToString(request.body());
		String queryString = (request.url().query() == null) ? "" : request.url().query();
		String signatureString = String.format("%s\n%s\n%s\n%s\n%s", request.method(), request.url().encodedPath(),
				queryString, DigestUtils.sha512Hex(bodyString), ts);

		// 加密字符串
		String signature = HmacSHA512Signer.sign(signatureString, secret);

		Request newRequest = request.newBuilder().removeHeader(GateApiConatants.ENDPOINT_SECURITY_TYPE_SIGNED)
				.addHeader("KEY", this.apiKey).addHeader("SIGN", signature).addHeader("Timestamp", ts)
				.addHeader("Content-Type", "application/json").build();
		
		return chain.proceed(newRequest);

	}

	private String bodyToString(RequestBody body) {
		if (body == null) {
			return "";
		}
		try {
			Buffer buffer = new Buffer();
			body.writeTo(buffer);
			return buffer.readUtf8();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
