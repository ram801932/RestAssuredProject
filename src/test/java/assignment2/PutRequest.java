package assignment2;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutRequest {

	
	
	@Test(enabled = true)
	public void testPutRequest() {
		
		RestAssured.baseURI = "http://localhost:3000/";

		String[] fruits =new String[] {"apricot","papaya"};
		String orderedBy = "Customer02";
		
		String reqBody = "{\r\n"
				+ "	\"food\": {\r\n"
				+ "		\"fruits\": [\r\n"
				+ "			\""+fruits[0]+"\",\r\n"
				+ "			\""+fruits[1]+"\"\r\n"
				+ "		],\r\n"
				+ "		\"vegetables\": [\r\n"
				+ "			{\r\n"
				+ "				\"veggieName\": \"peas\",\r\n"
				+ "				\"veggieLike\": true\r\n"
				+ "			},\r\n"
				+ "			{\r\n"
				+ "				\"veggieName\": \"broccoli\",\r\n"
				+ "				\"veggieLike\": false\r\n"
				+ "			}\r\n"
				+ "		]\r\n"
				+ "	},\r\n"
				+ "	\"basketOrderedby\": \""+orderedBy+"\"\r\n"
				+ "}";
		

		Response resp = RestAssured.given()
								.header("Content-Type", "application/json")
								.body(reqBody)
								.log().all()
								.put("greenBasket/2");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
		 JsonPath jsp = resp.getBody().jsonPath();
		Assert.assertEquals(jsp.getString("basketOrderedby"), orderedBy );
		Assert.assertEquals(jsp.getString("food.fruits[0]"), fruits[0]);
		Assert.assertEquals(jsp.getString("food.fruits[1]"), fruits[1] );
		
	}
	
	
}
