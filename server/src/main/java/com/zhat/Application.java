package com.zhat;

import java.io.IOException;

import com.zhat.server.EchoWorker;
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
    		
    		EchoWorker worker = new EchoWorker();
    		new Thread(worker).start();
    		new Thread(new Server(null, PORT, worker)).start();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	catch (NumberFormatException e) {
    		System.err.println("Wrong server.port format in resource file!");
    		e.printStackTrace();
    	}
    	
    	
    	
    	System.out.println("Test Hibernate");
    }
}
