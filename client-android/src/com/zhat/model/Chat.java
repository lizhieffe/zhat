package com.zhat.model;

public class Chat {
	
	Friend mFriend;
	ChatMessage mLastMsg;

	public Chat(Friend friend, ChatMessage lastMsg) {
		mFriend = friend;
		mLastMsg = lastMsg;
	}

	public Friend getFriend() {
		return mFriend;
	}

	public void setFriend(Friend friend) {
		mFriend = friend;
	}
	
	public ChatMessage getLastMsg() {
		return mLastMsg;
	}

	public void setLastMsg(ChatMessage lastMsg) {
		mLastMsg = lastMsg;
	}
}
