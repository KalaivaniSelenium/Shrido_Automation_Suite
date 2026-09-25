@Notifications @Shrido
Feature: Notifications  
This feature includes Notification tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Notification
	
  @AllnotificationofUser
  Scenario: TC-01 All notification of User (GET {{liveUrl}}/api/user_notification/notifications?page=1)
	When The User sends a GET request for Notification from "All notification of User" and captures the response body
	Then The response code for Notification should be "200"
	And The response body for Notification should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | All Notifications of User.        |


  #@DeleteNotification
  #Scenario: TC-02 Delete Notification (DELETE {{liveUrl}}/api/user_notification/0)
#	When The User sends a DELETE request for Notification from "Delete Notification" and captures the response body
#	Then The response code for Notification should be "200"
#	And The response body for Notification should contain the following key-value pairs:
  #| Key     | Value                             |
  #| code    | 200                               |
  #| status  | true                              |
  #| message | Notification deleted successfully.|
  
  @GetNotificationDetail
  Scenario: TC-03 Get Notification Detail (GET {{liveUrl}}/api/user_notification/notification?id={id})
	When The User sends a GET request for Notification from "Get Notification Detail" and captures the response body
	Then The response code for Notification should be "200"
	And The response body for Notification should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Notification details.             |
	
  @SendNotification
	Scenario: TC-04 Send Notification (POST {{liveUrl}}/api/user_notification/send_notification)
	When The User sends a POST request for Notification with the request body from "Send Notification.json" and captures the response body
	Then The response code for Notification should be "200"
	And The response body for Notification should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
	
  @GetUnReadNotificationCount
  Scenario: TC-05 Get UnRead Notification Count (GET {{liveUrl}}/api/user_notification/unreadCount)
	When The User sends a GET request for Notification from "Get UnRead Notification Count" and captures the response body
	Then The response code for Notification should be "200"
	And The response body for Notification should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | UnRead notifications count.       |
	
