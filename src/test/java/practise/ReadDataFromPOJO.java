package practise;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.POJOibmBatch;

public class ReadDataFromPOJO {

	@Test(enabled = true)
	public void postRequest() throws JsonProcessingException {

		RestAssured.baseURI = "http://localhost:3000/";
	
		//Reading data from POJO class -- serialization
		POJOibmBatch pojo = new POJOibmBatch();
		pojo.setName("Karna");
		pojo.setAddress("Shaniwar Wada");
		pojo.setCity("Pune");
		
//		ObjectMapper objMapper = new ObjectMapper();
//		String obj = objMapper.writeValueAsString(pojo);
		
       //POST request
		Response resp = RestAssured.given().header("Content-Type", "application/json")
				.body(pojo)
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
