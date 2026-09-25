package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.OnboardingEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class OnboardingStepDefinitions {

	private OnboardingEndpoints user;

	public OnboardingStepDefinitions(OnboardingEndpoints user) {
		this.user = user;
	}

	@Given("The User sets the base URL as {string} for User")
	public void the_user_sets_the_base_url_for_user(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}

	@Given("The User sets the base URL as {string} for msg91")
	public void the_user_sets_the_base_url_as_for_user_msg91(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	} 
	
	@When("The User sends a POST request for User with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_user_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		user.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for User should be {string}")
	public void the_response_code_for_user_should_be(String statusCode) {
		user.verifyResponseStatusValue(user.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for User should contain the following key-value pairs:")
	public void the_response_body_for_user_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		user.verifyResponseKeyValues(dataTable, user.result);
	}

	@When("The User sends a GET request for User from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_user_from_and_captures_the_response_body(String APIName) {
		user.sendGetRequest(APIName);

	}

	@When("The User sends a DELETE request for User from {string} and captures the response body")
	public void the_user_sends_a_delete_request_for_user_from_and_captures_the_response_body(String APIName) {
		user.applicationSalesDeletePayload(APIName);

	}
}
