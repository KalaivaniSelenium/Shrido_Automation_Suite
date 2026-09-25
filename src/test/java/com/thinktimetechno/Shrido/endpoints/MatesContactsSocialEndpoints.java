package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MatesContactsSocialEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    public static String dynamicMobile; 
    
    
    public Response sendGetRequest(String APIName) {
    	try {
    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
	   
	    switch (APIName) {
	        case "Get Referral History":
	            application_ENDPOINT_PATH = "/api/user/referral-history?page=0";
	            break;
	        case "Get Total Referrals":
	            application_ENDPOINT_PATH = "/api/user/total-referral";
	            break;
	        case "Get User Total Points":
	            application_ENDPOINT_PATH = "/api/user/total-points";
	            break;
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
                case "Add Referral Code.json":
                    application_ENDPOINT_PATH = "/api/user/add-referral";
                    break;
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
                case "Auth refresh access token.json":
                    application_ENDPOINT_PATH = "/api/auth/refresh";
                    break;
                case "Auth logout revoke session.json":
                    application_ENDPOINT_PATH = "/api/auth/logout";
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
              
                
             // Use the target mate ID for mate-related APIs
                if (application_ENDPOINT_PATH.equals("/api/mate/send_request")
                        || application_ENDPOINT_PATH.equals("/api/mate/request_status")
                        || application_ENDPOINT_PATH.equals("/api/mate/favourite")) {

                    if (BaseEndpoints.targetMateId > 0) {
                        jsonObject.put("user_id", BaseEndpoints.targetMateId);
                    }
                }
                
                
                
                
                
                //Adding referral code
                if (application_ENDPOINT_PATH.equals("/api/user/add-referral")) {
                    jsonObject.put("referral_code", BaseEndpoints.affiliateCode);  
                }
                if (application_ENDPOINT_PATH.equals("/api/trip/notify_mates")) {
                    jsonObject.put("trip_id", BaseEndpoints.tripId);
        		}
                if (application_ENDPOINT_PATH.equals("/api/auth/refresh")) {

                    if (BaseEndpoints.refreshToken == null
                            || BaseEndpoints.refreshToken.isBlank()) {

                        throw new IllegalStateException(
                                "Refresh token is empty. Check OTP verification token storage."
                        );
                    }

                    jsonObject.put("refresh_token", BaseEndpoints.refreshToken);
                }

                result = requestSpecification
                        .body(jsonObject.toString())
                        .post(application_ENDPOINT_PATH);
                System.out.println("Request URL: " + application_ENDPOINT_PATH);
                System.out.println("Request Body: " + jsonObject.toString());
                System.out.println("Response Status: " + result.getStatusCode());
                System.out.println("Response Body: " + result.getBody().asPrettyString());
                
                
             // Save new tokens after successful refresh
                if (application_ENDPOINT_PATH.equals("/api/auth/refresh")
                        && result.getStatusCode() == 200) {


                }
            }

        } catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
}

