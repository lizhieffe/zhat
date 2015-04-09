package com.zhat.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zhat.concurrency.AsyncTaskDispatcher;
import com.zhat.concurrency.AsyncTaskListener;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.http.response.ZLHttpServletResponse;

public class ZLAsyncContext implements AsyncContext {

	private ServletRequest request;
	private ServletResponse response;
	private long timeout = 10000;	// Default timeout = 10000 ms.
	Set<AsyncListener> listeners = new HashSet<AsyncListener>();
	
	public ZLAsyncContext(ServletRequest servletRequest, ServletResponse servletResponse) {
		this.request = servletRequest;
		this.response = servletResponse;
	}
	
	@Override
	public ServletRequest getRequest() {
		return request;
	}

	@Override
	public ServletResponse getResponse() {
		return response;
	}

	@Override
	public boolean hasOriginalRequestAndResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatch(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatch(ServletContext context, String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void complete() {
		ZLHttpServletRequest request = (ZLHttpServletRequest)getRequest();
		ZLHttpServletResponse response = (ZLHttpServletResponse)getResponse();
		request.getServer().send(request.getSocketChannel(), response.toByteArray());
	}

	@Override
	public void start(Runnable run) {
		final AsyncEvent event = new AsyncEvent(this, getRequest(), getResponse());
		List<AsyncTaskListener> list = new ArrayList<AsyncTaskListener>();
		
		for (final AsyncListener listener : listeners) {
			AsyncTaskListener atl = new AsyncTaskListener() {

				@Override
				public void onStartAsync() {
					try {
						listener.onStartAsync(event);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onComplete() {
					try {
						listener.onComplete(event);
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}

				@Override
				public void onTimeout() {
					try {
						listener.onTimeout(event);
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}

				@Override
				public void onError() {
					try {
						listener.onError(event);
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}
				
			};
			list.add(atl);
		}
		AsyncTaskDispatcher.getInstance().dispatch(run, list, timeout);
	}

	@Override
	public void addListener(AsyncListener listener) {
		listeners.add(listener);
	}

	@Override
	public void addListener(AsyncListener listener,
			ServletRequest servletRequest, ServletResponse servletResponse) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T extends AsyncListener> T createListener(Class<T> clazz)
			throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	@Override
	public long getTimeout() {
		return timeout;
	}
}
