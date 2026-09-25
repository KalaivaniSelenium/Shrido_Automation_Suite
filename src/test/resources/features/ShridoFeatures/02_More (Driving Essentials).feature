@DrivingEssentials @Shrido
Feature: More (Driving Essentials)  
This feature includes Driving Essentials tests using RESTFul services

  Background:
    Given The user sets the base URL as "https://api-staging.shrido.com.au" for Driving Essentials

  @AddCar
  Scenario: TC-01 Add Car Details (POST {{liveUrl}}/api/car/add_car)
    When The Driving Essentials sends a POST request for Driving Essentials with the request body from "Add car Detail.json" and captures the response body
    Then The response code for Driving Essentials should be "201"
    And The response body for Driving Essentials should contain the following key-value pairs:
      | Key     | Value                           |
      | code    | 201                             |
      | status  | true                            |
      | message | Car details saved successfully. |
  
  @DriverACK
  Scenario: TC-02 Submit Driver Acknowledgement (POST {{liveUrl}}/api/driver/acknowledgement)
    When The Driving Essentials sends a POST request for Driving Essentials with the request body from "Driver Acknowledged.json" and captures the response body
    Then The response code for Driving Essentials should be "200"
    And The response body for Driving Essentials should contain the following key-value pairs:
      | Key     | Value                                   |
      | code    | 200                                     |
      | status  | true                                    |
      | message | Driver agreement accepted successfully. |
	
  @Threshold
  Scenario: TC-03 Add License Details (POST {{liveUrl}}/api/car/create_threshold)
    When The Driving Essentials sends a POST request for Driving Essentials with the request body from "Threshold Create.json" and captures the response body
    Then The response code for Driving Essentials should be "200"
    And The response body for Driving Essentials should contain the following key-value pairs:
      | Key     | Value                           |
      | code    | 200                             |
      | status  | true                            |
      | message | Earning limit set successfully. |
  
  @AddThresholdValuesLimit
  Scenario: TC-04 Add Threshold Values Limit (POST {{liveUrl}}/api/settings/threshold-limit)
    When The Driving Essentials sends a POST request for Driving Essentials with the request body from "Add Threshold Values Limit.json" and captures the response body
    Then The response code for Driving Essentials should be "200"
    And The response body for Driving Essentials should contain the following key-value pairs:
      | Key     | Value |
      | code    | 200   |
      | status  | true  |
