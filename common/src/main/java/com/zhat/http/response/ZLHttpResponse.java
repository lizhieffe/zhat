package com.zhat.http.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.BasicHttpEntity;
import org.json.JSONObject;
import com.zhat.http.ZLHttpContentType;

public class ZLHttpResponse {
	
	private int statusCode;
	private BasicHttpEntity entity;
	
	public ZLHttpResponse() {
		this.entity = new BasicHttpEntity();
	}
	
	public void setStatus(int sc) {
		this.statusCode = sc;
	}
	
	public int getStatus() {
		return this.statusCode;
	}
	
	/**
	 * Get the reason phrase for a particular status code.
	 * @return
	 */
	public String getStatusText() {
		return HttpStatus.getStatusText(this.statusCode);
	}
	
	public InputStream getHeaders() {
		return IOUtils.toInputStream(getHeadersText());
	}
	
	public String getHeadersText() {
		String hs = this.entity.toString();
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
	
	/**
	 * Specifies the content.
	 * @param instream
	 */
	public void setContent(InputStream instream) {
		this.entity.setContent(instream);
	}
	
	/**
	 * Specifies the content.
	 * @param cString
	 */
	public void setContent(String cString) {
		try {
			setContent(IOUtils.toInputStream(cString, "UTF-8"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Specifies the content.
	 * @param cJson
	 */
	public void setContent(JSONObject cJson) {
		setContent(cJson.toString());
	}
	
	/**
	 * Returns a content stream of the entity.
	 * @return
	 */
	public InputStream getContent() {
		return this.entity.getContent();
	}
	
	/**
	 * Returns a content string of the entity.
	 * @return
	 * @throws IOException
	 */
	public String getContentText() throws IOException {
		return IOUtils.toString(getContent());
	}
	
	/**
	 * Specifies the Content-Type header.
	 * @param contentType
	 */
	public void setContentType(ZLHttpContentType type) {
		setContentType(type.getContentTypeText());
	}
	
	/**
	 * Specifies the Content-Type header, as String.
	 * @param ctString
	 */
	private void setContentType(String ctString) {
		entity.setContentType(ctString);
	}
	
	public InputStream toInputStream() throws IOException {
		String statusLine = "HTTP/1.1 " + getStatus() + " " + getStatusText() + "\n";
		String emptyLine = "\n";
		
		return new SequenceInputStream(IOUtils.toInputStream(statusLine),
				new SequenceInputStream(getHeaders(),
						new SequenceInputStream(IOUtils.toInputStream(emptyLine, "UTF-8")
												, getContent())));
	}
	
	public byte[] toByteArray() throws IOException {
		return IOUtils.toByteArray(toInputStream());
	}
	
	
}
