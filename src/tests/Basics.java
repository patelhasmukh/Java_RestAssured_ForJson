package tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;


public class Basics {
	
	//test annotation for testng framework 
	@Test

	public void Test() {
		
				
		//BaseURL
		RestAssured.baseURI= "https://maps.googleapis.com";
		
		//Enter parameters
		given().
			param("location", "-33.8670522,151.1957362").
			param("radius", "500").
			param("key","AIzaSyBFvPb1rNygsBOIYXP9w7NEgBkL0AIyPRQ").
				
		//Enter rest of the connection string		
		when().
			get("/maps/api/place/nearbysearch/json").
		
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			//body("results[0].geometry.location.lat",equalTo("-33.86755700000001"));
			body("results[0].name",equalTo("Sydney")).and().
			body("results[0].scope",equalTo("GOOGLE")).and().
			header("Server", "pablo");
								
	}


}
