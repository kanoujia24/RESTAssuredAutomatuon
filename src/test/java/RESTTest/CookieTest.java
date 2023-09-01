package RESTTest;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookieTest {
	
	//@Test(priority=1)
	void testCookies() {
		when()
		 .get("https://www.google.com/")
		.then()
		  .cookie("AEC","Ad49MVHXqF-hbWp4L6-R8V3YlzJ5VA3tjXLbLTaEyDHIsfM1i2QIPIEBfZI")
		  .log().all();
		
	}
	
	@Test(priority=2)
	void getCookieInfo() {
		 Response res =given()
		.when()
          .get("https://www.google.com/");
		 //now from here writing normal java code
		 
		 //get single cookie value
		 //String cookievalue=res.getCookie("AEC");//cookie name get by runningthe url in postman
		 //System.out.println("cookie vlue is"+" "+cookievalue);
		 
		 //get the vlaue of all cookie
		 Map<String,String> cookie_values=res.getCookies();
		 
		 //System.out.println(cookie_value.keySet());//if u want to print all cookie name 
		 
		 for(String k:cookie_values.keySet()) {
			 String cookie_value=res.getCookie(k);
		      System.out.println(k+" "+cookie_value);
	
		 }
	}
}
