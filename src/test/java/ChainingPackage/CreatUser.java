package ChainingPackage;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;
public class CreatUser {
	@Test
	
	void testCrestUser(ITestContext context) throws JSONException  {//ITestContext this is interface in testng here ituse to access id 
		//in next class/test when we run whole test through xml
		String bearertoken="6020a95a4959783dee5f96ecfff0975da7520f3dfadd720872bd1f2c4f79f3d3";
		Faker fr=new Faker();
		JSONObject jo=new JSONObject();
		jo.put("name", fr.name().fullName());
		jo.put("gender", "male");
		jo.put("email", fr.internet().emailAddress());
		jo.put("status", "inactive");
		
		
		int id=given()
		    .headers("authorization","Bearer "+bearertoken)
		    .body(jo.toString())
		    .contentType("application/json")
		.when()
		     .post("https://gorest.co.in/public/v2/users")
		      .jsonPath().getInt("id");
		System.out.println(id);
		      
		//context.setAttribute("user_id",id);// method which access variable inside test
		context.getSuite().setAttribute("user_id",id);
		
		
		
	}

}
