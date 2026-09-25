@Wallet @Shrido
Feature: Wallet
This feature includes Wallet tests using RESTFul services

  Background:
    Given The User sets the base URL as "https://api-staging.shrido.com.au" for Wallet

  @GetAllUserCards
  Scenario: TC-01 Get All User Cards (GET {{liveUrl}}/api/user/cards)
  When The User sends a GET request for Wallet with the endpoint "Get all Cards Of User" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                    |
      | code    | 200                      |
      | status  | true                     |
      | message | All Cards .              |

  @AddUserCard
  Scenario: TC-02 Add New User Card (POST {{liveUrl}}/api/user/card/add)
  When The User sends a POST request for Wallet with the request body from "Add Card.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                          |
      | code    | 200                            |
      | status  | true                           |
      | message | Card added successfully.       |

  @ChangeCardExpiry
  Scenario: TC-03 Update User Card (POST {{liveUrl}}/api/user/card/change-expiry)
  When The User sends a POST request for Wallet with the request body from "Change Card Expiry.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                                        |
      | code    | 200                                          |
      | status  | true                                         |
      | message | Card expiry date was updated successfully    |

  @RemoveCard
  Scenario: TC-04 Remove User Card (POST {{liveUrl}}/api/user/card/Remove Card)
  When The User sends a POST request for Wallet with the request body from "Remove Card.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                            |
      | code    | 200                              |
      | status  | true                             |
      | message | Card removed successfully.       |
      
  @SetCardasDefault
  Scenario: TC-05 Set card as default (POST {{liveUrl}}/api/user/card/Set card as default)
  When The User sends a POST request for Wallet with the request body from "Set card as default.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                    |
      | code    | 200                      |
      | status  | true                     |
      | message | Default card updated.    |
      
  @GetWalletHistory
  Scenario: TC-06 Get Wallet History (POST {{liveUrl}}/api/wallet/history)
  When The User sends a POST request for Wallet with the request body from "Get Wallet History.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                       |
      | code    | 200                         |
      | status  | true                        |
      | message | Wallet history              |
      
  @CreatePayment
  Scenario: TC-07 Create Payment (POST {{liveUrl}}/api/payment)
  When The User sends a POST request for Wallet with the request body from "Create Payment.json" and captures the response body
  Then The response code for Wallet should be "202"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                         |
      | code    | 202                           |
      | status  | true                          |
      | message | Payment successful            |

  @Verifyreceipt
  Scenario: TC-08 Verify receipt (POST {{liveUrl}}/api/payment/receipt/verify)
  When The User sends a POST request for Wallet with the request body from "Verify receipt.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                         |
      | code    | 200                           |
      | status  | true                          |
		 
  @VerifySubscription
  Scenario: TC-09 Verify Subscription (POST {{liveUrl}}/api/payment/verify/subscription)
  When The User sends a POST request for Wallet with the request body from "Verify Subscription.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                         |
      | code    | 200                           |
      | status  | true                          |
		 
  @GetAllSubscription
  Scenario: TC-10 Get All Subscription Plans (GET {{liveUrl}}/api/subscription/plans)
  When The User sends a GET request for Wallet with the endpoint "Get All Subscription Plans" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                             |
      | code    | 200                               |
      | status  | true                              |
      | message | All Subscription plans.           |
	
  @AddSubscriptionPlan
  Scenario: TC-11 Add Subscribe plans (POST {{liveUrl}}/api/subscription/subscription-plans)
  When The User sends a POST request for Wallet with the request body from "Add Subscribe plans.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                         |
      | code    | 200                           |
      | status  | true                          |
	
  @EditSubscriptionPlan
  Scenario: TC-12 Edit Subscription plan (PUT {{Url}}/api/subscription/subscription-plans)
  When The User sends a PUT request for Wallet with the request body from "Edit Subscription plan.json" and captures the response body
  Then The response code for Wallet should be "200"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                                  |
      | code    | 200                                    |
      | status  | true                                   |
		
  @UpdateAutoRefill
  Scenario: TC-13 Update Auto Refill and Renew Settings (POST {{liveUrl}}/api/user_settings/auto_refill_and_subscription)
  When The User sends a POST request for Wallet with the request body from "Update Auto Refill and Renew Settings.json" and captures the response body
  Then The response code for Wallet should be "202"
  And The response body for Wallet should contain the following key-value pairs:
      | Key     | Value                                   |
      | code    | 200                                     |
      | status  | true                                    |
      | message | Your wallet will automatically be credited with $10 worth of points when your balance falls below $10 |
