package com.zhat.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private static ThreadPool instance;
	private static int threads = 50;
	
	private ExecutorService executorService;
	
	private ThreadPool() {
		this.executorService = Executors.newFixedThreadPool(threads);
	}
	
	public static ThreadPool getInstance() {
		if (instance == null) {
			instance = new ThreadPool();
		}
		return instance;
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}
}
