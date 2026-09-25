package com.thinktimetechno.Shrido.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedBackEndpoints extends BaseEndpoints {

	private RequestSpecification requestSpecification;
	public Response result;
	public static String authToken;



	public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

		try {

			switch (jsonFileName) {
			case "Add FeedBack.json":
				application_ENDPOINT_PATH = "/api/user/feedback";
				break;
			  case "LogOut.json":
	                application_ENDPOINT_PATH = "/api/user/logout";
	                break;
			default:
				throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
			}
			 this.apiNameIdentifier = jsonFileName.replace(".json", "");
			// Prepare request
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
			
			

			String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/FeedBackPayloads/"
					+ jsonFileName;
			String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
			JSONObject jsonObject = new JSONObject(jsonContent);
			
			  if (application_ENDPOINT_PATH.equals("/api/user/logout")) {
	                jsonObject.put("user_id", BaseEndpoints.userId);
	            
	            }
			
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