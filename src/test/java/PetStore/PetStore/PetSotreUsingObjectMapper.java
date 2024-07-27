package PetStore.PetStore;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;



public class PetSotreUsingObjectMapper {

	ObjectMapper mapper = new ObjectMapper();

	
	
	@Test(priority = 1)
	public void createNewUser() throws StreamReadException, DatabindException, IOException {
		
		
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
	public void getUserDetails() throws StreamReadException, DatabindException, IOException {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		PetStorePojo value = mapper.readValue(new File("./resources/payload1.json"),PetStorePojo.class);
		Response response = RestAssured.given().log().all()
				.pathParam("usertoSearch", value.getUsername())
		.contentType("application/json")
		.get("/user/{usertoSearch}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		String actualUserName = response.jsonPath().getString("username");
		Assert.assertEquals(actualUserName, value.getUsername());
	}
	
	@Test(priority = 3)
	public void updateUser() throws StreamReadException, DatabindException, IOException {
		
		PetStorePojo value = mapper.readValue(new File("./resources/payload2.json"),PetStorePojo.class);
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response response = RestAssured.given().log().all()
				.pathParam("username", value.getUsername())
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
	public void deleteUser() throws StreamReadException, DatabindException, IOException {
		PetStorePojo value = mapper.readValue(new File("./resources/payload2.json"),PetStorePojo.class);
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response response = RestAssured.given().log().all()
				.pathParam("username", value.getUsername())
		.contentType("application/json")
		.delete("/user/{username}")
		.then()
		.log().all().extract().response();
		int code = response.statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
	}

}
