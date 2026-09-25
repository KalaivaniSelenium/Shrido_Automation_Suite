package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserSettingsEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
         
            case "Driver Settings.json":
                application_ENDPOINT_PATH = "/api/user_settings/driver";
                break;
            case "Passenger Settings.json":
                application_ENDPOINT_PATH = "/api/user_settings/passenger";
                break;
            case "Notification Settings.json":
                application_ENDPOINT_PATH = "/api/user_settings/notification";
                break;
            
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }

        this.apiNameIdentifier = jsonFileName.replace(".json", "");
	        // Prepare request
	        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);


            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/UserSettingsPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Send request with body
            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);

        // Print response
        System.out.println("Response: " + result.getBody().asPrettyString());

    	}catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    	
    }
    
    public Response sendGetRequest(String APIName) {
    	try {
	   
	    switch (APIName) {
	        case "All settings":
	            application_ENDPOINT_PATH = "/api/settings";
	            break; 
	        case "redirect_to_app":
                application_ENDPOINT_PATH = "/api/settings/redirect_to_app";
                break;  
                
	        default:
                throw new IllegalArgumentException(
                        "Endpoint not defined for API: " + APIName);     
	    }
	    this.apiNameIdentifier = APIName;
   	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

   	 // Send request
     if (APIName.equals("redirect_to_app")) {
         result = requestSpecification
                 .queryParam("redirect_to", "history")
                 .get(application_ENDPOINT_PATH);
     } else {
         result = requestSpecification
                 .get(application_ENDPOINT_PATH);
     }

     System.out.println("Response: " + result.getBody().asPrettyString());

     return result;
	   // return result = requestSpecification.get(application_ENDPOINT_PATH);
    	}
	    
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
	}
    }
    
   