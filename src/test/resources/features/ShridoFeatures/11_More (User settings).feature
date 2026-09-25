@UserSettingsTest @Shrido
Feature: More (User settings)  
This feature includes User Settings tests using RESTFul services

  Background:
  Given The User sets the base URL as "https://api-staging.shrido.com.au" for User Settings

 
  @DriverSettings
  Scenario: TC-01 Update Driver Settings (POST {{liveUrl}}/api/user_settings/driver)
  When The User sends a POST request for User Settings with the request body from "Driver Settings.json" and captures the response body
  Then The response code for User Settings should be "200"
  And The response body for User Settings should contain the following key-value pairs:
      | Key     | Value                                |
      | code    | 200                                  | 
      | status  | true                                 |
      | message | Driver settings updated successfully |

  @PassengerSettings
  Scenario: TC-02 Update Passenger Settings (POST {{liveUrl}}/api/user_settings/passenger)
  When The User sends a POST request for User Settings with the request body from "Passenger Settings.json" and captures the response body
  Then The response code for User Settings should be "200"
  And The response body for User Settings should contain the following key-value pairs:
      | Key     | Value                                    |
      | code    | 200                                      | 
      | status  | true                                     |
      | message | Passenger settings updated successfully. |


  @NotificationSettings
  Scenario: TC-03 Update Notification Settings (POST {{liveUrl}}/api/user_settings/notification)
  When The User sends a POST request for User Settings with the request body from "Notification Settings.json" and captures the response body
  Then The response code for User Settings should be "200"
  And The response body for User Settings should contain the following key-value pairs:
      | Key     | Value                                       |
      | code    | 200                                         |
      | status  | true                                        |
      | message | Notification settings updated successfully. |
  
  @Allsettings
	Scenario: TC-04 All settings (GET {{liveUrl}}/api/settings)
	When The User sends a GET request for User Settings from "All settings" and captures the response body
	Then The response code for User Settings should be "200"
	And The response body for User Settings should contain the following key-value pairs:
			| Key     | Value                         |
		  | code    | 200                           |
		  | status  | true                          |
			| message | All Settings...               |
			
  @RedirectToApp
    Scenario: TC-05 Redirect to App (GET {{liveUrl}}/api/settings/redirect_to_app?redirect_to=history)
    When The User sends a GET request for User Settings from "redirect_to_app" and captures the response body
    Then The response code for User Settings should be "200"			
			