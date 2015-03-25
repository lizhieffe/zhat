package com.zhat.model;

import java.util.ArrayList;
import java.util.List;

public class ChatBank {
	
	private static ChatBank instance;
	
	private List<Chat> mChats = new ArrayList<Chat>();
	
	public static ChatBank getInstance() {
		if (instance == null)
			instance = new ChatBank();
		return instance;
	}
	
	/**
	 * Create chats on initialization for now.
	 */
	private ChatBank() {
		Friend friend1 = new Friend("Lijuan Zhang");
		Friend friend2 = new Friend("Yanheng Li");
		Friend friend3 = new Friend("Peng Wu");
		
		ChatMessage chatMsg1 = new ChatMessage(ChatMessage.SENT_BY_ME, friend1, "hahahaha");
		ChatMessage chatMsg2 = new ChatMessage(ChatMessage.SENT_TO_ME, friend2, "Ji You");
		ChatMessage chatMsg3 = new ChatMessage(ChatMessage.SENT_TO_ME, friend3, "Go to CA!!!");
		
		Chat chat1 = new Chat(friend1, chatMsg1);
		Chat chat2 = new Chat(friend2, chatMsg2);
		Chat chat3 = new Chat(friend3, chatMsg3);
		
		mChats.add(chat1);
		mChats.add(chat2);
		mChats.add(chat3);
	}
	
	public List<Chat> getChats() {
		return mChats;
	}
}
