package tests;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.reusableFunctions;
import io.restassured.RestAssured;
//import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class jiraTest_AddingComment {
	
	@Test
	
	public void addComment(){
		//add comment
	RestAssured.baseURI = "http://localhost:8080";
	Response res= given().header("Content-Type", "application/json").
		headers("Cookie","JSESSIONID="+reusableFunctions.getSessionKey1()).
			
	
	body("{\"body\": \"I am updating the existing comment\","+
	    "\"visibility\": {"+
	        "\"type\": \"role\","+
	        "\"value\": \"Administrators\"}"+
	"}").when().
	post("/rest/api/2/issue/10045/comment").then().statusCode(201).
	extract().response();
	
	JsonPath js = reusableFunctions.rawToJson(res);
	String id = js.get("id");
	System.out.println(id);
	
	
	}

}
