package assignment1;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest {

	@DataProvider(name = "testData")
	public Object[] getData() {

		return new Object[] { 1, 2, 3, 4 };

	}

	@Test(enabled = true, dataProvider = "testData")
	public void testGetRequestByid(int id) {

		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = given().queryParam("id", id).get("Books");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		System.out.println("**************************************");
	}
	
	@Test(enabled = true)
	public void testGetRequest() {

		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = given().get("Books");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		System.out.println("**************************************");
		
		//get no.of books
		System.out.println("No.of Books: "+resp.body().jsonPath().getList("author").size());
	}

}
