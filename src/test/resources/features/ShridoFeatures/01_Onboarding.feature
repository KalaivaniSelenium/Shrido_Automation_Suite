@Onboarding @Shrido
Feature: Onboarding  
This feature includes Onboarding tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for User
	
	@Register
	Scenario: TC-01 Register a New User Successfully (POST {{liveUrl}}/api/user/register)
	When The User sends a POST request for User with the request body from "user register.json" and captures the response body
	Then The response code for User should be "201"
	And The response body for User should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 201                           |
  | status  | true                          |
  | message | User Registered Successfully. |
	
	@OTPVerify
	Scenario: TC-02 Verify OTP (POST {{liveUrl}}/api/otp/verify)
	When The User sends a POST request for User with the request body from "Otp verify.json" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Otp Verified Successfully.    |
	
	@ResendOTP
	Scenario: TC-03 Resend OTP (POST {{liveUrl}}/api/otp/resend_otp)
	When The User sends a POST request for User with the request body from "Resend Otp.json" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Otp sent successfully.        |
  
  @Addrole
	Scenario: TC-04 Add Role (POST {{liveUrl}}/api/user/role_verification)
	When The User sends a POST request for User with the request body from "Add Role.json" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
	| Key     | Value          |
  | code    | 200            |
  | status  | true           |
	| message | User role set. |
  
  @ProfileSetup 
	Scenario: TC-05 Profile Setup (POST {{liveUrl}}/api/user/profile_setup)
	When The User sends a POST request for User with the request body from "Profile_Setup.json" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Profile Updated Successfully. |
  
  @EmailVerify
	Scenario: TC-06 Email Verification (GET {{liveUrl}}/api/user/verify?token={token})
	When The User sends a GET request for User from "Email Verification" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Email Verified Successfully              |
  
  @ResendVerify
	Scenario: TC-07 Resend Email Verification Link (POST {{liveUrl}}/api/user/resend_verification_link)
	When The User sends a POST request for User with the request body from "Resend Verification Link.json" and captures the response body
	Then The response code for User should be "200"
	And The response body for User should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  | message | Verification link sent to              |
  
  @PopupSeen
    Scenario: TC-08 Mark Welcome Popup as Seen (POST {{liveUrl}}/api/user/popups/mark_as_seen)
    When The User sends a POST request for User with the request body from "Popups Seen.json" and captures the response body
    Then The response code for User should be "200"
    And The response body for User should contain the following key-value pairs:

  | Key     | Value                            |
  | code    | 200                              |
  | status  | true                             |
  | message | Welcome points awarded successfully! |
  
  #@msg91
  #Scenario: TC-08 msg91 send sms
  #Given The User sets the base URL as "https://control.msg91.com" for msg91
#	When The User sends a POST request for User with the request body from "msg91 send sms.json" and captures the response body
#	Then The response code for User should be "200"
#	And The response body for User should contain the following key-value pairs:
  #| Key     | Value                         |
  #| type    | success                       |
  