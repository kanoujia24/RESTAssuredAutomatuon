package RESTTest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



public class HashMapData {
	
//Creating post payload using HashMap
	//@Test(priority=1)
  void creatData() {
		
		HashMap hm=new HashMap();
		hm.put("name", "deepak");
		hm.put("job","tester");
		
		given().contentType("application/json").body(hm)
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201)
		      .body("name",equalTo("deepak"))
		      .body("job",equalTo("tester"))
		      .log().all();
		}
	//@Test(priority=2)
	void deletePost() {
		when().delete("https://reqres.in/api/users/600")
		.then().statusCode(204);
	}
	
	
	//Creating post payload using org.json
		//@Test(priority=1)
	  void orgJson() throws JSONException {
			JSONObject data =new JSONObject();
			
			data.put("name", "kanoujia");
			data.put("job","automation");
			
			given().contentType("application/json").body(data.toString())//due to org.json here need to chage response into string form
			.when().post("https://reqres.in/api/users")
			.then().statusCode(201)
			      .body("name",equalTo("kanoujia"))
			      .body("job",equalTo("automation"))
			      .log().all();
			}
		//@Test(priority=2)
		void deleteJson() {
			when().delete("https://reqres.in/api/users/600")
			.then().statusCode(204);
		}
		
		
//Creating post payload using POJO Class
				//@Test(priority=1)
			  void pojoData() {
					PojoData data=new PojoData();
					data.setName("kumar");
					data.setJob("developer");
					
					given().contentType("application/json").body(data)
					.when().post("https://reqres.in/api/users")
					.then().statusCode(201)
					      .body("name",equalTo("kumar"))
					      .body("job",equalTo("developer"))
					      .log().all();
					}
				//@Test(priority=2)
				void deletePojo() {
					when().delete("https://reqres.in/api/users/600")
					.then().statusCode(204);
				}
				
				
//Creating post payload using external file
				@Test(priority=1)
			  void externalFile() throws FileNotFoundException, JSONException {
					File f=new File(".\\rest.Data");
					FileReader fr=new FileReader(f);
					JSONTokener jt=new JSONTokener(fr);
					JSONObject data=new JSONObject(jt);
					
					given().contentType("application/json").body(data.toString())
					.when().post("https://reqres.in/api/users")
					.then().statusCode(201)
					      .body("name",equalTo("morpheus"))
					      .body("job",equalTo("leader"))
					      .log().all();
					}
				@Test(priority=2)
				void deleteFile() {
					when().delete("https://reqres.in/api/users/600")
					.then().statusCode(204);
				}
				
		

}
