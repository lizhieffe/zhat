package com.zhat.interfaces;

import com.zhat.http.ZLHttpServletRequest;
import com.zhat.http.response.ZLHttpServletResponse;

public interface IZLHttpServlet {
	public void service(ZLHttpServletRequest request, ZLHttpServletResponse response);
}
