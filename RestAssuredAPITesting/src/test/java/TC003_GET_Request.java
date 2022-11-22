import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
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
		
		// validation status
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		// Print response in console
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Lindsay"), true);
		
		// validation jasonPath
		JsonPath jsonPath = response.jsonPath();
		int responsePage = jsonPath.get("page");
		Assert.assertEquals(responsePage, 2);
		
		int responsePerPage = jsonPath.get("per_page");
		Assert.assertEquals(responsePerPage, 6);
		
		int responseFirstId = jsonPath.get("data[0].id");
		Assert.assertEquals(responseFirstId, 7);
		
		// Capture details of headers from response
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
		
		// Get all headers
		Headers allHeaders = response.headers(); // capture all the headers from response
		
		for(Header header:allHeaders) {
			System.out.println(header.getName() + "  " + header.getValue() );
		}
	}

}
