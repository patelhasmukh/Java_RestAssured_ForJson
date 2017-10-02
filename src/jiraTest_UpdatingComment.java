import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.reusableFunctions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
//import io.restassured.response.Response;

public class jiraTest_UpdatingComment {
	
	@Test
	
	public void updateComment(){
		//updating comment
	RestAssured.baseURI = "http://localhost:8080";
	Response res= given().header("Content-Type", "application/json").
	headers("Cookie","JSESSIONID="+reusableFunctions.getSessionKey1()).
	//pathParams("commentid", "10103"). //to parameterise- use this in the path like {commentid}
			
	
	body("{\"body\": \"Updating comment\","+
	    "\"visibility\": {"+
	        "\"type\": \"role\","+
	        "\"value\": \"Administrators\"}"+
	"}").when().
	put("/rest/api/2/issue/10045/comment/"+ reusableFunctions.getCommentId()).then().statusCode(200).
	extract().response();
	
	
	
	
	}

}
