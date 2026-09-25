package com.thinktimetechno.Shrido.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RewardsEndpoints extends BaseEndpoints {

	private RequestSpecification requestSpecification;
	public Response result;
	public static String authToken;

	public Response sendGetRequest(String APIName) {
		try {
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

			switch (APIName) {
			case "Get Gift Cards":
				application_ENDPOINT_PATH = "/api/gift-card";
				break;
			case "Live Url for get Gift Cards":
				application_ENDPOINT_PATH = "/api/gift-card";
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
			case "Redeem gift card.json":
				application_ENDPOINT_PATH = "/api/gift-card/redeem";
				break;

			default:
				throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
			}
			 this.apiNameIdentifier = jsonFileName.replace(".json", "");
			// Prepare request
			requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

			String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/GiftCardsPayloads/"
					+ jsonFileName;
			String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
			JSONObject jsonObject = new JSONObject(jsonContent);
			
			result = requestSpecification
					.body(jsonObject.toString())
					.post(application_ENDPOINT_PATH);

		} catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }
	}
}