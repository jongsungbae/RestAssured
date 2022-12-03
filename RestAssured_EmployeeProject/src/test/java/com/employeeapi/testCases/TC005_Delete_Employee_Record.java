package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseCase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends BaseCase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("*********Started TC005_Delete_Employee*********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		JsonPath jsonPath = response.jsonPath();
		
		// Capture id
		String empID = jsonPath.get("[0].id");
 		response = httpRequest.request(Method.DELETE, "/delete/"+ empID);
 		
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
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		Assert.assertEquals(responseBody.contains("deleted Records"), true);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********* Finish TC005_Delete_Employee *********");
	}

}
