package tests;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;


public class mdi_AdvAgy {
	
	@Test
	
	public void Test() {
		
		
		//BaseURL
		RestAssured.baseURI= "http://127.0.0.1:9900";
		
		//Enter parameters
		given().
			param("PriorVersion", "NULL").
			//param("Version", "2005").
				
		//Enter rest of the connection string		
		when().
			get("/MasterData/v1/ADVERTISERAGENCIES").
		
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("data[0].advertiser_agency_id",equalTo("{F2C85BC7-7FFA-4975-8205-41DE0BE85FEB}")).and().
			body("data[0].is_direct",equalTo(0)).and().
			body("data[0].agency_id",equalTo("{9E89FBBC-8390-49BA-8434-8AF5CA8CCAFC}"));
			
									
	}


}

