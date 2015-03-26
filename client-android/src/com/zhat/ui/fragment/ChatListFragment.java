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
import com.zhat.model.Chat;
import com.zhat.model.ChatBank;

public class ChatListFragment extends ListFragment {

	public final static String TAG = ChatListFragment.class.getCanonicalName();
		
	private List<Chat> mChats;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initChats();
		initListAdapter();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		return v;
	}
	
	private void initChats() {
		mChats = ChatBank.getInstance().getChats();
	}
	
	private void initListAdapter() {
		ArrayAdapter<Chat> adapter = new ChatListAdapter(mChats);
		setListAdapter(adapter);
	}
	
	private class ChatListAdapter extends ArrayAdapter<Chat> {
		
		ChatListAdapter(List<Chat> chats) {
			super(getActivity(), 0, chats);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null)
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_chat, null);
			
			// Configure the view for this crime
			Chat chat = getItem(position);
			
			TextView friendNameTextView = (TextView)convertView.findViewById(R.id.friend_name);
			friendNameTextView.setText(chat.getFriend().getName());
			
			TextView lastConversationTextView = (TextView)convertView.findViewById(R.id.last_conversation_content);
			lastConversationTextView.setText(chat.getLastMsg().getContent());
			
			return convertView;
		}
	}
}
