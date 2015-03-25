package com.zhat.model;

public class ChatMessage {
	
	public final static boolean SENT_BY_ME = true;
	public final static boolean SENT_TO_ME = false;
	
	private boolean mSentByMe;
	private Friend mFriend;
	private String mContent;
	
	public ChatMessage(boolean isSentByMe, Friend friend, String content) {
		mSentByMe = isSentByMe;
		mFriend = friend;
		mContent = content;
	}
	
	public boolean isSentByMe() {
		return mSentByMe;
	}

	public void setIsSentByMe(boolean isSentByMe) {
		mSentByMe = isSentByMe;
	}

	public Friend getFriend() {
		return mFriend;
	}

	public void setFriend(Friend friend) {
		mFriend = friend;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		mContent = content;
	}
}
