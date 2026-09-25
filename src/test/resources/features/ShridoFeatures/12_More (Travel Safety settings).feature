@TravelSafety @Shrido
Feature: More (Travel Safety settings)  
This feature includes Onboarding tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for TravelSafety
 
  @TravelSafety
  Scenario: TC-01 Travel Safety (POST {{liveUrl}}/api/user_settings/travel-safety)
	When The User sends a POST request for TravelSafety with the request body from "Travel Safety.json" and captures the response body
	Then The response code for TravelSafety should be "200"
	And The response body for TravelSafety should contain the following key-value pairs:
		  | Key     | Value                                  |
		  | code    | 200                                    |
		  | status  | true                                   |
		  | message | Travel safety settings updated successfully.      |
