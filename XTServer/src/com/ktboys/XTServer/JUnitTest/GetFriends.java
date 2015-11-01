package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.Manager.UserManage;

public class GetFriends {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UserManage um = new UserManage(1);
		ArrayList<String> friends=  um.getFriends();
		
		System.out.println("----start----"+friends.size()+"--");
		for(String str :friends){
			System.out.println(str);
		}
		System.out.println("----end----");
		um.close();
	}
	

}
