package practise;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestAssuredTest {

	@DataProvider(name= "testData")
	public Object[] getData() {
		
		return new Object[] {
			"Ram","Dhanush"
		};
		
	}
	
	
	@Test(enabled = true, dataProvider = "testData")
	public void getRequest(String name) {

		RestAssured.baseURI = "http://localhost:3000/";

		given().queryParam("name", name).when().get("ibmbatch").then().log().all();
	}

	@Test(enabled = true)
	public void getRequest1() {
		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = given().queryParam("id", "4").get("ibmbatch");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println("Response: "+resp.asPrettyString());
	}

	@Test(enabled = true)
	public void getRequest2() {
		RestAssured.baseURI = "http://localhost:3000/";

		RequestSpecification req = RestAssured.given();

		Response resp = req.request(Method.GET, "ibmbatch");

		Assert.assertEquals(resp.statusCode(), 200);

		System.out.println(resp.body().jsonPath().getList("name"));

	}

	@Test(enabled = true)
	public void postRequest() {

		RestAssured.baseURI = "http://localhost:3000/";

		String reqBody = "{\r\n" + "        \"name\": \"Abhira\",\r\n" 
		        + "        \"address\": \"Rabeli\",\r\n"
				+ "        \"city\": \"Mumbai\"\r\n" 
		        + "    }";

		Response resp = RestAssured.given().header("Content-Type", "application/json").body(reqBody)
				.post("ibmbatch");

		Assert.assertEquals(resp.statusCode(), 201);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
	}
	

	@Test(enabled = true)
	public void putRequest() {
		RestAssured.baseURI = "http://localhost:3000/";

		String reqBody = "{\r\n" 
				+ "        \"name\": \"Sanjay\",\r\n" 	
				+ "        \"address\": \"Ghansoli\",\r\n"
				+ "        \"city\": \"Mumbai\"\r\n" 
				+ "    }";

		Response resp = RestAssured.given().header("Content-Type", "application/json").body(reqBody)
				.put("ibmbatch/4");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void patchRequest() {
		RestAssured.baseURI = "http://localhost:3000/";

		JSONObject jsObj = new JSONObject();
		jsObj.put("name", "Swamy");
		

		Response resp = RestAssured.given().header("Content-Type", "application/json").body(jsObj.toJSONString())
				.patch("ibmbatch/4");

		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
		System.out.println("**************************************");
		System.out.println("Resp Body: " + resp.body().asPrettyString());
	}
	
	
	@Test(enabled = true)
	public void deleteRequest() {
		RestAssured.baseURI = "http://localhost:3000/";

		Response resp = RestAssured.given()
		.when()
		.delete("ibmbatch/5");
			
		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("**************************************");
		System.out.println("Status code: " + resp.statusCode());
		System.out.println("Status line: " + resp.statusLine());
	}
	
	
}
