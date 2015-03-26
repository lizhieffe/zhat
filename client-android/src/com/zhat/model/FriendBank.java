package com.zhat.model;

import java.util.ArrayList;
import java.util.List;

public class FriendBank {
	
	private static FriendBank instance;
	
	private List<Friend> mFriends;
	
	public List<Friend> getFriends() {
		return mFriends;
	}
	
	private FriendBank() {
		mFriends = new ArrayList<Friend>();
		
		Friend friend1 = new Friend("Lijuan Zhang");
		Friend friend2 = new Friend("Yanheng Li");
		Friend friend3 = new Friend("Peng Wu");
		Friend friend4 = new Friend("Chu Wang");
		Friend friend5 = new Friend("Nanhu Chen");
		Friend friend6 = new Friend("Junqiang Zhang");
		
		mFriends.add(friend1);
		mFriends.add(friend2);
		mFriends.add(friend3);
		mFriends.add(friend4);
		mFriends.add(friend5);
		mFriends.add(friend6);
	}
	
	public void setFriends(List<Friend> friends) {
		mFriends = friends;
	}

	public static FriendBank getInstance() {
		if (instance == null)
			instance = new FriendBank();
		return instance;
	}
}
