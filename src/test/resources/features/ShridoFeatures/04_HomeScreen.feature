@HomeScreen @Shrido
Feature: HomeScreen   
This feature includes HomeScreen tests using RESTFul services

	Background:
	 Given The user sets the base URL as "https://api-staging.shrido.com.au" for HomeScreen
	
	@CreateTrip
	Scenario: TC-01 Create Trip (POST {{liveUrl}}/api/trip/create_trip)
	When The User sends a POST request for HomeScreen with the request body from "Create Trip.json" and captures the response body
	Then The response code for HomeScreen should be "200"
	And The response body for HomeScreen should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Your planned trip has been scheduled. Tap on 'Rides' to view your trip request. |
	
	@NotifyMates
	Scenario: TC-02 Notify favourite friends about trip (POST {{liveUrl}}/api/trip/notify_mates)
	When The User sends a POST request for HomeScreen with the request body from "Notify favourite friends about trip.json" and captures the response body
	Then The response code for HomeScreen should be "200"
	And The response body for HomeScreen should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Sent Trip Details to all selected mates. |
	
