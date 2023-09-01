package RESTTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestHeader {
	//@Test(priority=1)
	void headers() {//in this test we only verify header value is correct or not
		when()
		  .get("https://www.google.com/")
		.then()
		  .header("Content-Type","text/html; charset=ISO-8859-1")
		  .and()//if you want to separate multiple validation then use and() method. It is not necessory depends on u.
 		  .header("Content-Encoding","gzip")
		  .and()
		  .header("Server","gws");
	}
	
	@Test(priority=2)
	void getHeaders() {
		Response res=given()
				.when().get("https://www.google.com/");
		   
		//get single header info
		//String headerValue=res.getHeader("Content-Type");
		//System.out.println("Header value is"+" "+headerValue);
		
		//get multi header info
		Headers headersValue=res.getHeaders();
		
		for(Header k:headersValue) 
			System.out.println(k.getName()+"  "+k.getValue());
		
	}

}
