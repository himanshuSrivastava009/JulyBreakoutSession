package PetStore.PetStore;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestPetStoreUsingPojo {

	
	//String username = "Himanshu";
	//String newUser = "Sushmita";
	
	/***
	 * {
"id": 786,
"username": "Mahesh",
"firstName": "Mukesh",
"lastName": "KOtwani",
"email": "mukesh@gmail.com",
"password": "mukesh@123",
"phone": "9090909090",
"userStatus": 1
}
	 */
	PetStorePojo pojo = new PetStorePojo(101,"Himanshu","Kumar","srivastava","himanshukumar9210@gmail.com","xyz123","0987654876",1);
	
	@Test(priority = 1)
	public void createNewUser() {
 
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response response = RestAssured.given().log().all()
			.contentType("application/json")
			.body(pojo)
			.post("/user")
			.then()
			.log().all().extract().response();
		
		System.out.println("Pojo Username =" +pojo.getUsername());
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);

		
	}
	
	@Test(priority = 2)
	public void getUserDetails() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
	
		Response response = RestAssured.given().log().all()
				.pathParam("usertoSearch", pojo.getUsername())
		.contentType("application/json")
		.get("/user/{usertoSearch}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		String actualUserName = response.jsonPath().getString("username");
		Assert.assertEquals(actualUserName, pojo.getUsername());
	}
	
	@Test(priority = 3)
	public void updateUser() {
		
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		pojo.setUsername("Sushmita");
		Response response = RestAssured.given().log().all()
				.pathParam("username", pojo.getUsername())
		.contentType("application/json")
		.body(pojo)
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
				.pathParam("username", pojo.getUsername())
		.contentType("application/json")
		.delete("/user/{username}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
	}
	
}
