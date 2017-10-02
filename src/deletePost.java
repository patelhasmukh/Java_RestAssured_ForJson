import org.testng.annotations.BeforeTest;
import files.resources;
import files.reusableFunctions;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.payLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class deletePost {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		
		
		FileInputStream file1= new FileInputStream("D:\\Tools\\Workspace\\RestAssured\\src\\files\\env.properties");
		prop.load(file1);
		
		//prop.get("HOST");
	}
	
	@Test
	public void addDelPlace(){
		
		
		//Task 1 grab the responce
		RestAssured.baseURI= prop.getProperty("HOST");
		
		Response res =given().		//declaring res to save the whole response once the request is passed
		
		queryParam("key",prop.getProperty("KEY") ).
		body(payLoad.getPostData()).log().all().
		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		extract().response();
		
		JsonPath js= reusableFunctions.rawToJson(res);
		// declare new var to convert response from res to string format
		String responseString = res.asString(); //asString() to show me as string
		System.out.println("This is a response for palce add msg : "+ "\n"+ responseString);
		
		//Task 2- grab the place ID from the response
		//To convert the string to Json, this step reads the srting and converts it not Json path, with the help of Json path class
		//JsonPath js = new JsonPath(responseString);						//*When you extract the response, it will first be in Raw format and then transfered to String and then Json
		String placeid = js.get("place_id");
		System.out.println("PlaceId = " + placeid);
		
		//Task 3 - Place this place id in delete request
		given().
		queryParam("key",prop.getProperty("KEY") ).
		
		//Inserting the msg body
		body("{"
				+ "\"place_id\" : \""+placeid+"\" "
						+ "}").
		when().
		post("maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
		
		
	}

}
