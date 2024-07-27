package PetStore.PetStore;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestPetStoreUsingSpecBuilder extends SpecBuilder {

	PetStorePojo pojo = new PetStorePojo(101, "Himanshu", "Kumar", "srivastava", "himanshukumar9210@gmail.com",
			"xyz123", "0987654876", 1);

	@Test(priority = 1)
	public void createNewUser() {

		RestAssured
				.given(requestSpecification)
				.body(pojo)
				.post("/user")
				.then()
				.spec(response);

	}

	@Test(priority = 2)
	public void getUserDetails() {

		

		RestAssured.given(requestSpecification)
				.pathParam("usertoSearch", pojo.getUsername())
				.contentType("application/json").get("/user/{usertoSearch}")
				.then()
				.spec(response);
		
	}

	@Test(priority = 3)
	public void updateUser() {

		pojo.setUsername("Sushmita");
		RestAssured.given(requestSpecification).pathParam("username", pojo.getUsername())
				.body(pojo).put("/user/{username}")
				.then()
				.spec(response);

	}

	@Test(priority = 4)
	public void deleteUser() {

		RestAssured.given(requestSpecification)
		.pathParam("username", pojo.getUsername())
				.delete("/user/{username}")
				.then()
				.spec(response);
		
	}
}
