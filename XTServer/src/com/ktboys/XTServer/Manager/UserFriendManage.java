package com.ktboys.XTServer.Manager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Userfriend;

public class UserFriendManage {

	private Userfriend mUF = null;
	private Session session;
	private Transaction transaction;

	public UserFriendManage(int id) {
		session = HibernateSessionFactory.getSession();

		String hql = " from Userfriend uf where uf.userId=" + id;
		Query query = session.createQuery(hql);
		mUF = (Userfriend) query.uniqueResult();
	}
	
	public boolean isExist(){
		return (mUF != null);
	}
	public int AddFriend(String username){
		
		return 0;
	}
}
