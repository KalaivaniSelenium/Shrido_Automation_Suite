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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OnboardingEndpoints extends BaseEndpoints{

    private RequestSpecification requestSpecification;
    public Response result;
    public static String authToken;  
     
    
    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

    	try {
      
        switch (jsonFileName) {
            case "user register.json":
                application_ENDPOINT_PATH = "/api/user/register";
                break;
            case "Otp verify.json":
                application_ENDPOINT_PATH = "/api/otp/verify";
                break;
            case "Resend Otp.json":
                application_ENDPOINT_PATH = "/api/otp/resend_otp";
                break;
            case "Add Role.json":
                application_ENDPOINT_PATH = "/api/user/role_verification";
                break;
            case "Profile_Setup.json":
                application_ENDPOINT_PATH = "/api/user/profile_setup";
                break;
            case "Add car Detail.json":
                application_ENDPOINT_PATH = "/api/car/add_car";
                break;
            case "Driver Acknowledged.json":
                application_ENDPOINT_PATH = "/api/driver/acknowledgement";
                break;
            case "Resend Verification Link.json":
                application_ENDPOINT_PATH = "/api/user/resend_verification_link";
                break;    
            case "msg91 send sms.json":
				application_ENDPOINT_PATH = "/api/v5/flow/";
				break;
            case "Popups Seen.json":
                application_ENDPOINT_PATH = "/api/user/popups/mark_as_seen";
                break;
                
            default:
                throw new IllegalArgumentException("Endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");

        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

        // Case 1: Resend OTP → No body required
        if (application_ENDPOINT_PATH.equals("/api/otp/resend_otp")) {
            result = requestSpecification
            		.post(application_ENDPOINT_PATH);
        } 
        else if (application_ENDPOINT_PATH.equals("/api/car/add_car")) {
        	
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
        else if (application_ENDPOINT_PATH.equals("/api/driver/add_license")) {

            String filePath = System.getProperty("user.dir") + "/src/test/resources/Images/license.png";
            File licensePhoto = new File(filePath);

            if (!licensePhoto.exists()) {
                throw new FileNotFoundException("License photo not found at: " + filePath);
            }

            result = requestSpecification
                    .multiPart("license_photo", licensePhoto, "image/png")
                    .multiPart("license_number", "000000000000")
                    .multiPart("license_expire_date", "15/02/2024")
                    .multiPart("date_of_birth", "02/01/2002")
                    .multiPart("address_of_license", "fgsfgsf")
                    .post(application_ENDPOINT_PATH);
        }
        else if (application_ENDPOINT_PATH.equals("/api/user/add_identification")) {

            String identityCardPath = System.getProperty("user.dir") + "/src/test/resources/Images/identity_card.jpg";
            String selfiePhotoPath = System.getProperty("user.dir") + "/src/test/resources/Images/selfie_photo.png";

            File identityCard = new File(identityCardPath);
            File selfiePhoto = new File(selfiePhotoPath);
            result = requestSpecification
                    .multiPart("identity_card", identityCard, "image/jpeg")
                    .multiPart("selfie_photo", selfiePhoto, "image/png")
                    .post(application_ENDPOINT_PATH);
        }
        else {
            // Case 2: Other APIs → Read payload and modify if needed
        	
            String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/OnboardingPayloads/"+ jsonFileName;
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Dynamic mobile for registration
            if (application_ENDPOINT_PATH.equals("/api/user/register") && jsonObject.has("mobile")) {
                String timeComponent = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
                dynamicMobile = "+611" + timeComponent;
                jsonObject.put("mobile", dynamicMobile);
                System.out.println("Generated Mobile Number: " + dynamicMobile);
            }

            // Inject OTP/token for verification
            if (application_ENDPOINT_PATH.equals("/api/otp/verify")) {
                jsonObject.put("id", BaseEndpoints.token);
                jsonObject.put("otp", BaseEndpoints.otpCode);
            }
            if (application_ENDPOINT_PATH.equals("/api/user/logout")) {
                jsonObject.put("user_id", BaseEndpoints.userId);
            
            }
            
            //Adding new role
            if (application_ENDPOINT_PATH.equals("/api/user/role_verification") && jsonObject.has("role")) {
            	int randomRole = (int) (Math.random() * 6) + 3; // Generates 3 to 8
                jsonObject.put("role", randomRole);
                System.out.println("Generated Role: " + randomRole);
            }
            
            //Setting up profile
            if (application_ENDPOINT_PATH.equals("/api/user/profile_setup")) {
                
                String randomTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
                String dynamicEmail = "tester" + randomTime + "@yopmail.com";
                BaseEndpoints.email = dynamicEmail;
                jsonObject.put("email", dynamicEmail);
                jsonObject.put("name", "John Doe " + randomTime);
            }
            
            
            
            // Resends email verification link
            if (application_ENDPOINT_PATH.equals("/api/user/resend_verification_link")) {
                jsonObject.put("email", BaseEndpoints.email);  
            }

            // Send request with body
            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);
        }

        // Print response
        System.out.println("Response: " + result.getBody().asPrettyString());

        // Store values only after registration
        if (application_ENDPOINT_PATH.equals("/api/user/register")) {

            JSONObject jsonResponse = new JSONObject(result.getBody().asPrettyString());

            if (jsonResponse.has("data")) {

                JSONObject dataObject = jsonResponse.getJSONObject("data");
                JSONObject userObject = dataObject.getJSONObject("user");

                // Changed: JWToken → access_token
                BaseEndpoints.inheritAuthToken = userObject.optString("access_token");

                BaseEndpoints.token = dataObject.optInt("token");
                BaseEndpoints.otpCode = dataObject.optString("otp_code");
                BaseEndpoints.affiliateCode = userObject.optString("affiliate_code");
                BaseEndpoints.userId = userObject.optInt("id");

                // Avoid printing the actual access token
                System.out.println("Access token saved successfully.");
                System.out.println("Saved Token: " + BaseEndpoints.token);
                System.out.println("Saved OTP: " + BaseEndpoints.otpCode);
                System.out.println("Saved Referral code: " + BaseEndpoints.affiliateCode);
            }
        }
        
     // Update tokens after successful OTP verification
        if (application_ENDPOINT_PATH.equals("/api/otp/verify")
                && result.getStatusCode() == 200) {

            JSONObject jsonResponse =
                    new JSONObject(result.getBody().asPrettyString());

            JSONObject dataObject = jsonResponse.getJSONObject("data");

        //    BaseEndpoints.inheritAuthToken = dataObject.optString("access_token");

            BaseEndpoints.refreshToken = dataObject.optString("refresh_token");

            BaseEndpoints.userId = dataObject.optInt("id");

            System.out.println("OTP verification tokens updated.");
            System.out.println("Current User ID: " + BaseEndpoints.userId);
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
	        case "Get Referral History":
	            application_ENDPOINT_PATH = "/api/user/referral-history?page=0";
	            break;
	        case "Get Total Referrals":
	            application_ENDPOINT_PATH = "/api/user/total-referral";
	            break;
	        case "Email Verification":
	            application_ENDPOINT_PATH = "/api/user/verify?token=1265825b-79cb-4990-b5ff-c28938ad0438";
	            break;
	            
	        case "Get Intro Card of User":
	            application_ENDPOINT_PATH = "/api/user/intro_card/"+ BaseEndpoints.userId;
	            break;
	        case "About Me":
	            application_ENDPOINT_PATH = "/api/user/me";
	            break;
	        case "Get User Total Points":
	            application_ENDPOINT_PATH = "/api/user/total-points";
	            break;
	        case "Get User Details Using User Id":
	            application_ENDPOINT_PATH = "/api/user/details?userId="+ BaseEndpoints.userId;
	            break;
	        
	    }

	    this.apiNameIdentifier = APIName;
	    return result = requestSpecification.get(application_ENDPOINT_PATH);
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
            case "Update Profile.json":
                application_ENDPOINT_PATH = "/api/user/profile_update";
                break;
            default:
                throw new IllegalArgumentException("PUT endpoint not defined for file: " + jsonFileName);
        }
        this.apiNameIdentifier = jsonFileName.replace(".json", "");

        // Prepare request
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);

        if (application_ENDPOINT_PATH.equals("/api/user/profile_update")) {
            String dynamicEmail = BaseEndpoints.email;
            result = requestSpecification
                    .multiPart("email", dynamicEmail)
                    .put(application_ENDPOINT_PATH);
        }
        else {
        	
        // Read JSON payload file
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/OnboardingPayloads/" + jsonFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        
        // Send PUT request
        result = requestSpecification
                .body(jsonObject.toString())
                .put(application_ENDPOINT_PATH);
        }
        System.out.println("Response: " + result.getBody().asPrettyString());}
    	catch (Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
                                        exceptionName);
            throw e;
        }}
    	
    public Response applicationSalesDeletePayload(String APIName ) {
    	try {
        requestSpecification = getRequestWithJSONHeader(application_ENDPOINT_PATH);
		switch (APIName){
		case "Delete User":
			application_ENDPOINT_PATH="/api/user/delete?mobile=" + dynamicMobile.replace("+", "");
			System.out.println(application_ENDPOINT_PATH);
			break;

		}
		this.apiNameIdentifier = APIName;
			result=requestSpecification.delete(application_ENDPOINT_PATH);
	     	return result;
    	}
	     	catch (Exception e) {
	            String exceptionName = e.getClass().getSimpleName();
	            FailedApiTracker.logFailure(apiNameIdentifier != null ? apiNameIdentifier : application_ENDPOINT_PATH,
	                                        exceptionName);
	            throw e;
	        }
    }}