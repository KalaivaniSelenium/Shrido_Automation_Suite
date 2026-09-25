@Reviews&Ratings  @Shrido
Feature: More (Reviews & Ratings) 
This feature includes Review tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Review
	
	@CreateReview
	Scenario: TC-01 Create Review (POST {{liveUrl}}/api/review/create)
	When The User sends a POST request for Review with the request body from "Create Review.json" and captures the response body
	Then The response code for Review should be "200"
	And The response body for Review should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Review Created.               |
	
  @GetUserReviews
  Scenario: TC-02 Get User Reviews (GET {{liveUrl}}/api/review/{id})
	When The User sends a GET request for Review API from "Get User Reviews" and captures the response body
	Then The response code for Review should be "200"
	And The response body for Review should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Reviews Of User.              |
  
  @GetTripReview
  Scenario: TC-03 Get Trip Review (GET {{liveUrl}}/api/review/trip?trip_id={id})
	When The User sends a GET request for Review API from "Get Trip Review" and captures the response body
	Then The response code for Review should be "200"
	And The response body for Review should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Reviews Of trip.              |
 
 