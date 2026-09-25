package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailsEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    public static String dynamicMobile; 
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
            case "Add PreDefine messages.json":
                application_ENDPOINT_PATH = "/api/settings/predefine_message";
                break;
            case "Add App price value.json":
                application_ENDPOINT_PATH = "/api/settings/app_price_suggest_value";
                break;
            case "Add Email Templates.json":
                application_ENDPOINT_PATH = "/api/settings/add-email-templates";
                break;
           
            case "Email Testing.json":
                application_ENDPOINT_PATH = "/api/settings/email-test";
                break;
            case "Add Email Us.json":
                application_ENDPOINT_PATH = "/api/settings/email-us";
                break;
                
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");
        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

        
        if(application_ENDPOINT_PATH.equals("/api/settings/predefine_message")) {
        		    result = requestSpecification
        		            .multiPart("text", "")
        		            .multiPart("review", "")
        		            .multiPart("type", "trip_request_notification")
        		            .post(application_ENDPOINT_PATH);
        		}
        else if(application_ENDPOINT_PATH.equals("/api/settings/add-email-templates")) {
		    result = requestSpecification
		            .multiPart("type", "feedback")
		            .post(application_ENDPOINT_PATH);
        }
        else {

	        
            //Read payload and modify if needed
        	
            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/EmailsPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);

            
            // Send request with body
            result = requestSpecification
                    .body(jsonObject.toString())
                   .post(application_ENDPOINT_PATH);
        }
         // Print response
            System.out.println("Response: " + result.getBody().asPrettyString());
       

    	}catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
    
  
    
    public void sendPutRequestWithPayload(String jsonFileName) throws IOException {
    	
    	try {
        switch (jsonFileName) {
            case "Update Email Template.json":
                application_ENDPOINT_PATH = "/api/settings/update-email-templates";
                break;
            default:
                throw new IllegalArgumentException("PUT endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");
        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
        
        if (application_ENDPOINT_PATH.equals("/api/settings/update-email-templates")) {
            result = requestSpecification
                    .multiPart("type", "feedback") 
                    .put(application_ENDPOINT_PATH);
        }
        
       
        System.out.println("Response: " + result.getBody().asPrettyString());}
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }}
    	
}