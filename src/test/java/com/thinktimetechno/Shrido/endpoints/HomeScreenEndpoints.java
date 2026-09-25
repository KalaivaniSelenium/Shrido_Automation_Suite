package com.thinktimetechno.Shrido.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HomeScreenEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;
   
   
    

    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
            case "Create Trip.json":
                application_ENDPOINT_PATH = "/api/trip/create_trip";
                break;
            case "Notify favourite friends about trip.json":
                application_ENDPOINT_PATH = "/api/trip/notify_mates";
                break;
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");
        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
        
       // Load payload file
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/HomeScreenPayloads/" + jsonFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        
        // Creating dynamic start date
        if (application_ENDPOINT_PATH.equals("/api/trip/create_trip")) {
        tripStartDate = LocalDateTime.now().plusDays(3)
                               .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            jsonObject.put("start_date", tripStartDate);
        }
        if (application_ENDPOINT_PATH.equals("/api/trip/notify_mates")) {
            jsonObject.put("trip_id", BaseEndpoints.tripId);
		}
        
        // Send POST request
        result = requestSpecification.body(jsonObject.toString())
                                     .post(application_ENDPOINT_PATH);

        // Print response
        System.out.println("Response: " + result.getBody().asPrettyString());

        // --- Extract and store trip_id ---
        if (application_ENDPOINT_PATH.equals("/api/trip/create_trip")) {
        JSONObject jsonResponse = new JSONObject(result.getBody().asPrettyString());
        if (jsonResponse.has("data")) {
	            BaseEndpoints.tripId = jsonResponse.getJSONObject("data").optInt("id");
	            System.out.println("Saved Trip ID: " + BaseEndpoints.tripId);
        }
            }
    	}
       
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }

}
