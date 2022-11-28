package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseCase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_All_Employees extends BaseCase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("*********Started TC001_Get_All_Employuees*********");
		
		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		Response response = httpRequest.request(Method.GET, "employees");
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("********* Check Response Body *********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		Assert.assertTrue(responseBody != null);
	}
	
	@Test
	void checkStatusCode() {
		logger.info("********* Check Status Code *********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("********* Check Response Time *********");
		
		long responseTime = response.getTime();
		logger.info("Response Time: " + responseTime);
		
		if(responseTime > 2000) {
			logger.warn("Response Time is greater than 2000");
		}
		Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********* Check Status Line *********");
		String statusLine = response.statusLine();
		logger.info("Status Line: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("********* Check Content Type *********");
		String contentType = response.header("Content-Type");
		logger.info("Content Type: " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");	
	}
	
	@Test
	void checkServerType() {
		logger.info("********* Check Server Type *********");
		String serverType = response.header("Server");
		logger.info("Server Type: " + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");	
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("********* Check Content Encoding *********");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding: " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");	
	}
	
	@Test
	void checkContentLength() {
		logger.info("********* Check Content Length *********");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length: " + contentLength);
		if(Integer.parseInt(contentLength)<100) {
			logger.warn("Content Length is less than 100");
		}
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookies() {
		logger.info("********* Check Cookies *********");
		String cookie = response.getCookie("PHPSESSID");
		//Assert.assertEquals(cookie, "1esuvsfslcmiee2bfrsgnijtg0");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********* Finish TC001_Get_All_Employees *********");
	}
	
}
