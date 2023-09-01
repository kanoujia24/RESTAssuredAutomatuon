package RESTTest;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public final class UploadDownloadFile {
	//@Test
	void singleFile() {
		File f=new File("C:\\Users\\lenovo\\Desktop\\Sulabh Image");
		given().multiPart("file",f)
		.contentType("multiPart/form-data")
		.when().post("http://localhost:8000/uploadFile")
		.then()
		.statusCode(200)
		.body("fileName",equalTo("pdf.docs.pdf"))
		.log().all();
		
		
	}
	
	//uploading multiple fiels
	//@Test
	void multiplefiles() {
		File f1=new File("C:\\Users\\lenovo\\Desktop\\Sulabh Image");
		File f2=new File("C:\\Users\\lenovo\\Desktop\\Sulabh Image");
		given()
		  .multiPart("files",f1)
		  .multiPart("files",f2)
		  .contentType("multiPart/form-data")
	   .when().post("http://localhost:8080/uploadMultipleFiles")
	   .then()
	        .statusCode(200)
	        .body("[0].fileName", equalTo("file-sample_100kB.doc"))
	        .body("[1].fileName",equalTo("pdf.docs.pdf"));
	}
	
	//download file
	@Test
	void download() {
		when()
		    .get("http://localhost:8080/downloadFile/pdf.docs.pdf")
		.then()
		    .statusCode(200)
		    .log().all();
	}

}
