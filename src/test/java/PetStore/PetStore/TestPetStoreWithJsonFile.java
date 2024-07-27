package PetStore.PetStore;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestPetStoreWithJsonFile {

	
	String username = "Himanshu";
	String newUser = "Sushmita";
	
	
	@Test(priority = 1)
	public void createNewUser() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response response = RestAssured.given().log().all()
			.contentType("application/json")
			.body(new File("./resources/payload1.json"))
			.post("/user")
			.then()
			.log().all().extract().response();
		
		
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);

		
	}
	
	@Test(priority = 2)
	public void getUserDetails() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
	
		Response response = RestAssured.given().log().all()
				.pathParam("usertoSearch", "Himanshu")
		.contentType("application/json")
		.get("/user/{usertoSearch}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		String actualUserName = response.jsonPath().getString("username");
		Assert.assertEquals(actualUserName, username);
	}
	
	@Test(priority = 3)
	public void updateUser() {
		
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response response = RestAssured.given().log().all()
				.pathParam("username", username)
		.contentType("application/json")
		.body(new File("./resources/payload2.json"))
		.put("/user/{username}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);

	}
	
	@Test(priority = 4)
	public void deleteUser() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response response = RestAssured.given().log().all()
				.pathParam("username", newUser)
		.contentType("application/json")
		.delete("/user/{username}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
	}
}
