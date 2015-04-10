package com.zhat;

import java.io.IOException;

import com.zhat.concurrency.AsyncTaskDispatcher;
import com.zhat.concurrency.ThreadPool;
import com.zhat.server.Server;
import com.zhat.utils.AppProperties;

public class Application {
	
    /**
     * ServerSocketChannel represents a channel for sockets that listen to
     * incoming connections.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
     	
    	try {
    		int PORT = Integer.parseInt(AppProperties.get("server.port"));
    		
    		new Thread(new Server(null, PORT)).start();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	catch (NumberFormatException e) {
    		System.err.println("Wrong server.port format in resource file!");
    		e.printStackTrace();
    	}
    	
    	AsyncTaskDispatcher.init(ThreadPool.getInstance().getExecutorService());
    	new Thread(AsyncTaskDispatcher.getInstance()).start();
    	
    }
}
