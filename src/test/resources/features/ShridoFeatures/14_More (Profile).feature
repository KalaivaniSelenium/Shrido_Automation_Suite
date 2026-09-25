@Profile @Shrido
Feature: More (Profile)  
This feature includes User tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Profile

  @UpdateProfile
  Scenario: TC-01 Update Profile (PUT {{liveUrl}}/api/user/profile_update)
  When The User sends a PUT request for Profile with the request body from "Update Profile.json" and captures the response body
  Then The response code for Profile should be "200"
	And The response body for Profile should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  | message | Profile Update...                      |
  
  @AboutMe
	Scenario: TC-02 About Me (GET {{liveUrl}}/api/user/me)
	When The User sends a GET request for Profile from "About Me" and captures the response body
	Then The response code for Profile should be "200"
	And The response body for Profile should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Data of User...               |
  
  @UserDetailsUsingUserId
  Scenario: TC-03 Get User Details Using User Id (GET {{liveUrl}}/api/user/details?userId={id})
	When The User sends a GET request for Profile from "Get User Details Using User Id" and captures the response body
	Then The response code for Profile should be "200"
	And The response body for Profile should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Data of User...               |
  
  @DeleteUser
  Scenario: TC-04 Delete User (DELETE {{liveUrl}}/api/user/delete?mobile={number})
	When The User sends a DELETE request for Profile from "Delete User" and captures the response body
	Then The response code for Profile should be "200"
	And The response body for Profile should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Data of User...               |
  
 
  
  