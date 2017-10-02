package mdiTestBed;

public class MDI_PayLoad {
	
	public static String dupAgyExc(){
		
		String request = "{\n \"General\": {\n \"Agency\": \"BB4\",\n \"ElExport\": \"Spot Data\",\n \"ReportingName\": \"VP\",\n \"BillingCalendar\": \"Broadcast\",\n \"BillingCycle\": \"EOM\",\n \"AgencyType\": \"GENERAL\",\n \"AgencyCommissionCash\": \"0.50\",\n \"AgencyCommissionTrade\": \"0.50\"\n },\n \"Addresses\": {\n \"Main\": {\n \"Street1\": \"1234 Vp street\",\n \"City\": \"San Francisco\",\n \"State\": \"<CA>\",\n \"Zip\": \"60021\",\n \"Country\": \"India\"\n }\n }\n}";
		
		return request;
		
		
	}

}
