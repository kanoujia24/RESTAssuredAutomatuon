package RESTTest;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import  static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

public class Authentication {
	
	//@Test(priority=1)
	void testBasicAuth() {
		given().auth().basic("admin","admin")
		.when().get("https://the-internet.herokuapp.com/basic_auth")
		.then()
		   .statusCode(200)
		   .log().all();
		}
	
	//@Test(priority=2)
	void testDigestAuth() {
		given().auth().digest("postman","password")
		.when().get("https://postman-echo.com/basic-auth")
		.then()
		   .statusCode(200)
		   .body("authenticated",equalTo(true))
		   .log().all();
		
	}
	//@Test
	void testPreemtiveAuth() {
		given().auth().preemptive().basic("postman","password")
		.when().get("https://postman-echo.com/basic-auth")
		.then()
		   .statusCode(200)
		   .body("authenticated",equalTo(true))
		   .log().all();
		
	}
	
	//@Test
	void testBearerToken() {
		String bearer="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";
		given().headers("Authorization","Bearer"+bearer)
		.when().get("https://api.github.com/users/repos")
		.then().statusCode(200).log().all();
	}
	
	//@Test()
	void testAPIKey() {
		given().queryParam("APPID","526ab7bb39d877346f19f44b8353d15e")
		.when().get("http://api.openweathermap.org/geo/1.0/direct?q=Lucknow&limit=7")
		.then().statusCode(200).log().all();
	}
@Test
void testFaker() {
	Faker fr=new Faker();
	 String fn=fr.name().fullName();
	 String fin=fr.name().firstName();
	 String ln=fr.name().lastName();
	 String un=fr.name().username();
	 String email=fr.internet().safeEmailAddress();
	 String pw=fr.internet().password();
	 System.out.println(fn);
	 System.out.println(fin);
	 System.out.println(ln);
	 System.out.println(un);
	 System.out.println(email);
	 System.out.println(pw);
	
}
	
}
