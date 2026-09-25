package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.NotificationEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class NotificationStepDefinitions {

	private NotificationEndpoints notification;

	public NotificationStepDefinitions(NotificationEndpoints notification) {
		this.notification = notification;
	}

	@Given("The User sets the base URL as {string} for Notification")
		public void The_User_sets_the_base_URL_as_for_Notification(String baseUrl) {
	    	RestAssured.baseURI = baseUrl;
	} 	
	@When("The User sends a POST request for Notification with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_notification_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		notification.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for Notification should be {string}")
	public void the_response_code_for_notification_should_be(String statusCode) {
		notification.verifyResponseStatusValue(notification.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Notification should contain the following key-value pairs:")
	public void the_response_body_for_notification_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		notification.verifyResponseKeyValues(dataTable, notification.result);
	}


	@When("The User sends a GET request for Notification from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_notification_from_and_captures_the_response_body(String APIName) throws Exception {
		notification.sendGetRequest(APIName);

	}

	@When("The User sends a DELETE request for Notification from {string} and captures the response body")
	public void the_user_sends_a_delete_request_for_notification_from_and_captures_the_response_body(String APIName) {
		notification.sendDeleteRequest(APIName);
	}



}
