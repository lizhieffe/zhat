package com.zhat.servlets;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.servlet.ZLAsyncContext;
import com.zhat.servlet.ZLHttpServletHelper;

public class delay9sec extends ZLHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120586465036465259L;
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {
		final ZLAsyncContext asyncCtx = ((ZLHttpServletRequest) request).startAsync(request, response);
		asyncCtx.addListener(new AsyncListener() {
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				
			}

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				ZLHttpServletHelper.doGetException(
						request, response, new ServletException("Server time out."));
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
			
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {

			}
		});
		
		asyncCtx.start(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(9000);
					String responseJsonText = "Finished";
					
					response.getWriter().println(responseJsonText);
					response.getWriter().close();
				}
				catch (IOException e) {
					ZLHttpServletHelper.doGetException(request, response, e);
				}
				catch (NumberFormatException e) {
					ZLHttpServletHelper.doGetException(request, response, new ServletException("Wrong user_id format."));
				}
				catch (InterruptedException e) {
					ZLHttpServletHelper.doGetException(request, response, e);
				}
				finally {
					asyncCtx.complete();
				}
			}
		});
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		throw new ServletException("POST method is not supported.");
	}
}
