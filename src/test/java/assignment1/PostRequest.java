package assignment1;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ReadExcelData;

public class PostRequest {

	String excelPath = System.getProperty("user.dir")+"/TestData.xlsx";
	
	@Test(enabled = true)
	public void testPostRequest() {

		RestAssured.baseURI = "http://localhost:3000/";

		String reqBody = " {\r\n"
				+ "       \"author\" : \"Dhanush\",\r\n"
				+ "	   \"title\" : \"Book5\",\r\n"
				+ "	 \"isAvailable\": \"Yes\"\r\n"
				+ "}";

		Response resp = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(reqBody)
				.post("Books");

		Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
	}
	
	@Test(enabled = true)
	public void testPostRequestByFetchingDataFromJSONfile() {

		RestAssured.baseURI = "http://localhost:3000/";

		String jsonFilePath = System.getProperty("user.dir")+"/TestDataBooks.json";

		Response resp = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(new File(jsonFilePath))
				.post("Books");

		Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
	}
	
	@Test(enabled = true)
	public void testPostRequestByFetchingDataFromExcel() {

		RestAssured.baseURI = "http://localhost:3000/";

		ReadExcelData readExcel = new ReadExcelData(excelPath);
		HashMap<String, String> mapObj = readExcel.getMap();
		
		Response resp = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(mapObj)
				.post("Books");

		Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
		JsonPath jsp = resp.getBody().jsonPath();
		
		Assert.assertEquals(jsp.getString("author"), mapObj.get("author") );
		Assert.assertEquals(jsp.getString("title"), mapObj.get("title") );
		Assert.assertEquals(jsp.getString("isAvailable"), mapObj.get("isAvailable") );
	}
	
	
	
}
