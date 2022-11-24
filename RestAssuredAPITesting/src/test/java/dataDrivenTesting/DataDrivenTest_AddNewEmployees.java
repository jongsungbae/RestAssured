package dataDrivenTesting;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	
	@Test(dataProvider="empDataProvider")
	void postAddNewEmployees(String ename, String esalary, String eage) {
		
		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Request parameter with post
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", ename);
		requestParams.put("salary", esalary);
		requestParams.put("age", eage);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		// Post Request
		Response response = httpRequest.request(Method.POST, "/create");
		
		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("validation status code");
		
		// validation Body
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esalary), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		System.out.println("validation body");
		
		// validation jasonPath
//		JsonPath jsonPath = response.jsonPath();
//		
//		String jsonStatus = jsonPath.get("status");
//		Assert.assertEquals(jsonStatus, "success");
//		System.out.println("validation status");
//		
//		String jsonDataName = jsonPath.getString("data.name");
//		Assert.assertEquals(jsonDataName, ename);
//		System.out.println("validation name");
//		
//		String jsonDataSalary = jsonPath.getString("data.salary");
//		Assert.assertEquals(jsonDataSalary, esalary);
//		System.out.println("validation salary");
//		
//		String jsonDataAge = jsonPath.getString("data.age");
//		Assert.assertEquals(jsonDataAge, eage);
//		System.out.println("validation age");
//		
//		String jsonMessage = jsonPath.getString("message");
//		Assert.assertEquals(jsonMessage.contains("Successfully"), true);
	}
	
	@DataProvider(name="empDataProvider")
	String [][] getEmpData() throws IOException{
		
		// Hardcoding for data
//		String empdata[][] = {
//			{"abc123", "3000", "21"},
//			{"abc124", "3000", "22"},
//			{"abc125", "3500", "23"}
//		};
		
		// Read Excel file
		String path = System.getProperty("user.dir") + "/src/test/java/dataDrivenTesting/empdata.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String empdata [][] = new String[rownum][colcount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				empdata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return(empdata);
	};

}
