package RESTTest;

import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HHTTPRequest {
	int id;
	@Test(priority=1)
	void getUser() {
		when()
		 .get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		.log().all();
	}
	
	@Test(priority=2)
	void creatUser() {
		HashMap hm=new HashMap();
		hm.put("name","Deepak");
		hm.put("job","tester");
		
		id=given()
		   .contentType("application/json")
		   .body(hm)
		.when()
		    .post("https://reqres.in/api/users")
	        .jsonPath().getInt("id");
		}
	
	@Test(priority=3,dependsOnMethods={"creatUser"})
	void updateUser() {
		HashMap hm=new HashMap();
		hm.put("name","Kanoujia");
		hm.put("job","automation");
		
		given()
		   .contentType("application/json")
		   .body(hm)
		.when()
		    .post("https://reqres.in/api/users/"+id)
		 .then()
		   .statusCode(201);
	       
		}
	
	@Test(priority=4)
	void deleteUser() {
		when()
		  .delete("https://reqres.in/api/users/"+id)
		.then()
		   .statusCode(204);
	}

}
