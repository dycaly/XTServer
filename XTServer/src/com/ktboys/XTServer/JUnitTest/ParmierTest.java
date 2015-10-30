package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.User;

public class ParmierTest {

	private Session session;
	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void test() {
		String hql = " from User u where u.username = 'dycaly' ";
		
		Query query = session.createQuery(hql);
		
		List<User> lists = query.list();
		
		for(User user :lists){
			System.out.println(user.getUserId() +" "+user.getUsername() +" "+user.getPassword());
		}
		
	}

}
