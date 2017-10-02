import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import files.reusableFunctions;

public class Basics3 {
	
	//test annotation for testng framework 
	@Test

	public void Test() {
		
				
		//BaseURL
		RestAssured.baseURI= "https://maps.googleapis.com";
		
		//Enter parameters
		Response res = given().
			param("location", "-33.8670522,151.1957362").
			param("radius", "500").
			param("key","AIzaSyBFvPb1rNygsBOIYXP9w7NEgBkL0AIyPRQ").log().all().
				
		//Enter rest of the connection string		
		when().
			get("/maps/api/place/nearbysearch/json").
		
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("results[0].name",equalTo("Sydney")).and().
			body("results[0].scope",equalTo("GOOGLE")).and().
			header("Server", "pablo").log().all().
			extract().response();
			JsonPath js = reusableFunctions.rawToJson(res);
			
			int count = js.get("results.size()");
			
			for (int i=0;i<count;i++){
				
				System.out.println((js.get("results["+i+"].name")).toString());
				System.out.println((js.get("results["+i+"].place_id")).toString());
				
				
				
			}
			
			System.out.println(count);
			
			
								
	}


}
