package com.zhat.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncTaskDispatcher implements Runnable {
	
	private static AsyncTaskDispatcher instance;
	
	ExecutorService executorService;
	Queue<AsyncTask> tasks = new ConcurrentLinkedQueue<AsyncTask>();
	
	private AsyncTaskDispatcher(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	synchronized public static void init(ExecutorService executorService) {
		if (instance != null)
			instance = new AsyncTaskDispatcher(executorService);
	}
	
	synchronized public static AsyncTaskDispatcher getInstance() {
		return instance;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				while (tasks.size() == 0)
					wait();
				handleTask(tasks.poll());
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void dispatch(Runnable run, long timeout) {
		List<AsyncTaskListener> listeners = new ArrayList<AsyncTaskListener>();
		dispatch(run, listeners, timeout);
	}
	
	public void dispatch(Runnable run, AsyncTaskListener listener, long timeout) {
		List<AsyncTaskListener> listeners = new ArrayList<AsyncTaskListener>();
		listeners.add(listener);
		dispatch(run, listeners, timeout);
	}
	
	public void dispatch(Runnable run, List<AsyncTaskListener> listeners, long timeout) {
		AsyncTask task = new AsyncTask(run, listeners, timeout);
		tasks.add(task);
	}
	
	private void handleTask(AsyncTask task) {
		Boolean result = true;
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(task.getRun(), result);
		executorService.execute(futureTask);
		
		try {
			futureTask.get(task.getTimeout(), TimeUnit.MILLISECONDS);
			for (AsyncTaskListener listener : task.getListeners())
				if (listener != null)
					listener.onStartAsync();
		} catch (InterruptedException e) {
			for (AsyncTaskListener listener : task.getListeners())
				if (listener != null)
					listener.onError();
		} catch (ExecutionException e) {
			for (AsyncTaskListener listener : task.getListeners())
				if (listener != null)
					listener.onError();
		} catch (TimeoutException e) {
			for (AsyncTaskListener listener : task.getListeners())
				if (listener != null)
					listener.onTimeout();
		}
		
		for (AsyncTaskListener listener : task.getListeners())
			if (listener != null)
				listener.onComplete();
	}
}
