package com.zhat.interfaces;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpServletResponse;

public interface IZLHttpServlet {
	public void service(ZLHttpRequest request, ZLHttpServletResponse response);
}
