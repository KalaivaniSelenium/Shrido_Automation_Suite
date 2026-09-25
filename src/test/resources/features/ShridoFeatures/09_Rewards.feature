@Rewards  @Shrido
Feature: Rewards 
This feature includes Rewards tests using RESTFul services
 
 Background:
  Given The User sets the base URL as "https://api-staging.shrido.com.au" for Rewards
	
	@GetGiftCards
  Scenario: TC-01 Get Gift Cards (GET {{liveUrl}}/api/gift-card)
	When The User sends a GET request for Rewards API from "Get Gift Cards" and captures the response body
	Then The response code for Rewards should be "200"
	And The response body for Rewards should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Cards fetched successfully.       |
	
	@RedeemGiftCard
	Scenario: TC-02 Redeem gift card (POST {{liveUrl}}/api/gift-card/redeem)
	When The User sends a POST request for Rewards with the request body from "Redeem gift card.json" and captures the response body
	Then The response code for Rewards should be "200"
	And The response body for Rewards should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
 
  #@URLforGiftCards
  #Scenario: TC-03 Live Url for get Gift Cards
#	When The User sends a GET request for GiftCards API from "Live Url for get Gift Cards" and captures the response body
#	Then The response code for GiftCards should be "200"
#	And The response body for GiftCards should contain the following key-value pairs:
  #| Key     | Value                             |
  #| code    | 200                               |
  #| status  | true                              |
  #| message | Cards fetched successfully.       |

 
 