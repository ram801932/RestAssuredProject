package assignment2;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PatchRequest {


	@Test(enabled = true)
	public void testPatchRequest() {
		
		RestAssured.baseURI = "http://localhost:3000/";
		
		JSONObject jsObj = new JSONObject();
		jsObj.put("basketOrderedby", "Customer99");

		Response resp = RestAssured.given()
								.header("Content-Type", "application/json")
								.body(jsObj)
								.log().all()
								.patch("greenBasket/2");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
		
		 JsonPath jsp = resp.getBody().jsonPath();
		Assert.assertEquals(jsp.getString("basketOrderedby"), jsObj.get("basketOrderedby") );
	}
}
