package RESTTest;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseData {
	
	//@Test(priority=1)
	void testXMLData() {
		when().get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.header("Content-Type","application/xml; charset=utf-8")
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer"));
	}
  
     //@Test(priority=2)
void testXMLData1() {
	Response res=given()
	.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
	Assert.assertEquals(res.getStatusCode(), 200);
	Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
	
	String ename=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
	Assert.assertEquals(ename,"Developer");
	
}

//Vreify total number of travelers

//@Test
    void testData2() {
	Response res=given()
	.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
	
	XmlPath xp=new XmlPath(res.asString());
	
	
	List<String> tsize=xp.getList("TravelerinformationResponse.travelers.Travelerinformation");
	System.out.println(tsize);
	Assert.assertEquals(tsize.size(), 10);
} 
    
    //verify specific traveller name is present or not

@Test
void testData3() {
Response res=given()
.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
boolean flag=false;
XmlPath xp=new XmlPath(res.asString());
List<String> ename=xp.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
System.out.println(ename);
for(String k:ename) {
	if(k.equals("asdasd")) {
		flag=true;
		break;
	}
}
Assert.assertEquals(flag, true);
}
}