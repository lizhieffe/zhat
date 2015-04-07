package com.zhat.concurrency;

import java.util.ArrayList;
import java.util.List;

public class AsyncTask {

	private Runnable run;
	private List<AsyncTaskListener> listeners;
	private long timeout;
	
	public AsyncTask(Runnable run, List<AsyncTaskListener> listeners, long timeout) {
		this.run = run;
		
		this.listeners = new ArrayList<AsyncTaskListener>();
		if (listeners != null)
			this.listeners.addAll(listeners);
		
		this.timeout = timeout;
	}
	
	public Runnable getRun() {
		return run;
	}

	public List<AsyncTaskListener> getListeners() {
		return listeners;
	}

	public long getTimeout() {
		return timeout;
	}
}
