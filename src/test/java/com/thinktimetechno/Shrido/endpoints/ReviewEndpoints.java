package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReviewEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    
    
    public Response sendGetRequest(String APIName) {
    	try {
    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
	   
	    switch (APIName) {
	        case "Get User Reviews":
	            application_ENDPOINT_PATH = "/api/review/"+BaseEndpoints.userId;
	            break;
	        case "Get Trip Review":
	            application_ENDPOINT_PATH = "/api/review/trip?trip_id="+BaseEndpoints.tripId;
	            break;
	            
	    }
	    this.apiNameIdentifier = APIName;
	    return result = requestSpecification
	    		        .get(application_ENDPOINT_PATH);
    	}
    	
    	
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
	}
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
            case "Create Review.json":
                application_ENDPOINT_PATH = "/api/review/create";
                break;

            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }

        this.apiNameIdentifier = jsonFileName.replace(".json", "");
            // Prepare request
            requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/ReviewPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);
            
            //Updating the user details to create a review
            
            jsonObject.put("to_trip_id", BaseEndpoints.tripId);
            jsonObject.put("to_user_id", BaseEndpoints.userId);
            

            // Send request with body
            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);
    	
    	}catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
}