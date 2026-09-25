package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TravelSafetyEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    public static String dynamicMobile; 
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
         
            case "Travel Safety.json":
                application_ENDPOINT_PATH = "/api/user_settings/travel-safety";
                break; 
    
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");

        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

       
            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/TravelSafetyPayloads/"+ jsonFileName;
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
    
   
  }