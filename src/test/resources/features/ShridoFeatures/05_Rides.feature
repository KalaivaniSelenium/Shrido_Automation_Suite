@Rides @Shrido
Feature: Rides
This feature includes Rides tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Rides
	
	@SearchTrip
	Scenario: TC-01 Search all trip (POST {{liveUrl}}/api/trip/search)
	When The User sends a POST request for Rides with the request body from "Search all trip.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All Trips...                  |
  
  @TripHistory
	Scenario: TC-02 History of Trips (POST {{liveUrl}}/api/trip/trip_history)
	When The User sends a POST request for Rides with the request body from "History of Trips.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | History of Trips...           |
  
  @AllDatesOfTrips
  Scenario: TC-03 Get All Dates of Trips (GET {{liveUrl}}/api/trip/trip_dates)
	When The User sends a GET request for Rides API from "Get All Dates of Trips" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                                           |
  | code    | 200                                             |
  | status  | true                                            |
  | message | All dates of user's trips which is scheduled.   |
  
  @ReScheduleTripTime
  Scenario: TC-04 Re-Schedule trip time (PATCH {{liveUrl}}/api/trip/re_schedule_trip_time)
  When The User sends a PATCH request for Rides with the request body from "Re-Schedule trip time.json" and captures the response body
  Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  | message | Trip time is re-scheduled.             |
  
  @TripRequestSend
	Scenario: TC-05 Send trip Request (POST {{liveUrl}}/api/trip/trips/{tripId}/send_request)
	When The User sends a POST request for Rides with the request body from "Send trip Request.json" and captures the response body
	Then The response code for Rides should be "201"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 201                           |
  | status  | true                          |
  | message | Request sent successfully...  |
	
	@TripRequestAccept
	Scenario: TC-06 Request accepted (POST {{liveUrl}}/api/request/{id}/request_accept)
	When The User sends a POST request for Rides with the request body from "Request accepted.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Trip Request Accepted.        |
  
  @TripRequestReject
	Scenario: TC-07 Request Reject (POST {{liveUrl}}/api/request/{id}/request_reject)
	When The User sends a POST request for Rides with the request body from "Request Reject.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Trip Request Rejected.        |
	
	@AllRequests
	Scenario: TC-08 All request (POST {{liveUrl}}/api/request/get_trips_with_filter)
	When The User sends a POST request for Rides with the request body from "All request.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All Trips.                    |
  
  @AllConfirmedRequests
	Scenario: TC-09 All Confirmed Request of User (POST {{liveUrl}}/api/request/all_confirmed_requests)
	When The User sends a POST request for Rides with the request body from "All Confirmed Request of User.json" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All confirmed Trips.          |
  
  @DeleteTrip
  Scenario: TC-10 Delete trip (DELETE {{liveUrl}}/api/trip/{tripId})
	When The User sends a DELETE request for Rides from "Delete trip" and captures the response body
	Then The response code for Rides should be "200"
	And The response body for Rides should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Trip Deleted...                   |
	
	