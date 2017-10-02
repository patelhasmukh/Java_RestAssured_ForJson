import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.reusableFunctions;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class bxfPlayAck {
	
	@Test
	public void postData() throws IOException{
		
		
		String postData = GenerateStringFromResource("C:\\Users\\shussain\\Desktop\\BXF_Msg\\PlaylistAck\\1501009905653.xml");
		//BaseURL
		RestAssured.baseURI= "http://192.168.101.66:9159";
		
		Response resp = given().log().all().
		header("Content-Type", "application/XML; charset=utf-8").
		body(postData).
		when().
		post("/bxfxml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		//to convert raw data to string
		XmlPath xmlResponse= reusableFunctions.rawToXML(resp);
		String responseString = resp.asString();
		System.out.println("XML response is - "+ responseString);
					
	}
	 public static String GenerateStringFromResource(String path) throws IOException{
		 
		 return new String(Files.readAllBytes(Paths.get(path)));
	 }

	}


