package com.zhat.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ZLIOUtils {
	
	public static ByteArrayInputStream outputStream2InputStream(ByteArrayOutputStream os) {
		byte[] data = os.toByteArray();
		return new ByteArrayInputStream(data);
	}
	
	public static String outputStream2String(ByteArrayOutputStream os) {
		return os.toString();
	}
	
	public static InputStream string2InputStream(String str) throws IOException {
		return IOUtils.toInputStream(str, "UTF-8");
	}
}
