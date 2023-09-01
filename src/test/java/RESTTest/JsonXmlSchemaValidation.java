package RESTTest;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonXmlSchemaValidation {
	//@Test
	
	void testJsonSchema() {
		
		when().get("https://dummy.restapiexample.com/api/v1/employees")
		.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema"));
		
	}
	
	
	@Test
	void testXmlSchema() {
		when().get("http://restapi.adequateshop.com/api/Traveler")
		.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema"));
	}
	
	
	
	

}
