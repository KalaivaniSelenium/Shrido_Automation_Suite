@LiveTripTracking @Shrido
Feature: Live Trip Tracking  
This feature includes Live Trip Tracking tests using RESTFul services

	Background:
	Given The user sets the base URL as "https://api-staging.shrido.com.au" for Live Trip Tracking
  
  @NextTrip
  Scenario: TC-01 Get next trip (GET {{liveUrl}}/api/trip/next_trip)
	When The User sends a GET request for Trip Tracking from "Get next trip.json" and captures the response body
	Then The response code for Trip Tracking should be "200"
	And The response body for Trip Tracking should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Next trip..                       |

  @UpdateTripStatus
	Scenario: TC-02 Update trip status (POST {{liveUrl}}/api/trip/change_trip_status/{tripId})
	When The User sends a POST request for Trip Tracking with the request body from "Update trip status.json" and captures the response body
	Then The response code for Trip Tracking should be "200"
	And The response body for Trip Tracking should contain the following key-value pairs:
  | Key     | Value                           |
  | code    | 200                             |
  | status  | true                            |
  | message | Trip Status updated completed.. |

  @EndTrip
	Scenario: TC-03 End User Trip (POST {{liveUrl}}/api/trip/end_trip)
	When The User sends a POST request for Trip Tracking with the request body from "End User Trip.json" and captures the response body
	Then The response code for Trip Tracking should be "200"
	And The response body for Trip Tracking should contain the following key-value pairs:
  | Key     | Value                            |
  | code    | 200                              |
  | status  | true                             |
  | message | Driver trip is End Successfully. |
	
	
    @RecentAddress
Scenario: TC-04 Get Recent Address (GET {{liveUrl}}/api/trip/recent_address/:type)

    When The User sends a GET request for Trip Tracking from "Recent Address.json" and captures the response body

    Then The response code for Trip Tracking should be "200"

    And The response body for Trip Tracking should contain the following key-value pairs:

      | Key    | Value |
      | code   | 200   |
      | status | true  |
		
	