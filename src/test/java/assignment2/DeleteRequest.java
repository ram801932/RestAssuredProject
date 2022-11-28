package assignment2;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteRequest {

	@Test(enabled = true)
	public void testGetRequest() {

		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = given().delete("greenBasket/2");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		System.out.println("**************************************");
		
	}
	
}
