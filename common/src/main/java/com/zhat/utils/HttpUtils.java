package com.zhat.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;

public class HttpUtils {
	
	public static byte[] HttpEntity2ByteArray(HttpEntity httpEntity) 
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		httpEntity.writeTo(baos);
	    return baos.toByteArray();
	}
}
