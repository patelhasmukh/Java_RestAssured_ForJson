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


public class postRequestXML {
	
	@Test
	public void postData() throws IOException{
		
		
		String postData = GenerateStringFromResource("C:\\Users\\shussain\\Desktop\\postData.xml");
		//BaseURL
		RestAssured.baseURI= "https://maps.googleapis.com";
		
		Response resp = given().
		
		queryParam("key","AIzaSyBFvPb1rNygsBOIYXP9w7NEgBkL0AIyPRQ" ).
		body(postData).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		//to convert raw data to string
		XmlPath x= reusableFunctions.rawToXML(resp);
		System.out.println("X value is - "+ x);
		System.out.println("Status is -" +x.get( "PlaceAddResponse.status").toString());

	
				
	}
	 public static String GenerateStringFromResource(String path) throws IOException{
		 
		 return new String(Files.readAllBytes(Paths.get(path)));
	 }

	}


