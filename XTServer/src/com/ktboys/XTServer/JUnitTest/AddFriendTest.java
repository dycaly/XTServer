package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.Manager.UserManage;

public class AddFriendTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int id =1;
		UserManage um = new UserManage(id);
		um.addFriend("bushiba");
		um.close();
	}

}
