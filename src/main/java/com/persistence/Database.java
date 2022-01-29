package com.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
@Component
public class Database {
	 private static Session session;
	
	 public static Session getSession(){
		 if(session == null) {
			 Configuration cfg = new Configuration();
		     cfg.configure("hibernate.cfg.xml");
		     SessionFactory factory = cfg.buildSessionFactory();
		     session = factory.openSession();
		 }
		 return session;
	 }
}
