package mdiTestBed;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class MDI_Post_Agy {
	
	@Test
	public void postData(){
		
		//BaseURL
		RestAssured.baseURI= "http://192.168.6.187:9158";
		
		Response res = given().
		body("{\n \"General\": {\n \"Agency\": \"BB4\",\n \"ElExport\": \"Spot Data\",\n \"ReportingName\": \"VP\",\n \"BillingCalendar\": \"Broadcast\",\n \"BillingCycle\": \"EOM\",\n \"AgencyType\": \"GENERAL\",\n \"AgencyCommissionCash\": \"0.50\",\n \"AgencyCommissionTrade\": \"0.50\"\n },\n \"Addresses\": {\n \"Main\": {\n \"Street1\": \"1234 Vp street\",\n \"City\": \"San Francisco\",\n \"State\": \"<CA>\",\n \"Zip\": \"60021\",\n \"Country\": \"India\"\n }\n }\n}").
		when().
		post("/Import/AddAgency").
		then().assertThat().statusCode(500).and().
		body("errors[0]",equalTo("TImportProcessor.DispatchPostMessage: EXCEPTION [EAssertionFailed: TProcessor.ValidateAddRequest - Agency with name [BB4] already exists (C:\\builds\\10.5\\src\\WideOrbit\\Source\\Projects\\Servers\\WOMasterDataIntegrationServer\\Import\\u_AgencyProcessor.pas, line 1072)]"))
		.and().contentType(ContentType.JSON).and().
		extract().response();
		
		// declare new var to convert responce from res to string format
		String responseString = res.asString(); //asString() to show me as string
		System.out.println(responseString);
		/*JsonPath js = new JsonPath(responseString);
		String agyId= js.get("result.AgencyID");
		System.out.println("agyId = " +agyId);*/
		

	}

}
