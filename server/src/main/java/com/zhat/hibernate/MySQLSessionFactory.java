package com.zhat.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MySQLSessionFactory {
	
	private static SessionFactory sessionFactory;
	
	public static Session openSession() {
		
		synchronized (MySQLSessionFactory.class) {
			if (sessionFactory == null) {
			
				Configuration configuration=new Configuration().configure(); // configures settings from hibernate.cfg.xml
				
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
	
	    		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	    		
	    		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
		}
		
		return sessionFactory.openSession();
	}
}
