package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DrivingEssentialsEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    public static String dynamicMobile; 
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
           
            case "Add car Detail.json":
                application_ENDPOINT_PATH = "/api/car/add_car";
                break;
            case "Driver Acknowledged.json":
                application_ENDPOINT_PATH = "/api/driver/acknowledgement";
                break;
            case "Threshold Create.json":
                application_ENDPOINT_PATH = "/api/car/create_threshold";
                break;
            case "Add Threshold Values Limit.json":
                application_ENDPOINT_PATH = "/api/settings/threshold-limit";
                break;
                
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");

        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

      if (application_ENDPOINT_PATH.equals("/api/car/add_car")) {
        	
            String filePath = System.getProperty("user.dir") + "/src/test/resources/Images/car01.jpg";
            File carPhoto = new File(filePath);

            if (!carPhoto.exists()) {
                throw new FileNotFoundException("Car image not found at: " + filePath);
            }
            
        	int randomDigits = 1000 + (int) (Math.random() * 9000); // 1000 to 9999
            String dynamicCarReg = "CAR" + randomDigits;  
            // Case 2: Add Car API → form-data (multipart)
            result = requestSpecification
                    .multiPart("car_registration", dynamicCarReg)
                    .multiPart("car_brand_name", "TaTa")
                    .multiPart("car_model_name", "swift")
                    .multiPart("color", "Red")
                    // Upload image file (place a sample file in your resources/images folder)
                    .multiPart("car_photo", carPhoto, "image/jpeg")
                    .post(application_ENDPOINT_PATH);

        } 
       
        else {
            // Case 2: Other APIs → Read payload and modify if needed
        	
            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/DrivingEssentialsPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Send POST request
            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);
        // Print response
        System.out.println("Response: " + result.getBody().asPrettyString());

        }
    	}catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
    
  }