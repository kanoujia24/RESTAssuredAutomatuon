package ChainingPackage;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;
public class UpdateUser {
	
	@Test
	void testUpdateUser(ITestContext context) throws JSONException {
		String bearertoken="6020a95a4959783dee5f96ecfff0975da7520f3dfadd720872bd1f2c4f79f3d3";
		//int id=(Integer) context.getAttribute("user_id");
		int id=(Integer) context.getSuite().getAttribute("user_id");
		Faker fr=new Faker();
		JSONObject jo=new JSONObject();
		jo.put("name", fr.name().fullName());
		jo.put("email", fr.internet().emailAddress());
		jo.put("status", "active");
		
		given()
		   .headers("Authorization","Bearer "+bearertoken)
		   .pathParam("id", id)
		   .body(jo.toString())
		   .contentType("application/json")
		 .when()
		    .put("https://gorest.co.in/public/v2/users/{id}")
		  .then()
		    .statusCode(200)
		    .log().all();
	}

}
