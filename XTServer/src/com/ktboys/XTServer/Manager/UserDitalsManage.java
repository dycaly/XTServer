package com.ktboys.XTServer.Manager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Userditals;

public class UserDitalsManage {

	private Userditals mUserditals = null;
	private Session session;
	private Transaction transaction;

	public void close() {

		session.close();
	}

	public UserDitalsManage(int Id) {
		session = HibernateSessionFactory.getSession();
		String hql = " from Userditals ud where ud.userId =" + Id;

		Query query = session.createQuery(hql);
		mUserditals = (Userditals) query.uniqueResult();
	}

	public String getJson() {
		if (mUserditals != null) {

			Gson gson = new Gson();
			return gson.toJson(mUserditals);
		} else {
			return null;
		}
	}

	public int updateUserDitals(String picUrl, String nickname, String name,
			Integer age, Integer sex, String school, String college,
			String email, String phone) {
		transaction = session.beginTransaction();
		mUserditals.setPicUrl(picUrl);
		mUserditals.setNickname(nickname);
		mUserditals.setName(name);
		mUserditals.setAge(age);
		mUserditals.setSex(sex);
		mUserditals.setSchool(school);
		mUserditals.setCollege(college);
		mUserditals.setEmail(email);
		mUserditals.setPhone(phone);
		session.update(mUserditals);
		transaction.commit();
		return 0;
	}
}
