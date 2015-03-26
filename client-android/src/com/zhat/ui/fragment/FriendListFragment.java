package com.zhat.ui.fragment;

import java.util.List;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.zhat.R;
import com.zhat.model.Friend;
import com.zhat.model.FriendBank;

public class FriendListFragment extends ListFragment {

	public final static String TAG = FriendListFragment.class.getCanonicalName();

	private List<Friend> mFriends;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initFriends();
		initListAdapter();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		return v;
	}
	
	private void initFriends() {
		mFriends = FriendBank.getInstance().getFriends();
	}
	
	private void initListAdapter() {
		ArrayAdapter<Friend> adapter = new FriendListAdapter(mFriends);
		setListAdapter(adapter);
	}
	
	private class FriendListAdapter extends ArrayAdapter<Friend> {
		
		FriendListAdapter(List<Friend> friends) {
			super(getActivity(), 0, friends);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null)
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_friend, null);
			
			// Configure the view for this crime
			Friend friend = getItem(position);
			
			TextView friendNameTextView = (TextView)convertView.findViewById(R.id.friend_name);
			friendNameTextView.setText(friend.getName());
			
			return convertView;
		}
	}
}
