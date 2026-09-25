@MatesTest @Shrido
Feature: Mates Tests  
This feature includes Mates tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Mates
	
	@ReferralHistory
	Scenario: TC-01 Retrieve Referral History (GET {{liveUrl}}/api/user/referral-history?page=0)
	When The User sends a GET request for Mates from "Get Referral History" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Referral History.             |
	
	@TotalReferrals
	Scenario: TC-02 Retrieve Total Referrals (GET {{liveUrl}}/api/user/total-referral)
	When The User sends a GET request for Mates from "Get Total Referrals" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Total Referral Points.        |
	
	
	@Referralcode
	Scenario: TC-03 Apply Referral Code (POST {{liveUrl}}/api/user/add-referral)
	When The User sends a POST request for Mates with the request body from "Add Referral Code.json" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | Referral Code Added Successfully. |
  
  @GetTotalPoints
  Scenario: TC-04 Get User Total Points (GET {{liveUrl}}/api/user/total-points)
	When The User sends a GET request for Mates from "Get User Total Points" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
  | Key     | Value                             |
  | code    | 200                               |
  | status  | true                              |
  | message | User`s Total Points.              |
  
	@GetLatestFriends
	Scenario: TC-05 Get Latest Friends (GET {{liveUrl}}/api/mate?page=0)
	When The User sends a GET request for Mates from "Get Latest Friends" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
	
	@GetAllMates
	Scenario: TC-06 Get All Mates(GET {{liveUrl}}/api/mate/all_mates?page=0)
	When The User sends a GET request for Mates from "Get All Mates" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All Mate.                     |
  
	@SyncContact
	Scenario: TC-07 Sync Contact (POST {{liveUrl}}/api/mate/sync_contact)
	When The User sends a POST request for Mates with the request body from "Sync Contact.json" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Contact Sync Successfully.    |
	
	@Sendfriendrequest
	Scenario: TC-08 Send friend request (POST {{liveUrl}}/api/mate/send_request)
	When The User sends a POST request for Mates with the request body from "Send friend request.json" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Friend Request Sent...        |
  
  @Updatefriendrequeststatus
	Scenario: TC-09 Update friend request status (POST {{liveUrl}}/api/mate/request_status)
	When The User sends a POST request for Mates with the request body from "Update friend request status.json" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Friend Request Accepted...    |
	
	@FavouriteorUnfavouritefriend
	Scenario: TC-10 Favourite or Unfavourite friend (POST {{url}}/api/mate/favourite)
	When The User sends a POST request for Mates with the request body from "Favourite or Unfavourite friend.json" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Update Favourite Status.      |
  
  @GetUser'sFavouritesfriends
	Scenario: TC-11 Get User's Favourites friends (GET {{liveUrl}}/api/mate/favourites?page=0)
	When The User sends a GET request for Mates from "Get User's Favourites friends" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All Favourites Friends...     |
	
	@GetMatesfromride
	Scenario: TC-12 GetMatesfromride (GET {{url}}/api/mate/mates_from_ride)
	When The User sends a GET request for Mates from "Get Mates from ride" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | Mates from rides...           |
  
  @Getfriendsfromcontact
	Scenario: TC-13 Get friends from contact (GET {{liveUrl}}/api/mate/mates_from_contact?page=0)
	When The User sends a GET request for Mates from "Get friends from contact" and captures the response body
	Then The response code for Mates should be "200"
	And The response body for Mates should contain the following key-value pairs:
	| Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  | message | All mates from contacts.      |


  @RefreshAccessToken
    Scenario: TC-14 Auth - Refresh Access Token (POST {{liveUrl}}/api/auth/refresh)
    When The User sends a POST request for Mates with the request body from "Auth refresh access token.json" and captures the response body
    Then The response code for Mates should be "200"
    And The response body for Mates should contain the following key-value pairs:
  | Key     | Value          |
  | code    | 200            |
  | status  | true           |
  | message | Token refreshed. |
  
  
