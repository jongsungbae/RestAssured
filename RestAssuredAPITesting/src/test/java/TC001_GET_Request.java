
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	
	@Test
	void getUserDetails() {
		
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		Response response = httpRequest.request(Method.GET, "/api/users/2");
		
		// Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);
		
		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		// status line verification
		String statusLine = response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
}
