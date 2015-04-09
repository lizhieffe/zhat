package com.zhat.http.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class HttpResponseUtils {
	
	public static int getStatusCode(HttpResponse response) {
		return response.getStatusLine().getStatusCode();
	}
	
	public static String getStatusText(int statusCode) {
		return HttpStatus.getStatusText(statusCode);
	}
	
	public static String getStatusText(HttpResponse response) {
		return getStatusText(getStatusCode(response));
	}
	
	public static String getHeadersText(HttpEntity entity) {
		String hs = entity.toString();
		if (hs == null || hs.length() == 0)
			return "";
		if (hs.charAt(0) == '[')
			hs = hs.substring(1, hs.length());
		if (hs.charAt(hs.length() - 1) == ']')
			hs = hs.substring(0, hs.length() - 1);
		String[] headers = hs.split(",");
		if (headers.length == 0)
			return "";
		StringBuilder b = new StringBuilder();
		for (String s : headers) {
			b.append(s);
			b.append("\n");
		}
		return b.toString();
	}
	
	public static String getHeadersText(HttpResponse response) {
		return getHeadersText(response.getEntity());
	}
	
	public static InputStream getHeadersInputStream(HttpEntity entity) {
		return IOUtils.toInputStream(getHeadersText(entity));
	}
	
	public static InputStream getHeadersInputStream(HttpResponse response) {
		return getHeadersInputStream(response.getEntity());
	}
	
	/**
	 * Returns a content stream of the entity.
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static InputStream getContentInputStream(HttpEntity entity) 
			throws IOException {
		return entity.getContent();
	}
	
	public static InputStream getContentInputStream(HttpResponse response) 
			throws IOException {
		return getContentInputStream(response.getEntity());
	}
	
	/**
	 * Returns a content string of the entity.
	 * @return
	 * @throws IOException
	 */
	public static String getContentText(HttpEntity entity) throws IOException {
		return IOUtils.toString(getContentInputStream(entity));
	}
	
	public static String getContentText(HttpResponse response) throws IOException {
		return getContentText(response.getEntity());
	}
	
	public static InputStream toInputStream(HttpResponse response) throws IOException {
//		String statusLine = "HTTP/1.1 " + response.getStatusLine().getStatusCode() 
//				+ " " + getStatusText(response) + "\n";
		String statusLine = response.getStatusLine().toString() + "\n";
		String emptyLine = "\n";
		
		return new SequenceInputStream(IOUtils.toInputStream(statusLine),
				new SequenceInputStream(getHeadersInputStream(response),
						new SequenceInputStream(IOUtils.toInputStream(emptyLine, "UTF-8")
												, getContentInputStream(response))));
	}
	
	public static byte[] toByteArray(HttpResponse response) throws IOException {
		return IOUtils.toByteArray(toInputStream(response));
	}
}
