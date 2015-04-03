package com.zhat;

import java.io.IOException;

import org.hibernate.Session;

import com.zhat.hibernate.MySQLSessionFactory;
import com.zhat.model.User;
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
    	Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	User user = new User();
    	user.setName("vli");
    	user.setSex(User.SEX_MALE);
    	session.save(user);
    	
    	User user1 = new User();
    	user1.setName("lijuan");
    	user1.setSex(User.SEX_FEMALE);
    	session.save(user1);
    	
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
    }
}
