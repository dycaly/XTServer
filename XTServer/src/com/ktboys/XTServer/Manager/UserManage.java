package com.ktboys.XTServer.Manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jgroups.protocols.MFC;
import org.jgroups.protocols.UFC;

import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.User;
import com.ktboys.XTServer.Entity.Userditals;
import com.ktboys.XTServer.Entity.Userfriend;

public class UserManage {

	private User mUser = null;
	private Session session;
	private Transaction transaction;
	private ArrayList<String> friends = new ArrayList<String>();

	public void close() {
		session.close();
	}

	public UserManage() {
		session = HibernateSessionFactory.getSession();
	}

	public UserManage(int Id) {
		session = HibernateSessionFactory.getSession();
		String hql = "from User u where u.userId=" + Id;

		Query query = session.createQuery(hql);
		mUser = (User) query.uniqueResult();
	}

	public UserManage(String token) {
		session = HibernateSessionFactory.getSession();
		String hql = "from User u where u.token='" + token + "'";

		Query query = session.createQuery(hql);
		mUser = (User) query.uniqueResult();
	}

	public UserManage(String username, String password) {
		session = HibernateSessionFactory.getSession();
		String hql = " from User u where u.username='" + username
				+ "'and u.password='" + password + "' ";
		Query query = session.createQuery(hql);
		mUser = (User) query.uniqueResult();

	}

	public boolean isExist() {
		return (mUser != null);
	}

	public void registUser(String username, String password) {

		String hqlSame = " from User u where u.username = '" + username + "' ";
		Query query = session.createQuery(hqlSame);
		User user = (User) query.uniqueResult();
		if (user == null) {
			transaction = session.beginTransaction();
			String hqlId = " select u.userId from User u where u.userId >=ALL(select u.userId from u)";
			Query queryId = session.createQuery(hqlId);
			int id = (int) queryId.uniqueResult();
			id++;
			mUser = new User(id, username, password, username + "_" + password,
					0);
			session.save(mUser);
			Userditals ud = new Userditals();
			ud.setUser(mUser);
			ud.setUserId(id);
			ud.setNickname(username);
			ud.setRegdate(new Timestamp(System.currentTimeMillis()));
			session.save(ud);
			transaction.commit();

		}
	}

	public void updatePassword(String password) {
		if (mUser == null) {
			return;
		}
		transaction = session.beginTransaction();
		mUser.setPassword(password);
		mUser.setToken(mUser.getUsername() + "_" + password);
		session.update(mUser);
		transaction.commit();
	}

	public String getToken() {
		if (mUser != null) {
			return mUser.getToken();
		} else {
			return null;
		}

	}

	public String getUserName() {
		if (mUser != null) {
			return mUser.getUsername();
		} else {
			return null;
		}
	}

	public int getUserId() {
		if (mUser != null) {
			return mUser.getUserId();
		} else {
			return -1;
		}
	}

	public int addFriend(String username) {

		if (mUser != null) {
			transaction = session.beginTransaction();

			String hql = " from User u where u.username='" + username + "'";
			Query query = session.createQuery(hql);
			User user = (User) query.uniqueResult();

			String hqlId = " select uf.userfriend from Userfriend uf where uf.userfriend >=ALL(select uf.userfriend from uf)";
			Query queryId = session.createQuery(hqlId);
			int id = (int) queryId.uniqueResult();
			id++;
			Userfriend uf = new Userfriend(id, mUser, user);

			session.save(uf);
			transaction.commit();
		}
		return 1;
	}

	public ArrayList<String> getFriends() {

		friends.clear();

		String hql = " select uf.userByOwnedId from Userfriend uf where uf.userByOwnerId in (from User u where u.userId="
				+ mUser.getUserId()+")";
		Query query = session.createQuery(hql);
		List<User> users = query.list();
		for (User ur : users) {
			String hqlNickName = "select ud.nickname from Userditals ud where ud.userId = "
					+ ur.getUserId();
			Query nnQuery = session.createQuery(hqlNickName);
			friends.add((String)nnQuery.uniqueResult());
		}

		return friends;
	}
}
