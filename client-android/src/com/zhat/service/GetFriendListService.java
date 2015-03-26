package com.zhat.service;

import android.content.Context;
import android.preference.PreferenceManager;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GetFriendListService {

	private final Context mContext;
	private final AsyncHttpResponseHandler mHandler;
	private final String mUserId;
	
	public GetFriendListService(Context context
			, AsyncHttpResponseHandler handler, String userId) {
		mContext = context;
		mHandler = handler;
		mUserId = userId;
	}
	
	private String getUri() {
		return "/get_friend_list";
	}
	
	public void start() {
		String domain = PreferenceManager.getDefaultSharedPreferences(mContext).getString("server_url", "");
		String port = PreferenceManager.getDefaultSharedPreferences(mContext).getString("server_port", "");
		String url = domain +":" + port + getUri();
		
		RequestParams params = new RequestParams();
		params.put("user_id", mUserId);
		
		RestClient.get(url, params, mHandler);
	}
}
