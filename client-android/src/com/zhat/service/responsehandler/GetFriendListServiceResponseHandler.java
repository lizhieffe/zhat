package com.zhat.service.responsehandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.app.ListFragment;
import android.widget.ArrayAdapter;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zhat.model.Friend;
import com.zhat.model.FriendBank;

public class GetFriendListServiceResponseHandler extends AsyncHttpResponseHandler {
	
	private final ListFragment mFriendListFragment;
	
	public GetFriendListServiceResponseHandler(ListFragment friendListFragment) {
		mFriendListFragment = friendListFragment;
	}
	
	@Override
	public void onStart() {
		
	}
	
	@Override
    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
        // called when response HTTP status is "200 OK"
		FriendBank.getInstance().setFriends(parseFriendListFromResponse());
		((ArrayAdapter<?>)mFriendListFragment.getListAdapter()).notifyDataSetChanged();
    }
	
	@Override
    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		e.printStackTrace();
    }

    @Override
    public void onRetry() {
        // called when request is retried
	}
    
    private List<Friend> parseFriendListFromResponse() {
    	return new ArrayList<Friend>();
    }
}
