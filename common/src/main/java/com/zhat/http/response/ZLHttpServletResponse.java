package com.zhat.http.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.zhat.http.ZLHttpContentType;
import com.zhat.http.exceptions.ZLHttpRequestContentTypeException;
import com.zhat.utils.ZLIOUtils;

public class ZLHttpServletResponse implements HttpServletResponse {

	public static String KEY_CONTENT_TYPE = "Content-Type";

	private int statusCode;
	private ZLHttpContentType contentType;
	private Map<String, Set<String>> headers;
	private ByteArrayOutputStream contentOutputStream;
	private PrintWriter contentPrintWriter;
	
	public ZLHttpServletResponse() {
		statusCode = 200;
		contentType = ZLHttpContentType.APPLICATION_TEXT_PLAIN;
		headers = new HashMap<String, Set<String>>();
		contentOutputStream = new ByteArrayOutputStream();
		contentPrintWriter = new PrintWriter(contentOutputStream);
	}
	
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		return contentType.getContentTypeText();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return contentPrintWriter;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String type) {
		try {
			contentType = ZLHttpContentType.parseContentTypeFromString(type);
		} catch (ZLHttpRequestContentTypeException e) {
			e.printStackTrace();
		}
	}
	
	public void setContentType(ZLHttpContentType contentType) {
		this.contentType = contentType;
	}

	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDateHeader(String name, long date) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public void setHeader(String name, String value) {
		Set<String> set = new HashSet<String>();
		set.add(value);
		headers.put(name, set);
	}

	@Override
	public void addHeader(String name, String value) {
		if (!headers.containsKey(name))
			headers.put(name, new HashSet<String>());
		headers.get(name).add(value);
	}

	@Override
	public void setIntHeader(String name, int value) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public void addIntHeader(String name, int value) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public void setStatus(int sc) {
		statusCode = sc;
	}
	
	public String getStatusText() {
		return HttpStatus.getStatusText(statusCode);
	}
	
	@Override
	public void setStatus(int sc, String sm) throws NotImplementedException {
		throw new NotImplementedException();
	}

	public int getStatus() {
		return this.statusCode;
	}
	
	/**
	 * Output headers as pretty text.
	 * @return
	 */
	public String getHeadersText() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_CONTENT_TYPE);
		sb.append(": ");
		sb.append(getContentType());
		sb.append("\n");
		Iterator<Entry<String, Set<String>>> it = headers.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Set<String>> entry = it.next();
			String name = entry.getKey();
			Set<String> values = entry.getValue();
			Iterator<String> valuesIt = values.iterator();
			while (valuesIt.hasNext()) {
				sb.append(name);
				sb.append(": ");
				sb.append(valuesIt.next());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public InputStream getHeadersInputStream() {
		try {
			return ZLIOUtils.string2InputStream(getHeadersText());
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getContentText() {
		return ZLIOUtils.outputStream2String(contentOutputStream);
	}
	
	public InputStream getContentInputStream() {
		return ZLIOUtils.outputStream2InputStream(contentOutputStream);
	}
	
	public byte[] toByteArray() {
		try {
			return ZLIOUtils.inputStream2ByteArray(toInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString() {
		return null;
	}
	
	public InputStream toInputStream() {
		String statusLine = "HTTP/1.1 " + getStatus() + " " + getStatusText() + "\n";
		String emptyLine = "\n";
		
		try {
			return new SequenceInputStream(IOUtils.toInputStream(statusLine),
					new SequenceInputStream(getHeadersInputStream(),
							new SequenceInputStream(IOUtils.toInputStream(emptyLine, "UTF-8")
													, getContentInputStream())));
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
