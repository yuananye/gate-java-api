package com.gate.client.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class to sign messages using HMAC-SHA256.
 */
public class HmacSHA512Signer {

	/**
	 * Sign the given message using the given secret.
	 * 
	 * @param message message to sign
	 * @param secret  secret key
	 * @return a signed message
	 */
	public static String sign(String signatureString, String secret) {

		try {
			Mac hmacSha512 = Mac.getInstance("HmacSHA512");
			SecretKeySpec spec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
			hmacSha512.init(spec);
			return Hex.encodeHexString(hmacSha512.doFinal(signatureString.getBytes()));

		} catch (Exception e) {
			throw new RuntimeException("加密失败.", e);
		}
	}
}
