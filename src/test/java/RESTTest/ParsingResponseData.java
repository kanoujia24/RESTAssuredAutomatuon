package RESTTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;



public class ParsingResponseData {
  //APPROCH ONE 	

	//@Test
	void testData() {
		given().contentType(ContentType.JSON)
		.when().get("https://dummy.restapiexample.com/api/v1/employees")
		.then()
		.statusCode(200)
		.header("Content-Type","application/json")
		.body("data[0].employee_name",equalTo("Tiger Nixon"));
	}
	
	
		//APPROACH 02
	//@Test
	void testData1(){
		
		Response res=given()
				.contentType(ContentType.JSON)
		.when().get("https://dummy.restapiexample.com/api/v1/employees");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String em=res.jsonPath().get("data[0].employee_name").toString();
		Assert.assertEquals(em,"Tiger Nixon");
	}	
	
	//APPROACH 03
	
	//@Test
	void testdata2() throws JSONException {
		Response res=given()
				.contentType(ContentType.JSON)
			    .when().get("https://dummy.restapiexample.com/api/v1/employees");
		
		JSONObject jo=new JSONObject(res.toString());
		
		for(int i=0; i<jo.getJSONArray("data").length();i++) {
			
		String name=jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();
		System.out.println(name);
			
		}
		
	}
	
	//now check specific employee name is present or not,
	//@Test
	void testdata3() throws JSONException  {
		Response res=given()
				.contentType(ContentType.JSON)
			    .when().get("https://dummy.restapiexample.com/api/v1/employees");
		
		JSONObject jo=new JSONObject(res.toString());
		
		boolean flag =false;
		
		for(int i=0; i<jo.getJSONArray("data").length();i++) {
			
		String name=jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();
		if(name.equals("Airi Satou")) {
			flag=true;
			break;
		}
			
		}
		Assert.assertEquals(flag,true);
		
	}
	
	
	//chAECK THE TOTAL AGE OF ALL EMPLOYEE
	//@Test
	void testData4() throws JSONException {
	double totalage=0;
	Response res=given()
			.contentType(ContentType.JSON)
		    .when().get("https://dummy.restapiexample.com/api/v1/employees");
	JSONObject jo=new JSONObject(res.toString());
	for(int i=0;i<jo.getJSONArray("data").length();i++) {
		String eage=jo.getJSONArray("data").getJSONObject(i).get("age").toString();
		totalage=totalage+(Double.parseDouble(eage));
	}
	System.out.println("total age of empoloyees"+" "+totalage);
	Assert.assertEquals(totalage, 1011);
	
	}

}
