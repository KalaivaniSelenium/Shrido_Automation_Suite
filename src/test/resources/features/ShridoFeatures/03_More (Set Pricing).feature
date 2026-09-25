@SetPricing @Shrido
Feature: More (Set Pricing) 
This feature includes Set Pricing tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Set Pricing

	@DriverPriceValue
  Scenario: TC-01 Add Driver Price Value (POST {{liveUrl}}/api/user_settings/add_price_detail)
	When The User sends a POST request for SetPricing with the request body from "Add Driver Price Value.json" and captures the response body
	Then The response code for SetPricing should be "200"
	And The response body for SetPricing should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  | message | Custom rates updated successfully      |
  
  @AddApppricevalue
	Scenario: TC-02 Add App price value (POST {{liveUrl}}/api/settings/app_price_suggest_value)
	When The User sends a POST request for SetPricing with the request body from "Add App price value.json" and captures the response body
	Then The response code for SetPricing should be "200"
	And The response body for SetPricing should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  
  