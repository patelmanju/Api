package com.test.tekarchapitest;

import java.util.Properties;

import javax.xml.crypto.Data;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.endpoints;
import com.test.models.createuserrequestpojo;
import com.test.models.getuserresponsepojo;
import com.test.models.loginrequestpojo;
import com.test.models.loginresponsepojo;
import com.test.utils.CommonUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class SimpleCRUD {

	@BeforeClass
	public static void setUp() {
		CommonUtility CU = new CommonUtility();
		Properties sfDataFile = CU.loadFile("sfData");
		String url = CU.getApplicationProperty("BaseURI", sfDataFile);
		RestAssured.baseURI = url;
	}
@Test

	public static void TC2getUserDetails() {

		//token1 = login();
		//Header header = new Header("token", token1);

		Response res = RestAssured.given()
				
				.when()
				.get(endpoints.GET_DATA);

		res.then().statusCode(200);
		res.then().body("status", Matchers.equalTo("success"));
		//report.logTestInfo("data details are:");
		getuserresponsepojo[] getdata = res.as(getuserresponsepojo[].class);
		String id= getdata[0].getId();
		System.out.println("id===="+ id );

		 
		

	}
	
/*Testcase 5
resouce endpoint = /employee/{id}
get the details of user whose id is 2
Http method=get
validate fallowing
status code is 200
content type is json
employee name is Garrett Winters
employee salary is 170750
employee age is 63
fetch the message data and print it to console*/




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
	
	
	@Test(enabled = true)
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
		//System.out.println("id=" + id);

	}
	@Test
	
	public static void TC3createUSerDetails() {
		//token1 = login();
		//Header header = new Header("token", token1);
		CommonUtility CU = new CommonUtility();
		Properties addDataFile = CU.loadFile("addData");
		createuserrequestpojo userdata = new createuserrequestpojo();
		//userdata.setId(addDataFile.getProperty("addid"));
		userdata.setName(addDataFile.getProperty("name"));
		userdata.setSalary(addDataFile.getProperty("salary"));
		userdata.setAge(addDataFile.getProperty("age"));

		Response res = RestAssured.given().contentType(ContentType.JSON)

				.body(userdata).when().post(endpoints.ADD_DATA);

		System.out.println("user detail created");
		//report.logTestInfo("user detail created");

		res.then().statusCode(200);
		//res.then().body("status", Matchers.equalTo("success"));
		res.then().body("status", Matchers.equalTo("success"));
		res.then().body("employee_name",Matchers.equalTo("test") );
		res.then().body("employee_salary",Matchers.equalTo("123") );
		res.then().body("employee_age",Matchers.equalTo("23") );
		
		
		int id = res.jsonPath().get("[0].id");
		System.out.println("id=" + id);

	}

	@Test(enabled = false)
	public static void updateUSerDetails() {
		String token1 = login();
		Header header = new Header("token", token1);
		Response res = RestAssured.given().header(header).contentType(ContentType.JSON).body(
				"{\"accountno\":\"TA-Aug2202\",\"departmentno\":3,\"salary\":6000,\"pincode\":123123,\"userid\":\"zhFI4oQlHjP2cn3mW3GP\",\"id\":\"3alpp4U70XFMCIOSIbrf\"}")
				.when().put("updateData");

		res.then().statusCode(200);
		res.then().body("status", Matchers.equalTo("success"));

	}
	/*resouce endpoint = /delete/{id}
	get the id from create testcase 2
	Http method=delete
	sample response
	{
	"status": "",
	"data": "",
	"message": ""
	}

	validate for
	status code is 200
	response body status is "success"
	id which you have entered in the path parameter is same as in the response
	fetch the message data and print it to console*/

	@Test(enabled = true)
	public static void deleteUserInfo() {
	
		
		Response res = RestAssured.given().contentType(ContentType.JSON)
				.body("{\"id\":\"0\"}").when()
				.delete("/delete/0");
		res.then().statusCode(400);
		//res.then().contentType(ContentType.JSON);
		//res.then().body("status", Matchers.equalTo("success"));

	}
}










