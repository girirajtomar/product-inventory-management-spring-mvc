package com.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.model.User;
@Component
public class UserDatabase {	 
	 public static boolean isExist(User user) {
		 Session session = Database.getSession();
		 Query<User> query = session.createQuery("from User where username=:u and password=:p",User.class);
		 query.setParameter("u", user.getUsername());
		 query.setParameter("p", user.getPassword());
		
		 return query.uniqueResult() != null;	
	 }
	 
	 public static void createUser(User user) {
		 Session session = Database.getSession();
		 Transaction tx = session.beginTransaction();
		 session.save(user);
		 tx.commit();
	 }
	 
	 public static void main(String[] args) {
	
		 User user = new User("giriraj","tomar");
		 createUser(user);
		 
	 }

}
