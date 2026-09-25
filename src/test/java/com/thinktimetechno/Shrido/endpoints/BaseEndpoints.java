package com.thinktimetechno.Shrido.endpoints;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import static org.hamcrest.Matchers.containsString;
import com.thinktimetechno.constants.FrameworkConstants;
import com.thinktimetechno.utils.FailedApiTracker;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class BaseEndpoints {
	public static final int SUCCESS_STATUS_CODE = 200;

	public static final int GET_REQUEST = 0;
	public static final int POST_REQUEST = 1;
	public static final int DELETE_REQUEST = 2;
	public static final int PUT_REQUEST = 3;
	public static String tokenkey=null;
	protected String application_ENDPOINT_PATH = "";
	protected String apiNameIdentifier = "";
	protected static String cardID = "";
	protected static String customerID = "";
	
	//From the Shrido registration API
	public static String inheritAuthToken;
    public static int token;
    public static String otpCode;
    public static String affiliateCode;
    public static String email;
    public static String dynamicMobile;
    public static String refreshToken;
    public static int peerUserId;
    public static String roomId;
    public static int userId;       
    public static int targetMateId = 290; 
    
    //From the Shrido Trip creation API
    public static int tripId;
    public static int request_tripId;
    public static String tripStartDate;
    
    
      //	protected final String base_url = "https://reqres.in/api/users";
		protected final String base_url = "https://perfbkend.solarion.ai";
	public BaseEndpoints() {
	}

	    

	
	public void verifyResponseKeyValues(String key, String val, Response r) {
		String keyValue = r.jsonPath().getString(key);
		assertThat(keyValue, is(val));
	}
	public void verifyResponseKeyValues(DataTable dataTable, Response response) {
	    int actualStatusCode = response.getStatusCode(); 
	    try {
	        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

	        for (Map<String, String> row : rows) {
	            String key = row.get("Key");
	            String expectedValue = row.get("Value");

	            String actualValue = response.jsonPath().getString(key);

	            if (actualValue == null) {
	                actualValue = response.jsonPath().getString("data." + key);
	            }

	            if (actualValue != null) actualValue = actualValue.trim();
	            if (expectedValue != null) expectedValue = expectedValue.trim();

	            if ("message".equalsIgnoreCase(key) && !expectedValue.isEmpty()) {
	                assertThat("Key: " + key, actualValue, containsString(expectedValue));
	            } else {
	                assertThat("Key: " + key, actualValue, is(expectedValue));
	            }
	        }
	    } 
	    catch (AssertionError e) {
	        // Assertion failure
	        FailedApiTracker.logFailure(
	            apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
	            actualStatusCode + " & " + e.getClass().getSimpleName()
	        );
	        throw e;
	    } 
	    catch (Exception e) {
	        // Any other runtime exception
	        FailedApiTracker.logFailure(
	            apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
	            actualStatusCode + " & " + e.getClass().getSimpleName()
	        );
	        throw e;
	    }
	}
	public String verifyTokenResponseKeyValues(String key,Response r) {
		tokenkey = r.jsonPath().getString(key);
		return tokenkey;
	}

	public void verifyTrue(boolean val) {
		assertTrue(val);
	}

	public void verifyFalse(boolean val) {
		assertFalse(val);
		;
	}

	public void verifyResponseStatusValue(Response response, int expectedCode) {
	    try {
	        assertThat(response.getStatusCode(), is(expectedCode));
	    } catch (AssertionError e) {
	        // Log the failure
	    	FailedApiTracker.logFailure(apiNameIdentifier, String.valueOf(response.getStatusCode()));
	        throw e; // Still fail the test
	    }
	}

	public String getBaseUrl() {
		return this.base_url;
	}

	public void verifyNestedResponseKeyValues(String nestTedCompnent, String key, String val, Response r) {
		Map<String, String> nestedJSON = r.jsonPath().getMap(nestTedCompnent);
		String actual = String.valueOf(nestedJSON.get(key));
		assertThat(actual, is(val));
	}

	public void verifyNestedArrayValueResponseKeyValues(String nestTedCompnent, String[] val, Response r) {

		ArrayList<Object> nestedArray = (ArrayList<Object>) r.jsonPath().getList(nestTedCompnent);

		String actual;

		for (int i = 0; i < nestedArray.size(); i++) {
			actual = (String) nestedArray.get(i);
			assertThat(actual, is(val[i]));
		}
	}

	public void verifyNestedArrayMapResponseKeyValues(String nestTedCompnent, String key, String[] val, Response r) {
		ArrayList<Object> nestedArray = (ArrayList<Object>) r.jsonPath().getList(nestTedCompnent);

		String actual;
		for (int i = 0; i < nestedArray.size(); i++) {
			Map<String, String> map = (Map<String, String>) nestedArray.get(i);
			actual = String.valueOf(map.get(key));
			assertThat(actual, is(val[i]));
		}
	}
	
	public RequestSpecification getRequestWithJSONHeadersToken() {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + FrameworkConstants.STATIC_TOKEN);
		return r;
	}


	public RequestSpecification getRequestWithJSONHeaders(String token) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + token);
//		.contentType(ContentType.JSON)
        
		return r;
	}
	public RequestSpecification getRequestWithJSONHeadersToken(String token) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + token);
//		.contentType(ContentType.JSON)
        
		return r;
	}
	
	
	//Shrido API requests
	
	public RequestSpecification getRequestWithJSONHeader(String endpointPath) {
	    RequestSpecification r = RestAssured.given();

	    Set<String> multipartEndpoints = Set.of(
	    	    "/api/car/add_car",
	    	    "/api/driver/add_license",
	    	    "/api/user/add_identification",
	    	    "/api/user/profile_update",
	    	    "/api/settings/add-email-templates",
	    	    "/api/settings/predefine_message",
	    	    "/api/settings/update-email-templates"
	    	);
	    if (!multipartEndpoints.contains(endpointPath)) {
	        r.header("Content-Type", "application/json");
	        r.header("Accept", "application/json");
	    }

	    if (!endpointPath.equals("/api/user/register") && BaseEndpoints.inheritAuthToken != null) {
	        r.header("x-access-token", BaseEndpoints.inheritAuthToken);
	    }

	    return r;
	}
	
	public RequestSpecification getRequestWithJSONHeader(String endpointPath,String jsonfile) {
	    RequestSpecification r = RestAssured.given();

	    Set<String> multipartJsonfile = Set.of(
	    	 "Verify_Card.json",
	    	 "Get Card Info.json",
	    	 "New Request.json",
	    	 "Add Sms Template.json"
	    	);
	    
	    if (!multipartJsonfile.contains(jsonfile)) {
	        r.header("Content-Type", "application/json");
	        r.header("Accept", "application/json");
	    }

	    if (BaseEndpoints.inheritAuthToken != null) {
	        r.header("x-access-token", BaseEndpoints.inheritAuthToken);
	    }

	    return r;
	}

	public RequestSpecification getRequestWithJSONHeaders(String P,String S) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header(P, S);
		return r;
	}

	public RequestSpecification getRequestWithXMLHeaders() {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/xml");
		return r;
	}

	protected JSONObject createJSONPayload(Object pojo) {
		return new JSONObject(pojo);
	}
//	public JSONObject createJSONPayload(Object pojo) {
//		return new JSONObject(pojo);
//	}
	
	
	
	public JSONObject getUpdatedPayload(String filePath) throws IOException {
		   String content = new String(Files.readAllBytes(new File(filePath).toPath()));
		    JSONObject jsonObject = new JSONObject(content);

		    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		    String updatedName = "TestFolder_" + timeStamp;

		    // ✅ Case 1: Direct "name" key in root object
		    if (jsonObject.has("name")) {
		        jsonObject.put("name", updatedName);
		    }

		    // ✅ Case 2: Inside objects array -> set name for each object
		    if (jsonObject.has("objects")) {
		        JSONArray objects = jsonObject.getJSONArray("objects");
		        for (int i = 0; i < objects.length(); i++) {
		            JSONObject obj = objects.getJSONObject(i);
		            if (obj.has("name")) {
		                obj.put("name", updatedName); // or add "_i" to name if multiple needed
		            }
		        }
		    }

		    return jsonObject;
	  
	}


	public Response sendRequest(RequestSpecification request, int requestType, String url, Object pojo) {
		Response response = null;

		// Add the Json to the body of the request
		//if (null != pojo) {
			//String payload = createJSONPayload(pojo).toString();
			request.body(pojo);
		//}

		// need to add a switch based on request type
		switch (requestType) {
		case BaseEndpoints.GET_REQUEST:
			if (null == request) {
				response = RestAssured.when().get(url);
			} else {
				response = request.get(url);
			}
			break;
		case BaseEndpoints.POST_REQUEST:
			if (null == request) {
				response = RestAssured.when().post(url);
			} else {
				response = request.post(url);
			}
			break;
		case BaseEndpoints.DELETE_REQUEST:
			if (null == request) {
				response = RestAssured.when().delete(url);
			} else {
				response = request.delete(url);
			}
			break;
		case BaseEndpoints.PUT_REQUEST:
			if (null == request) {
				response = RestAssured.when().put(url);
			} else {
				response = request.put(url);
			}
			break;
		default:
			if (null == request) {
				response = RestAssured.when().post(url);
			} else {
				response = request.post(url);
			}
			response = request.post(url);
			break;
		}
		return response;
	}
}
