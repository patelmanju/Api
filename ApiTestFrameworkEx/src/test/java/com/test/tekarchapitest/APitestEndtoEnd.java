package com.test.tekarchapitest;

import org.testng.annotations.Test;
import com.test.helpers.userhelper;

public class APitestEndtoEnd extends userhelper {

	@Test(priority = 1, enabled = true)
	public static void Testcase1() {
		getdata();
		
	}
	@Test(priority = 2, enabled = true)
	public static void Testcase2() {
		createUSerDetails();	
		
	}
	@Test(priority = 3, enabled = true)
	public static void Testcase3() {
		deleteUserInfo();
		
	}
	@Test(priority = 4, enabled = true)
	public static void Testcase4() {
		 deleteUserInfo1();
		
	}
	@Test(priority = 5, enabled = true)
	public static void Testcase5() {
		 getUserDetails();
		
	}
	
	
	
}