package com.ktboys.XTServer.JUnitTest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Userditals;
import com.ktboys.XTServer.Manager.UserDitalsManage;
import com.ktboys.XTServer.Manager.UserManage;
import com.ktboys.XTServer.Result.UserDitalsResult;

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
		String token = "dycaly_dyc19950807";
		UserDitalsManage u = new UserDitalsManage(1);
		Userditals ud = u.getUserditals();
		UserDitalsResult udr = new UserDitalsResult(0, ud.getPicUrl(),
				ud.getNickname(), ud.getName(), ud.getAge(), ud.getSex(),
				ud.getSchool(), ud.getCollege(), ud.getEmail(), ud.getPhone(),
				ud.getRegdate().toString());
		Gson gson = new Gson();
		String str = gson.toJson(udr);

		System.out.println(str);
		// String token = "dycaly_dyc19950807";
		//
		// String hql =
		// " from Userditals ud where ud.userId in (select u.userId from User u where u.token='"
		// + token + "')";
		//
		// Query query = session.createQuery(hql);
		// Userditals ud = (Userditals) query.uniqueResult();
		//
		// if (ud != null) {
		// System.out.println("����:" + ud.getName() + " ����:" + ud.getAge()
		// + " �Ա�:" + ud.getSex() + " ѧУ:" + ud.getSchool() + " ѧԺ:"
		// + ud.getCollege() +" ����:"+ud.getEmail()+" �ֻ�:"+ud.getPhone());
		// } else {
		// System.out.println("��ѯ�û���Ϣʧ��");
		// }
	}
}
