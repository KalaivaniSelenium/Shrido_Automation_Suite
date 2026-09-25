@Emails @Shrido
Feature: Emails  
This feature includes Settings tests using RESTFul services

	Background:
	Given The User sets the base URL as "https://api-staging.shrido.com.au" for Emails

	@AddPreDefinemessages
	Scenario: TC-01 Add PreDefine messages (POST {{liveUrl}}/api/settings/predefine_message)
	When The User sends a POST request for Settings with the request body from "Add PreDefine messages.json" and captures the response body
	Then The response code for Settings should be "200"
	And The response body for Settings should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  
  
  @AddEmailTemplates
	Scenario: TC-02 Add Email Templates (POST {{liveUrl}}/api/settings/add-email-templates)
	When The User sends a POST request for Settings with the request body from "Add Email Templates.json" and captures the response body
	Then The response code for Settings should be "200"
	And The response body for Settings should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |

  @UpdateEmailTemplate
  Scenario: TC-03 Update Email Template (PUT {{liveUrl}}/api/settings/update-email-templates)
  When The User sends a PUT request for Settings with the request body from "Update Email Template.json" and captures the response body
  Then The response code for Settings should be "200"
	And The response body for Settings should contain the following key-value pairs:
  | Key     | Value                                  |
  | code    | 200                                    |
  | status  | true                                   |
  		
  @EmailTesting
	Scenario: TC-04 Email Testing (POST {{liveUrl}}/api/settings/email-test)
	When The User sends a POST request for Settings with the request body from "Email Testing.json" and captures the response body
	Then The response code for Settings should be "200"
	And The response body for Settings should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |
  
  @AddEmailUs
	Scenario: TC-05 Add Email Us (POST {{liveUrl}}/api/settings/email-us)
	When The User sends a POST request for Settings with the request body from "Add Email Us.json" and captures the response body
	Then The response code for Settings should be "200"
	And The response body for Settings should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 200                           |
  | status  | true                          |

	
