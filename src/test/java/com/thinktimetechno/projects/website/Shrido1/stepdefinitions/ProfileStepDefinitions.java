package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.ProfileEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class ProfileStepDefinitions {

	private ProfileEndpoints profile;

	public ProfileStepDefinitions(ProfileEndpoints profile) {
		this.profile = profile;
	}

	@Given("The User sets the base URL as {string} for Profile")
	public void the_user_sets_the_base_url_for_profile(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}

	@When("The User sends a GET request for Profile from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_profile_from_and_captures_the_response_body(String APIName) {
		profile.sendGetRequest(APIName);

	}

	@When("The User sends a DELETE request for Profile from {string} and captures the response body")
	public void the_user_sends_a_delete_request_for_profile_from_and_captures_the_response_body(String APIName) {
		profile.applicationSalesDeletePayload(APIName);

	}
	

	@When("The User sends a PUT request for Profile with the request body from {string} and captures the response body")
	public void the_user_sends_a_put_request_for_profile_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		profile.sendPutRequestWithPayload(jsonFile);

	}

	@Then("The response code for Profile should be {string}")
	public void the_response_code_for_profile_should_be(String statusCode) {
		profile.verifyResponseStatusValue(profile.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Profile should contain the following key-value pairs:")
	public void the_response_body_for_profile_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		profile.verifyResponseKeyValues(dataTable, profile.result);
	}


}
