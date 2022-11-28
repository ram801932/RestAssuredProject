package assignment2;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ReadExcelData;

public class PostRequest {

	
	String jsonFilePath = System.getProperty("user.dir")+"/TestDataGreenBasket.json";
	String baseURI = "http://localhost:3000/";
	
	@Test(enabled = true)
	public void testPostRequestByFetchingDataFromJSONfile() {

		RestAssured.baseURI = baseURI;
		
		Response resp = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(new File(jsonFilePath))
				.post("greenBasket");

		Assert.assertEquals(resp.statusCode(), 201);
		
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
			
		
	}
	
	
	
}
