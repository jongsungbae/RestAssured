package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseCase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends BaseCase {
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("*********Started TC002_Get_Single_Employee*********");
		
		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		response = httpRequest.request(Method.GET, "employee/" + empID);

		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
		logger.info("check Response Body");
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		logger.info("check Status Code");
	}
	
	@Test
	void checkResponseTime() {
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 2000);
		logger.info("check Response Time");
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
	void checkServerType() {
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.21.6");	
		logger.info("check server type");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********* Finish TC002_Get_Single_Employee *********");
	}
	
	

}
