package files;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.reusableFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class jiraTest {
	Properties prop = new Properties();
	@BeforeTest
	
public void getData() throws IOException{
		
		
		FileInputStream file1= new FileInputStream("D:\\Tools\\Workspace\\RestAssured\\src\\files\\env.properties");
		prop.load(file1);
		
	}
	
	@Test
	public void JiraApi() throws FileNotFoundException, IOException{
			
		//create issue
			RestAssured.baseURI = prop.getProperty("JIRAHOST"); //"http://localhost:8080";
		Response res= given().header("Content-Type", "application/json").headers("Cookie","JSESSIONID="+reusableFunctions.getSessionKey1()).
		
		body("{"+
	"\"fields\":{"+
		"\"project\":{"+
			"\"key\": \"TEST\""+
		"},"+
		"\"summary\": \"Syed issue 6\","+
		"\"description\":\"Creating a second bug\","+
		"\"issuetype\":{"+
			"\"name\": \"Bug\""+
		"}"+
	"}}").when().
		post("/rest/api/2/issue").then().statusCode(201).
		extract().response();
		
		JsonPath js = reusableFunctions.rawToJson(res);
		String id = js.get("id");
		System.out.println(id);
		
		//prop.setProperty("JIRAID", id);
		//prop.store(new FileOutputStream("test.properties"), null);
		
	}

	

}
