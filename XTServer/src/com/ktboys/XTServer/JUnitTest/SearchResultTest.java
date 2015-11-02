package com.ktboys.XTServer.JUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ktboys.XTServer.Manager.SearchUserManage;

public class SearchResultTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		SearchUserManage sum = new SearchUserManage("¶­Ôª²Æ", 1);
		System.out.println(sum.getJson());
	}

}
