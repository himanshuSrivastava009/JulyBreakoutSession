package PetStore.PetStore;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestPetStore {

	String username = "Himanshu";
	String newUser = "Mohit";
	String payload = "{\n"
            + "\"id\": 786,\n"
            + "\"username\": \""+username +"\",\n"
            + "\"firstName\": \"Mukesh\",\n"
            + "\"lastName\": \"Otwani\",\n"
            + "\"email\": \"mukesh@gmail.com\",\n"
            + "\"password\": \"mukesh@123\",\n"
            + "\"phone\": \"9090909090\",\n"
            + "\"userStatus\": 1\n"
            + "}";
	
	@Test(priority = 1)
	public void createNewUser() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response response = RestAssured.given().log().all()
			.contentType("application/json")
			.body(payload)
			.post("/user")
			.then()
			.log().all().extract().response();
		
		
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		
		//JsonPath path = new JsonPath(response.toString());
		//username = response.jsonPath().getString("username");
		//System.out.println("username is = " +username);
		
	}
	
	@Test(priority = 2)
	public void getUserDetails() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		//String enpointPoint = "/user/"+username;
		Response response = RestAssured.given().log().all()
				.pathParam("username", username)
		.contentType("application/json")
		.get("/user/{username}")
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
		//String enpointPoint = "/user/"+username;
		Response response = RestAssured.given().log().all()
				.pathParam("username", username)
		.contentType("application/json")
		.body("{\n"
				+ "\"id\": 786,\n"
				+ "\"username\": \""+newUser+"\",\n"
				+ "\"firstName\": \"Mukesh\",\n"
				+ "\"lastName\": \"KOtwani\",\n"
				+ "\"email\": \"mukesh@gmail.com\",\n"
				+ "\"password\": \"mukesh@123\",\n"
				+ "\"phone\": \"9090909090\",\n"
				+ "\"userStatus\": 1\n"
				+ "}")
		.put("/user/{username}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		//String actualUserName = response.jsonPath().getString("username");
		//Assert.assertEquals(actualUserName, newUser);
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
