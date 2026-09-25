package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.UserSettingsEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class UserSettingsSetpDefinfitions {

	private UserSettingsEndpoints userSettings;

	public UserSettingsSetpDefinfitions(UserSettingsEndpoints userSettings) {
		this.userSettings = userSettings;
	}

    @Given("The User sets the base URL as {string} for User Settings")
	public void the_user_sets_the_base_url_as_for_user_settings(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	}
    
	@When("The User sends a POST request for User Settings with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_user_settings_with_the_request_body_from_and_captures_the_response_body(String jsonFileName) throws IOException {
		userSettings.sendPostRequestWithPayload(jsonFileName);
	}
	
	@Then("The response code for User Settings should be {string}")
	public void the_response_code_for_user_settings_should_be(String statusCode) {
		userSettings.verifyResponseStatusValue(userSettings.result, Integer.parseInt(statusCode));
	}
	
	@Then("The response body for User Settings should contain the following key-value pairs:")
	public void the_response_body_for_user_settings_should_contain_the_following_key_value_pairs(DataTable dataTable) {
	    userSettings.verifyResponseKeyValues(dataTable, userSettings.result);
	}
	
	@When("The User sends a GET request for User Settings from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_user_settings_from_and_captures_the_response_body(String APIName) {
		userSettings.sendGetRequest(APIName);

	}
}