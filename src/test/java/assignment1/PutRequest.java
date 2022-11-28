package assignment1;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutRequest {

	
	@Test(enabled = true)
	public void testPutRequestByFetchingDataFromHashMap() {

		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = RestAssured.given()
								.header("Content-Type", "application/json")
								.body(PutRequest.getMap())
								.put("Books/5");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
	}
	
	
	@Test(enabled = true)
	public void testPutRequest() {
		RestAssured.baseURI = "http://localhost:3000/";

		String reqBody = "{\r\n"
				+ "       \"author\": \"Ranjith\",\r\n"
				+ "        \"title\": \"Book5\",\r\n"
				+ "        \"isAvailable\": \"No\"\r\n"
				+ "}";
		

		Response resp = RestAssured.given()
								.header("Content-Type", "application/json")
								.body(reqBody)
								.log().all()
								.put("Books/5");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
	}
	
	public static HashMap<String, String> getMap() {
		 HashMap<String, String> map= new HashMap<>();
		  map.put("author", "Brinda");
		  map.put("title", "SeleniumCodeCamp");
		  map.put("isAvailable", "Yes");
		return map;
	}
}
