package mdiTestBed;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MDI_Post_Agy_Execption {

	@Test
	public void postData() {

		// BaseURL
		RestAssured.baseURI = "http://192.168.6.187:9158";

		Response res = given()
				.body(MDI_PayLoad.dupAgyExc())
				.when().post("/Import/AddAgency")
				.then().assertThat().statusCode(500).and()
				.body("errors[0]",
						equalTo("TImportProcessor.DispatchPostMessage: EXCEPTION [EAssertionFailed: TProcessor.ValidateAddRequest - Agency with name [BB4] already exists (C:\\builds\\10.5\\src\\WideOrbit\\Source\\Projects\\Servers\\WOMasterDataIntegrationServer\\Import\\u_AgencyProcessor.pas, line 1072)]"))
				.and().contentType(ContentType.JSON).and().extract().response();

		// declare new var to convert responce from res to string format
		String responseString = res.asString(); // asString() to show me as String
												
		System.out.println(responseString);


	}

}
