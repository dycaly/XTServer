package com.ktboys.XTServer.Manager;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Userditals;
import com.ktboys.XTServer.Result.SearchUserResult;
import com.ktboys.XTServer.Result.UserNick_Name;

public class SearchUserManage {

	private ArrayList<Userditals> userditals;
	private String name;
	private Session session;
	
	public SearchUserManage(String name,int method) {
		session = HibernateSessionFactory.getSession();
		this.name = name;
		userditals=new ArrayList<Userditals>();
		String hql;
		if (method == 0) {
			hql = "from Userditals ud where ud.nickname='"+name+"'";
		}
		else {
			hql = "from Userditals ud where ud.name='"+name+"'";
		}
		Query query = session.createQuery(hql);
		userditals = (ArrayList<Userditals>) query.list();
	}
	
	public String getJson(){
		String json;
		SearchUserResult sur=new SearchUserResult(0);
		
		for (Userditals ud :userditals) {
			sur.addItem(new UserNick_Name(ud.getUser().getUsername(), ud.getNickname(),ud.getName()));
		}
		Gson gson = new Gson();
		json = gson.toJson(sur);
		return json;
	}
	public void close(){
		session.close();
	}
	
}
