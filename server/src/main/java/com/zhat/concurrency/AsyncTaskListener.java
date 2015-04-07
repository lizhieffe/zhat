package com.zhat.concurrency;

public interface AsyncTaskListener {
	public void onStartAsync();
	public void onComplete();
	public void onTimeout();
	public void onError();
}
