package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProfileEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    
  
    public Response sendGetRequest(String APIName) {
    	try {
    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
	   
	    switch (APIName) {
	        
	        case "About Me":
	            application_ENDPOINT_PATH = "/api/user/me";
	            break;
	        case "Get User Details Using User Id":
	            application_ENDPOINT_PATH = "/api/user/details?userId="+ BaseEndpoints.userId;
	            break;     
	    }

	    this.apiNameIdentifier = APIName;
	    return result = requestSpecification.get(application_ENDPOINT_PATH);
    	}
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
	}
    
    public void sendPutRequestWithPayload(String jsonFileName) throws IOException {
    	
    	try {
        switch (jsonFileName) {
            case "Update Profile.json":
                application_ENDPOINT_PATH = "/api/user/profile_update";
                break;
            default:
                throw new IllegalArgumentException("PUT endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");

        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

        if (application_ENDPOINT_PATH.equals("/api/user/profile_update")) {
            String dynamicEmail = BaseEndpoints.email;
            result = requestSpecification
                    .multiPart("email", dynamicEmail)
                    .put(application_ENDPOINT_PATH);
        }
        else {
        	
        // Read JSON payload file
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/ProfilePayloads/" + jsonFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        
        // Send PUT request
        result = requestSpecification
                .body(jsonObject.toString())
                .put(application_ENDPOINT_PATH);
        }
        System.out.println("Response: " + result.getBody().asPrettyString());}
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }}
    	
    public Response applicationSalesDeletePayload(String APIName ) {
    	try {
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
		switch (APIName){
		case "Delete User":
			application_ENDPOINT_PATH="/api/user/delete?mobile=" + dynamicMobile.replace("+", "");
			System.out.println(application_ENDPOINT_PATH);
			break;

		}
		this.apiNameIdentifier = APIName;
			result=requestSpecification.delete(application_ENDPOINT_PATH);
	     	return result;
    	}
	     	catch (Exception e) {
	            String exceptionName = e.getClass().getSimpleName();
	            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
	                                        exceptionName);
	            throw e;
	        }
    }}