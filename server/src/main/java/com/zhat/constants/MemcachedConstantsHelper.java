package com.zhat.constants;

public class MemcachedConstantsHelper {
	
	public static String getKeyAllFriends(int userId) {
		return MemcachedConstants.KEY_ALL_FRIENDS + "_" + userId;
	}
}
