package com.test.helpers;

import java.lang.reflect.Method;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.utils.CommonUtility;
import com.test.utils.GenerateReports;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userhelper {

	public static String token1;
	public static GenerateReports report = null;

	@BeforeTest
	public static void setupBeforeTest() {
		report = GenerateReports.getInstance();
		report.startExtentReport();
	}

	@AfterTest
	public static void teardownAfterTest() {
		report.endReport();
	}

	@BeforeClass
	public static void setup() {
		urisetup();
	}

	@BeforeMethod
	public static void startreport(Method m) {
		report.startSingleTestReport(m.getName());
	}

	public static void urisetup() {
		CommonUtility CU = new CommonUtility();
		Properties sfDataFile = CU.loadFile("sfData");
		String url = CU.getApplicationProperty("BaseURI", sfDataFile);
		RestAssured.baseURI = url;
	}

	

	public static void getdata() {

	
		Response res = RestAssured.given().when().get("/employees");
		
		res.then().statusCode(200);
		
		res.then().body("status", Matchers.equalTo("success"));
		
		System.out.println("number of records=" + res.jsonPath().get("size()"));
		res.prettyPrint();

		

	}
public static void createUSerDetails() {
				
		Response res = RestAssured.given().contentType(ContentType.JSON).body
		("{\"id\":25,\"employee_name\":\"test\",\"employee_salary\":123,\"employee_age\":\"23\",\"profile_image\":\"\"}")
		
			.when().post("/create"); 
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status", Matchers.equalTo("success"));
		res.then().body("employee_name",Matchers.equalTo("test") );
		res.then().body("employee_salary",Matchers.equalTo("123") );
		res.then().body("employee_age",Matchers.equalTo("23") );
		res.then().body("id",Matchers.equalTo("25") );
		
		int id = res.jsonPath().get("[0].data");
		System.out.println("id=" + id);

	}
public static void deleteUserInfo() {
		
	Response res = RestAssured.given().contentType(ContentType.JSON)
			.body("{\"id\":\"9196\"}").when()
			.delete("/delete/9196");
	res.then().statusCode(200);
	res.then().contentType(ContentType.JSON);
	res.then().body("status", Matchers.equalTo("success"));
	

}
public static void deleteUserInfo1() {
	
	
	Response res = RestAssured.given().contentType(ContentType.JSON)
			.body("{\"id\":\"0\"}").when()
			.delete("/delete/0");
	res.then().statusCode(400);
	
}

@Test(enabled = true)
public static void getUserDetails() {
	
	Response res = RestAssured.given().when().get("/employee/2");

	res.then().statusCode(200);
	res.then().contentType(ContentType.JSON);
	res.prettyPrint();
	res.then().body("employee_name",Matchers.equalTo("Garrett Winters"));
	res.then().body("employee_salary",Matchers.equalTo("170750"));
	res.then().body("employee_age",Matchers.equalTo("63"));
	
					
	
}
}
