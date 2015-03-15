package com.zhat.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

import com.zhat.utils.AppProperties;

public class MemcachedManager {
	
	private static MemcachedManager instance;
	
	private String IP;
	private int PORT;
	private MemcachedClient client;
	
	public static MemcachedManager getInstance() {
		if (instance == null)
			instance = new MemcachedManager();
		return instance;
	}
	
	public MemcachedClient getMemcachedClient() {
		if (client == null)
			client = initMemcachedClient();
		return client;
	}
	
	private MemcachedClient initMemcachedClient() {
		try {
			this.IP = AppProperties.get("memcached.ip");
			this.PORT = Integer.parseInt(AppProperties.get("memcached.port"));
			return new MemcachedClient(new InetSocketAddress(this.IP, this.PORT));
		}
		catch (NumberFormatException e) {
			System.err.println("Wrong memcached port format!");
			e.printStackTrace();
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Cannot connect to Memcached server!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
