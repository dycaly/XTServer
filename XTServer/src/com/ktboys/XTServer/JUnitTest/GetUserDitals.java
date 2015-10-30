package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Userditals;

public class GetUserDitals {

	private Session session;
	private Transaction transaction;

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		transaction.commit();
		session.close();
	}

	@Test
	public void test() {

		String token = "dycaly_nfs123456";

		String hql = " from Userditals ud where ud.userId in (select u.userId from User u where u.token='"
				+ token + "')";

		Query query = session.createQuery(hql);
		Userditals ud = (Userditals) query.uniqueResult();

		if (ud != null) {
			System.out.println("姓名:" + ud.getName() + " 年龄:" + ud.getAge()
					+ " 性别:" + ud.getSex() + " 学校:" + ud.getSchool() + " 学院:"
					+ ud.getCollege() +" 邮箱:"+ud.getEmail()+" 手机:"+ud.getPhone());
		} else {
			System.out.println("查询用户信息失败");
		}
	}

}
