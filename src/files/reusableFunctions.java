package files;

import static io.restassured.RestAssured.given;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class reusableFunctions {
	
	public static XmlPath rawToXML(Response res){
		
		String respon =  res.asString();
		XmlPath x = new XmlPath(respon);
		return x;
		
	}
	
	public static JsonPath rawToJson(Response res){
		
		String responseString = res.asString();
		JsonPath js = new JsonPath(responseString);	
		return js;
}
	public static String getSessionKey1(){
		
		//Properties prop = new Properties();
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		body("{ \"username\": \"syedhussainqa\", \"password\": \"Kamry5801\" }").
		when().post("/rest/auth/1/session").then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath js = reusableFunctions.rawToJson(res);
		String sessionid = js.get("session.value");
		return sessionid;
	}
	
	public static  String getCommentId(){
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
	String commentId = js.get("id");
	return commentId;
	
}
}
