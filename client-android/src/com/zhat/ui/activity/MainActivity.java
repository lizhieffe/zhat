package com.zhat.ui.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.zhat.R;
import com.zhat.ui.fragment.ChatListFragment;
import com.zhat.ui.fragment.FriendListFragment;
import com.zhat.ui.util.TabListener;

public class MainActivity extends Activity {

	private final static String SELECTED_TAB = "selected_tab";
	private int mSelectedTab = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initiate the app preference
        initAppPreference();
        
        if (savedInstanceState != null)
        	mSelectedTab = savedInstanceState.getInt(SELECTED_TAB);
        
        initNavigationTabs();
        
        String serverUrl = PreferenceManager.getDefaultSharedPreferences(this)
        		.getString("server_url", "");
        System.out.println(serverUrl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	savedInstanceState.putInt(SELECTED_TAB, getActionBar().getSelectedNavigationIndex());
    	
    	super.onSaveInstanceState(savedInstanceState);
    }
    
    public void initAppPreference() {
    	PreferenceManager.setDefaultValues(this, R.xml.preference, false);
    }
    
    public void initNavigationTabs() {
    	ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        
        Tab tab = actionBar.newTab().setText(R.string.friends).setTabListener(
        		new TabListener<FriendListFragment>(this, FriendListFragment.TAG, FriendListFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab().setText(R.string.chat).setTabListener(
        		new TabListener<ChatListFragment>(this, ChatListFragment.TAG, ChatListFragment.class));
        actionBar.addTab(tab);
        
        actionBar.setSelectedNavigationItem(mSelectedTab);
    }
}
