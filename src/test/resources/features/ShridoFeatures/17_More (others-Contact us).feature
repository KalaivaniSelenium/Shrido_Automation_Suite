@FeedBack @Shrido
Feature: More (others - Contact us/Feedback/Logout) 
This feature includes FeedBack tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Feedback

  @AddFeedback
	Scenario: TC-01 Add FeedBack (POST {{Url}}/api/user/feedback)
	When The User sends a POST request for Others with the request body from "Add FeedBack.json" and captures the response body
	Then The response code for Others should be "200"
	And The response body for Others should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Thanks for your feedback!     |
  
  @LogOut
  Scenario: TC-02 LogOut (POST {{liveUrl}}/api/user/logout)
	When The User sends a POST request for Others with the request body from "LogOut.json" and captures the response body
	Then The response code for Others should be "200"
	And The response body for Others should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  | message | Logged out successfully.                   |
  
   @Logout
     Scenario: TC-03 Auth - Logout (Revoke Session) (POST {{liveUrl}}/api/auth/logout)
     When The User sends a POST request for Mates with the request body from "Auth logout revoke session.json" and captures the response body     
     Then The response code for Mates should be "200"
     And The response body for Mates should contain the following key-value pairs:
  | Key     | Value      |
  | code    | 200        |
  | status  | true       |
  | message | Logged out. |
  
  
  
  

