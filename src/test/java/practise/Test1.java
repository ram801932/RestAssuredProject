package practise;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test1 {

	@Test
	public void getMethod() {
		Response response = RestAssured.get("http://localhost:3000/ibmbatch");
		
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void deleteMethod() {
		Response response = RestAssured.delete("http://localhost:3000/ibmbatch/3");
		
		System.out.println(response.asPrettyString());
	}
	
	
	@Test
	public void testDirectory() {

		RestAssured.baseURI = "http://localhost:3000/";
	
		//String jsonFilePath = "./TestData.json";
		String jsonFilePath = System.getProperty("user.dir")+"\\TestData.json";
		
       //POST request
		Response resp = RestAssured.given().header("Content-Type", "application/json")
				.body(new File(jsonFilePath))
				.log().all()
				.post("ibmbatch");

		//Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		System.out.println("****************************************");
	}
	

}
