import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	
	@Test
	void getUserDetails() {
		
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		Response response = httpRequest.request(Method.GET, "/api/users?page=2");
		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		int responsePage = response.jsonPath().get("page");
		Assert.assertEquals(responsePage, 2);
		
		int responseFirstId = response.jsonPath().get("data[0].id");
		Assert.assertEquals(responseFirstId, 7);
		
		// Capture details of headers from response
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}

}
