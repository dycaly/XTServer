package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.User;
import com.ktboys.XTServer.Entity.Userditals;
import com.ktboys.XTServer.Manager.UserManage;

public class RegistTest {

//	private Session session;
//	private Transaction transaction;

	@Before
	public void setUp() throws Exception {
//		session = HibernateSessionFactory.getSession();
//		transaction = session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
//		transaction.commit();
//		session.close();
	}

	@Test
	public void test() {

		String username = "trsTest";
		String password = "123";
		UserManage um = new UserManage(username, password);
		if (um.isExist()) {
			um.updatePassword("987");
			System.out.println("注册成功");
		}
		else{
			System.out.println("该用户名已被注册");
		}
		um.close();
		
//		String username = "bushiba";
//		String password = "123";
//
//		String hqlSame = " from User u where u.username = '" + username + "' ";
//		Query query = session.createQuery(hqlSame);
//		User user = (User) query.uniqueResult();
//		if (user == null) {
//			String hqlId = " select u.userId from User u where u.userId >=ALL(select u.userId from u)";
//			Query queryId = session.createQuery(hqlId);
//			int id = (int) queryId.uniqueResult();
//			id++;
//			session.save(new User(id, username, password, username + "_"
//					+ password, 0));
//			Userditals ud = new Userditals();
//			ud.setUser(user);
//			ud.setUserId(id);
//			ud.setRegdate(new Timestamp(System.currentTimeMillis()));
//			session.save(ud);
//		} else {
//			System.out.println("该用户名已被注册");
//		}
		
	}

}
