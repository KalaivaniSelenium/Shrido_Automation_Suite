//package com.thinktimetechno.Shrido.endpoints;
//
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.json.JSONObject;
//
//import com.thinktimetechno.utils.FailedApiTracker;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class RidesEndpoints extends BaseEndpoints{
//
//    private RequestSpecification requestSpecification;
//    public Response result;
//    public static String authToken;  
//    
//    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {
//
//        try {
//
//            switch (jsonFileName) {
//            
//                case "Search all trip.json":
//	                application_ENDPOINT_PATH = "/api/trip/search";
//	                break;
//                case "History of Trips.json":
//	                application_ENDPOINT_PATH = "/api/trip/trip_history";
//	                break;
//                case "Send trip Request.json":
//                    application_ENDPOINT_PATH = "/api/trip/trips/" + BaseEndpoints.tripId + "/send_request";
//                    break;
//                case "Request accepted.json":
//                    application_ENDPOINT_PATH = "/api/request/" + BaseEndpoints.request_tripId + "/request_accept";
//                    break;
//                case "Request Reject.json":
//                    application_ENDPOINT_PATH = "/api/request/" + BaseEndpoints.request_tripId + "/request_reject";
//                    break;
//                case "All request.json":
//                    application_ENDPOINT_PATH = "/api/request/get_trips_with_filter";
//                    break;
//                case "All Confirmed Request of User.json":
//                    application_ENDPOINT_PATH = "/api/request/all_confirmed_requests";
//                    break;
//                default:
//                    throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
//            }
//            this.apiNameIdentifier = jsonFileName.replace(".json", "");
//            // Prepare request
//            requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
//
//            // ---- CASE 1: No body required for Request Reject ----
//            if (application_ENDPOINT_PATH.equals("/api/request/" + BaseEndpoints.request_tripId + "/request_reject")) {
//                result = requestSpecification.post(application_ENDPOINT_PATH);
//            }
//
//            // ---- CASE 2: Handle APIs with body ----
//            else {
//                String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/RidesPayloads/" + jsonFileName;
//                String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
//                JSONObject jsonObject = new JSONObject(jsonContent);
//
//                // Updating trip date for filter endpoints
//                if (application_ENDPOINT_PATH.equals("/api/request/get_trips_with_filter")) {
//                    jsonObject.put("date", tripStartDate);
//                }
//                if (application_ENDPOINT_PATH.equals("/api/request/all_confirmed_requests")) {
//                    jsonObject.put("date", tripStartDate);
//                }
//                // Getting History of trips
//                if (application_ENDPOINT_PATH.equals("/api/trip/trip_history")) {
//                    jsonObject.put("trip_date", tripStartDate);
//        		}
//
//                // Send request with body
//                result = requestSpecification
//                        .body(jsonObject.toString())
//                        .post(application_ENDPOINT_PATH);
//
//                // --- Extract and store request trip_id ---
//                if (application_ENDPOINT_PATH.equals("/api/trip/trips/" + BaseEndpoints.tripId + "/send_request")) {
//                    JSONObject jsonResponse = new JSONObject(result.getBody().asPrettyString());
//                    if (jsonResponse.has("data")) {
//                        BaseEndpoints.request_tripId = jsonResponse.getJSONObject("data").optInt("id");
//                        System.out.println("Saved Request Trip ID: " + BaseEndpoints.request_tripId);
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            String exceptionName = e.getClass().getSimpleName();
//            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
//                                        exceptionName);
//            throw e;
//        }
//    }
//    
//    
//    public Response sendGetRequest(String APIName) throws Exception {
//    	try {
//    	 requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
//	   
//	    switch (APIName) {
//	        case "Get All Dates of Trips":
//	            application_ENDPOINT_PATH = "/api/trip/trip_dates";
//	            break;  
//	    }
//	    this.apiNameIdentifier = APIName;
//	    return result = requestSpecification
//	    		       .get(application_ENDPOINT_PATH);
//    	}
//	    
//	    catch (Exception e) {
//		    String exceptionName = e.getClass().getSimpleName();
//		    FailedApiTracker.logFailure(application_ENDPOINT_PATH, exceptionName);
//		    System.err.println("Error occurred while sending POST request for: " + APIName);
//		    throw e;
//    }}
//    	
//    	
//    	
//    	public Response sendPatchRequestWithPayload(String jsonFileName) throws IOException {
//    	    try {
//    	        switch (jsonFileName) {
//    	            case "Re-Schedule trip time.json":
//    	                application_ENDPOINT_PATH = "/api/trip/re_schedule_trip_time";
//    	                break;
//    	            default:
//    	                throw new IllegalArgumentException("PATCH endpoint not defined for file: " + jsonFileName);
//    	        }
//
//    	        this.apiNameIdentifier = jsonFileName.replace(".json", "");
//    	        // Prepare request
//    	        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
//
//    	        // Load JSON payload
//    	        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/RidesPayloads/" + jsonFileName;
//    	        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
//    	        JSONObject jsonObject = new JSONObject(jsonContent);
//
//    	        if (application_ENDPOINT_PATH.equals("/api/trip/re_schedule_trip_time")) {
//    	        jsonObject.put("trip_id", BaseEndpoints.tripId);
//    	        jsonObject.put("start_date", tripStartDate);
//
//    	        // Dynamic start time (current time + 1 hour)
//    	        String futureTime = LocalDateTime.now().plusHours(1)
//    	                             .format(DateTimeFormatter.ofPattern("hh:mma"));
//    	        jsonObject.put("start_desired_time", futureTime);
//    	        jsonObject.put("start_latest_time", futureTime);
//    	        }
//    	        
//    	        // Send PATCH request
//    	        result = requestSpecification
//    	        		.body(jsonObject.toString())
//    	                .patch(application_ENDPOINT_PATH);
//
//    	        // Print response
//    	        System.out.println("Response: " + result.getBody().asPrettyString());
//    	        return result;
//    	    }
//    	    catch (Exception e) {
//                String exceptionName = e.getClass().getSimpleName();
//                FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
//                                            exceptionName);
//                throw e;
//            }
//	}
//    
//    	
//    public Response sendDeleteRequest(String APIName ) {
//    	try {
//		switch (APIName){
//		case "Delete trip":
//			application_ENDPOINT_PATH="/api/trip/"+BaseEndpoints.tripId;
//			 break;
//		case "Request Cancel":
//		    application_ENDPOINT_PATH =
//		            "/api/request/" + BaseEndpoints.request_tripId + "/request_cancel";
//		    break;		 
//		}
//		this.apiNameIdentifier = APIName;
//        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
//
//			result = requestSpecification
//					.delete(application_ENDPOINT_PATH);
//	     	return result;
//            }
//    	catch (Exception e) {
//            String exceptionName = e.getClass().getSimpleName();
//            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
//                                        exceptionName);
//            throw e;
//        }
//    }
//
//}





package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RidesEndpoints extends BaseEndpoints {

    private RequestSpecification requestSpecification;

    public Response result;

    public static String authToken;


    // =========================================================
    // POST REQUESTS
    // =========================================================

    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

        try {

            // -------------------------------------------------
            // 1. Set API endpoint based on JSON file name
            // -------------------------------------------------

            switch (jsonFileName) {

                case "Search all trip.json":
                    application_ENDPOINT_PATH = "/api/trip/search";
                    break;

                case "History of Trips.json":
                    application_ENDPOINT_PATH = "/api/trip/trip_history";
                    break;

                case "Send trip Request.json":
                    application_ENDPOINT_PATH =
                            "/api/trip/trips/" + BaseEndpoints.tripId + "/send_request";
                    break;

                case "Request accepted.json":
                    application_ENDPOINT_PATH =
                            "/api/request/" + BaseEndpoints.request_tripId + "/request_accept";
                    break;

                case "Request Reject.json":
                    application_ENDPOINT_PATH =
                            "/api/request/" + BaseEndpoints.request_tripId + "/request_reject";
                    break;

                case "All request.json":
                    application_ENDPOINT_PATH = "/api/request/get_trips_with_filter";
                    break;

                case "All Confirmed Request of User.json":
                    application_ENDPOINT_PATH = "/api/request/all_confirmed_requests";
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Endpoint not defined for file: " + jsonFileName);
            }


            this.apiNameIdentifier = jsonFileName.replace(".json", "");


            // -------------------------------------------------
            // 2. Prepare request specification
            // -------------------------------------------------

            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            // -------------------------------------------------
            // 3. Request Reject API - no request body
            // -------------------------------------------------

            if (jsonFileName.equals("Request Reject.json")) {

                result = requestSpecification.post(application_ENDPOINT_PATH);

            }


            // -------------------------------------------------
            // 4. Other POST APIs - load JSON payload
            // -------------------------------------------------

            else {

                String filePath = System.getProperty("user.dir")
                        + "/src/test/resources/Payloads/RidesPayloads/"
                        + jsonFileName;

                String jsonContent =
                        new String(Files.readAllBytes(Paths.get(filePath)));

                JSONObject jsonObject = new JSONObject(jsonContent);


                // -------------------------------------------------
                // 5. Send Trip Request - update trip ID and date
                // -------------------------------------------------

                if (jsonFileName.equals("Send trip Request.json")) {

                    jsonObject.put("trip_id", BaseEndpoints.tripId);

                    jsonObject.put("trip_date", BaseEndpoints.tripStartDate);
                }


                // -------------------------------------------------
                // 6. Request Accepted - update driver trip ID
                // -------------------------------------------------

                if (jsonFileName.equals("Request accepted.json")) {

                    jsonObject.put("driver_trip_id", BaseEndpoints.tripId);
                }


                // -------------------------------------------------
                // 7. Update date for All Request API
                // -------------------------------------------------

                if (jsonFileName.equals("All request.json")) {

                    jsonObject.put("date", BaseEndpoints.tripStartDate);
                }


                // -------------------------------------------------
                // 8. Update date for All Confirmed Requests API
                // -------------------------------------------------

                if (jsonFileName.equals("All Confirmed Request of User.json")) {

                    jsonObject.put("date", BaseEndpoints.tripStartDate);
                }


                // -------------------------------------------------
                // 9. Update date for Trip History API
                // -------------------------------------------------

                if (jsonFileName.equals("History of Trips.json")) {

                    jsonObject.put("trip_date", BaseEndpoints.tripStartDate);
                }


                // -------------------------------------------------
                // 10. Send POST request with JSON body
                // -------------------------------------------------

                result = requestSpecification
                        .body(jsonObject.toString())
                        .post(application_ENDPOINT_PATH);


                // -------------------------------------------------
                // 11. Capture Request ID from Send Request response
                // -------------------------------------------------

                if (jsonFileName.equals("Send trip Request.json")
                        && result.getStatusCode() >= 200
                        && result.getStatusCode() < 300) {

                    JSONObject jsonResponse =
                            new JSONObject(result.getBody().asString());

                    if (jsonResponse.has("data")
                            && !jsonResponse.isNull("data")) {

                        JSONObject dataObject =
                                jsonResponse.getJSONObject("data");

                        BaseEndpoints.request_tripId =
                                dataObject.optInt("id", 0);

                        System.out.println(
                                "Saved Request ID: " + BaseEndpoints.request_tripId);

                    } else {

                        System.out.println(
                                "Send Request succeeded, but response data was missing.");
                    }
                }
            }


            // -------------------------------------------------
            // 12. Print response status and body for debugging
            // -------------------------------------------------

            System.out.println("API: " + apiNameIdentifier);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println("Response Body: "
                    + result.getBody().asPrettyString());


        } catch (Exception e) {

            String exceptionName = e.getClass().getSimpleName();

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    exceptionName);

            throw e;
        }
    }



    // =========================================================
    // GET REQUESTS
    // =========================================================

    public Response sendGetRequest(String APIName) throws Exception {

        try {

            // Set endpoint BEFORE creating request specification

            switch (APIName) {

                case "Get All Dates of Trips":
                    application_ENDPOINT_PATH = "/api/trip/trip_dates";
                    break;

                default:
                    throw new IllegalArgumentException(
                            "GET endpoint not defined for: " + APIName);
            }


            this.apiNameIdentifier = APIName;


            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            result = requestSpecification.get(application_ENDPOINT_PATH);


            System.out.println("API: " + APIName);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println("Response Body: "
                    + result.getBody().asPrettyString());


            return result;


        } catch (Exception e) {

            String exceptionName = e.getClass().getSimpleName();

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    exceptionName);

            throw e;
        }
    }



    // =========================================================
    // PATCH REQUESTS
    // =========================================================

    public Response sendPatchRequestWithPayload(String jsonFileName)
            throws IOException {

        try {

            // -------------------------------------------------
            // 1. Set PATCH endpoint
            // -------------------------------------------------

            switch (jsonFileName) {

                case "Re-Schedule trip time.json":
                    application_ENDPOINT_PATH = "/api/trip/re_schedule_trip_time";
                    break;

                default:
                    throw new IllegalArgumentException(
                            "PATCH endpoint not defined for file: " + jsonFileName);
            }


            this.apiNameIdentifier = jsonFileName.replace(".json", "");


            // -------------------------------------------------
            // 2. Prepare request
            // -------------------------------------------------

            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            // -------------------------------------------------
            // 3. Load JSON payload
            // -------------------------------------------------

            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/Payloads/RidesPayloads/"
                    + jsonFileName;

            String jsonContent =
                    new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject jsonObject = new JSONObject(jsonContent);


            // -------------------------------------------------
            // 4. Update trip ID, date and time dynamically
            // -------------------------------------------------

            if (jsonFileName.equals("Re-Schedule trip time.json")) {

                jsonObject.put("trip_id", BaseEndpoints.tripId);

                jsonObject.put("start_date", BaseEndpoints.tripStartDate);


                String futureTime = LocalDateTime.now()
                        .plusHours(1)
                        .format(DateTimeFormatter.ofPattern("hh:mma"));

                jsonObject.put("start_desired_time", futureTime);

                jsonObject.put("start_latest_time", futureTime);
            }


            // -------------------------------------------------
            // 5. Send PATCH request
            // -------------------------------------------------

            result = requestSpecification
                    .body(jsonObject.toString())
                    .patch(application_ENDPOINT_PATH);


            System.out.println("API: " + apiNameIdentifier);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println("Response Body: "
                    + result.getBody().asPrettyString());


            return result;


        } catch (Exception e) {

            String exceptionName = e.getClass().getSimpleName();

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    exceptionName);

            throw e;
        }
    }



    // =========================================================
    // DELETE REQUESTS
    // =========================================================

    public Response sendDeleteRequest(String APIName) {

        try {

            // -------------------------------------------------
            // 1. Set DELETE endpoint
            // -------------------------------------------------

            switch (APIName) {

                case "Delete trip":

                    application_ENDPOINT_PATH =
                            "/api/trip/" + BaseEndpoints.tripId;

                    break;


                case "Request Cancel":

                    application_ENDPOINT_PATH =
                            "/api/request/"
                                    + BaseEndpoints.request_tripId
                                    + "/request_cancel";

                    break;


                default:

                    throw new IllegalArgumentException(
                            "DELETE endpoint not defined for: " + APIName);
            }


            this.apiNameIdentifier = APIName;


            // -------------------------------------------------
            // 2. Prepare request and send DELETE
            // -------------------------------------------------

            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            result = requestSpecification.delete(application_ENDPOINT_PATH);


            System.out.println("API: " + APIName);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println("Response Body: "
                    + result.getBody().asPrettyString());


            return result;


        } catch (Exception e) {

            String exceptionName = e.getClass().getSimpleName();

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    exceptionName);

            throw e;
        }
    }

}