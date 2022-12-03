package com.employeeapi.testCases;

import com.employeeapi.base.BaseCase;
import com.employeeapi.utilities.RestUtils;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends BaseCase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException {
		logger.info("*********Started TC003_Update_Employee*********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", empName);
		requestParams.put("job", empSalary);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		// Response object
		response = httpRequest.request(Method.PUT, "/update/" + empID);
		
		Thread.sleep(3);
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		logger.info("check Status Code");
	}
	
	@Test
	void checkStatusLine() {
		String statusLine = response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		logger.info("check status line");
	}
	
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");	
		logger.info("check content type");
	}
	
	@Test
	void checkResponseEmpInfo() {
		
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		
		Assert.assertEquals(responseBody.contains(empName), true);
		logger.info("check Response name");
		
		Assert.assertEquals(responseBody.contains(empSalary), true);
		logger.info("check Response salary");
		
		Assert.assertEquals(responseBody.contains(empAge), true);
		logger.info("check Response age");
		
	}

	@AfterClass
	void tearDown() {
		logger.info("********* Finish TC004_Update_Employee *********");
	}
	

}
