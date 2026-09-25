@Chat @Shrido
Feature: Chat  
This feature includes Chat tests using RESTFul services

  Background:
  Given The User sets the base URL as "https://api-staging.shrido.com.au" for Chat

  @GetAllChat
  Scenario: TC-01 Get All Chat (POST {{liveUrl}}/api/chat/all_chat)
  When The User sends a POST request for Chat with the request body from "Get All Chat.json" and captures the response body
  Then The response code for Chat should be "200"
  And The response body for Chat should contain the following key-value pairs:
      | Key     | Value                |
      | code    | 200                  |
      | status  | true                 |
      | message | All chat...          |
    @ChatList
Scenario: TC-02 Chat List (GET {{liveUrl}}/api/chat/list)

When The User sends a GET request for Chat from "Chat List" and captures the response body

Then The response code for Chat should be "200"

And The response body for Chat should contain the following key-value pairs:
  | Key     | Value                          |
  | code    | 200                            |
  | status  | true                           |
  | message | Chat list fetched successfully |
  
     @DeleteChat
Scenario: TC-03 Delete Chat - Hide for User (POST {{liveUrl}}/api/chat/delete)

When The User sends a POST request for Chat with the request body from "Delete Chat (hide for user).json" and captures the response body

Then The response code for Chat should be "200"

And The response body for Chat should contain the following key-value pairs:
  | Key     | Value                      |
  | code    | 200                        |
  | status  | true                       |
  | message | Chat deleted successfully. |
  
  
    @MarkChatRead
Scenario: TC-04 Mark Chat Read (POST {{liveUrl}}/api/chat/mark-read)

When The User sends a POST request for Chat with the request body from "Mark Chat Read.json" and captures the response body

Then The response code for Chat should be "200"

And The response body for Chat should contain the following key-value pairs:
  | Key     | Value                 |
  | code    | 200                   |
  | status  | true                  |
  | message | Chat marked as read. |
  
  