package com.thinktimetechno.Shrido.endpoints;

import java.io.IOException;
import com.thinktimetechno.utils.FailedApiTracker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NotificationEndpoints extends BaseEndpoints {

	private RequestSpecification requestSpecification;
	public Response result;
	public static String authToken;
	public static String dynamicMobile;
	public static String tripStartDate;

	public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

		try {

			switch (jsonFileName) {
			case "Send Notification.json":
				application_ENDPOINT_PATH = "/api/user_notification/send_notification";
				break;
			
			default:
				throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
			}

			 this.apiNameIdentifier = jsonFileName.replace(".json", "");
			// Prepare request
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);


			// Send POST request
			result = requestSpecification
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

	public Response sendGetRequest(String APIName) throws Exception {
		try {
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

			switch (APIName) {
			case "All notification of User":
				application_ENDPOINT_PATH = "/api/user_notification/notifications?page=1";
				break;
			
		    case "Get Notification Detail":
			    application_ENDPOINT_PATH = "/api/user_notification/notification?id="+BaseEndpoints.userId;
			    break;
		
	        case "Get UnRead Notification Count":
			    application_ENDPOINT_PATH = "/api/user_notification/unreadCount";
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

	public Response sendDeleteRequest(String APIName) {
		try {
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
			switch (APIName) {
			case "Delete Notification":
				application_ENDPOINT_PATH = "/api/user_notification/0";
				break;
			}
			this.apiNameIdentifier = APIName;
			result = requestSpecification
					.delete(application_ENDPOINT_PATH);
			return result;
			
			 
		} catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
	}


}