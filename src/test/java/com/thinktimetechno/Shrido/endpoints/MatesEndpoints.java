package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MatesEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    public static String dynamicMobile; 
    
    
    public Response sendGetRequest(String APIName) {
    	try {
    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
	   
	    switch (APIName) {
	        case "Get Latest Friends":
	            application_ENDPOINT_PATH = "/api/mate?page=0";
	            break;
	        case "Get All Mates":
	            application_ENDPOINT_PATH = "/api/mate/all_mates?page=0";
	            break;
	        case "Get User's Favourites friends":
	            application_ENDPOINT_PATH = "/api/mate/favourites?page=0";
	            break;
	        case "Get Mates from ride":
	            application_ENDPOINT_PATH = "/api/mate/mates_from_ride";
	            break;
	        case "Get friends from contact":
	            application_ENDPOINT_PATH = "/api/mate/mates_from_contact?page=0";
	            break;
	        case "Get all Users who use Application":
	            application_ENDPOINT_PATH = "/api/mate/all_users";
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
                case "Sync Contact.json":
                    application_ENDPOINT_PATH = "/api/mate/sync_contact";
                    break;
                case "Send friend request.json":
                    application_ENDPOINT_PATH = "/api/mate/send_request";
                    break;
                case "Update friend request status.json":
                    application_ENDPOINT_PATH = "/api/mate/request_status";
                    break;
                case "Favourite or Unfavourite friend.json":
                    application_ENDPOINT_PATH = "/api/mate/favourite";
                    break;
                
                case "User Block.json":
                    application_ENDPOINT_PATH = "/api/user_block/5"; // No body required
                    break;
                default:
                    throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
            }
            this.apiNameIdentifier = jsonFileName.replace(".json", "");
            // Prepare request
            requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

            // ---- CASE 1: No body required for User Block ----
            if (application_ENDPOINT_PATH.equals("/api/user_block/5")) {
                result = requestSpecification
                		.post(application_ENDPOINT_PATH);
            }

            // ---- CASE 2: APIs with body ----
            else {
                String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/MatesPayloads/" + jsonFileName;
                String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
                JSONObject jsonObject = new JSONObject(jsonContent);
                
                if (application_ENDPOINT_PATH.equals("/api/trip/notify_mates")) {
                    jsonObject.put("trip_id", BaseEndpoints.tripId);
        		}

                result = requestSpecification
                        .body(jsonObject.toString())
                        .post(application_ENDPOINT_PATH);
            }

        } catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
}

