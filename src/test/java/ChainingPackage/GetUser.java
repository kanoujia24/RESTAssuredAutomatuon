package ChainingPackage;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class GetUser {
	@Test
	void testGetUser(ITestContext context) {
		//int id=(Integer) context.getAttribute("user_id");//for test level
		int id=(Integer) context.getSuite().getAttribute("user_id");//for suit level
		String bearertoken="6020a95a4959783dee5f96ecfff0975da7520f3dfadd720872bd1f2c4f79f3d3";
		given()
		  .headers("Authorization","Bearer "+bearertoken)
		  .pathParam("id",id)
		.when()
		  .get("https://gorest.co.in/public/v2/users/{id}")
		.then()
		  .statusCode(200)
		  .log().all();
	}

	

}
