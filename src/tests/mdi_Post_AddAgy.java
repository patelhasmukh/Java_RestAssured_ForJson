package tests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class mdi_Post_AddAgy {
	
	@Test
	public void postData(){
		
		//BaseURL
		RestAssured.baseURI= "http://192.168.6.187:8108";
		
		Response res = given().
		body("{"+
				 "\"General\": {"+
				 "\"Agency\": \"TestSyed09\","+
				 "\"ElExport\": \"Spot Data\","+
				 "\"ReportingName\": \"<VP>\","+
				 "\"BillingCalendar\": \"Broadcast\","+
				 "\"BillingCycle\": \"EOM\","+
				 "\"AgencyType\": \"GENERAL\","+
				 "\"AgencyCommissionCash\": \"0.50\","+
				 "\"AgencyCommissionTrade\": \"0.50\","+
				 "},"+
				 "\"Addresses\": {"+
				 "\"Main\": {"+
				 "\"Street1\": \"1234 <Vp> street\","+
				 "\"City\": \"&&San Francisco\","+
				 "\"State\": \"<CA>\","+
				 "\"Zip\": \"!60021\","+
				 "\"Country\": \"India\" "+
 "}"+
 "}"+
 "}").
		when().
		post("/Import/AddAgency").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		extract().response();
		
		
		// declare new var to convert responce from res to string format
		String responseString = res.asString(); //asString() to show me as string
		System.out.println(responseString);
	}

}
