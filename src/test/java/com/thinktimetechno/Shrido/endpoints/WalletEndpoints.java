package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WalletEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
            case "Get Wallet History.json":
                application_ENDPOINT_PATH = "/api/wallet/history";
                break;
            case "Add Card.json":
                application_ENDPOINT_PATH = "/api/user/card/add";
                break;
            case "Change Card Expiry.json":
                application_ENDPOINT_PATH = "/api/user/card/change-expiry";
                break;
            case "Remove Card.json":
                application_ENDPOINT_PATH = "/api/user/card/remove-card";
                break;
            case "Set card as default.json":
                application_ENDPOINT_PATH = "/api/user/card/set-default";
                break;
            case "Add Subscribe plans.json":
				application_ENDPOINT_PATH = "/api/subscription/subscription-plans";
				break;
            case "Create Payment.json":
                application_ENDPOINT_PATH = "/api/payment";
                break;
            case "Verify receipt.json":
                application_ENDPOINT_PATH = "/api/payment/receipt/verify";
                break;
            case "Verify Subscription.json":
                application_ENDPOINT_PATH = "/api/payment/verify/subscription";
                break;
            case "Update Auto Refill and Renew Settings.json":
                application_ENDPOINT_PATH = "/api/user_settings/auto_refill_and_subscription";
                break;
                
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }

        this.apiNameIdentifier = jsonFileName.replace(".json", "");
	        // Prepare request
	        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);


            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/WalletPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);
            
            // Updating card id
            if (application_ENDPOINT_PATH.equals("/api/user/card/change-expiry")) {
                jsonObject.put("card_id", cardID);
    		}
  
            if (application_ENDPOINT_PATH.equals("/api/user/card/remove-card")) {
                jsonObject.put("card_id", cardID);
    		}
            
            if (application_ENDPOINT_PATH.equals("/api/user/card/set-default")) {
                jsonObject.put("card_id", cardID);
    		}
            
            //Payment card ID & customer ID
            if (application_ENDPOINT_PATH.equals("/api/payment")) {
                jsonObject.put("card_id", cardID);
                jsonObject.put("customer_id", customerID);
    		}

            // Send request with body
            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);

        // Print response
        System.out.println("Response: " + result.getBody().asPrettyString());
        
       // Store values only after adding Card
       if (application_ENDPOINT_PATH.equals("/api/user/card/add")) {
    	        cardID = result.jsonPath().getString("data.dataValues.card_id");
    	        customerID = result.jsonPath().getString("data.customer_id");
               
                System.out.println("Fetched Card ID: " + cardID);
                System.out.println("Fetched Customer ID: " + customerID);
            }
        
        
    	}catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
    }
    
    public Response sendGetRequest(String APIName) {
    	try {
    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
	   
	    switch (APIName) {
	        case "Get all Cards Of User":
	            application_ENDPOINT_PATH = "/api/user/cards";
	            break;
	        case "Get All Subscription Plans":
				application_ENDPOINT_PATH = "/api/subscription/plans";
				break;

	        default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + APIName);
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
    
    public void sendPutRequestWithPayload(String jsonFileName) throws IOException {
    	
    	try {
        switch (jsonFileName) {
            case "Edit Subscription plan.json":
                application_ENDPOINT_PATH = "/api/subscription/subscription-plans";
                break;
            default:
                throw new IllegalArgumentException("PUT endpoint not defined for file: " + jsonFileName);
        }

        this.apiNameIdentifier = jsonFileName.replace(".json", "");
        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);


        	
        // Read JSON payload file
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/WalletPayloads/" + jsonFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        
        // Send PUT request
        result = requestSpecification
                .body(jsonObject.toString())
                .put(application_ENDPOINT_PATH);
        
        System.out.println("Response: " + result.getBody().asPrettyString());}
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }}

}
    
   