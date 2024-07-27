package PetStore.PetStore;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	private final String baseUri = "https://petstore.swagger.io/v2";
	RequestSpecification requestSpecification;
	ResponseSpecification response;
	
	@BeforeClass
	public void setup() {
		
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setContentType("application/json")
				.log(LogDetail.ALL)
				.build();
		
		response = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.log(LogDetail.ALL)
				.build();
		
	}
	

}
