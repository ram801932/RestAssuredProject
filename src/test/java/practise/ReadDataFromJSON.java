package practise;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReadDataFromJSON {

	@Test(enabled = true)
	public void postRequest() {

		RestAssured.baseURI = "http://localhost:3000/";
	
       //POST request
		Response resp = RestAssured.given().header("Content-Type", "application/json")
				.body(new File("./TestData.json"))
				.log().all()
				.post("ibmbatch");

		//Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
		//GET request
		given().get("ibmbatch").then().log().all();
	}
	
	
}
