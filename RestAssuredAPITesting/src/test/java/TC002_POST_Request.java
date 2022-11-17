import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {
	
	@Test
	void postUser() {
		
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";
				
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
				
		// Request parameter with post
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "Jay");
		requestParams.put("job", "QA Engineer");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		// Response object
		Response response = httpRequest.request(Method.POST, "/api/users");
				
		// Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);
				
		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		
		String responseName = response.jsonPath().get("name");
		Assert.assertEquals(responseName, "Jay");
	}
}
