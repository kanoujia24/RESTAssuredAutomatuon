package RESTTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class pathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testPathAndQueryParameters() {
		given()
		  .pathParam("MyPath","users") //pathparameter
		  .queryParam("page", 2)
		  .queryParam("id", 5)
		.when()
		   .get("https://reqres.in/api/{MyPath}")
		 .then()
		    .statusCode(200)
		    .log().all();
			
		
		
	}

}
