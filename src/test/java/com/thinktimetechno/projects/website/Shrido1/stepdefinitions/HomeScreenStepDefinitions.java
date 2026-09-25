package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.HomeScreenEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class HomeScreenStepDefinitions {

	private HomeScreenEndpoints home;

	public HomeScreenStepDefinitions(HomeScreenEndpoints home) {
		this.home = home;
	}

	@Given("The user sets the base URL as {string} for HomeScreen")
	public void the_user_sets_the_base_url_as_for_home_screen(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}
	@When("The User sends a POST request for HomeScreen with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_home_screen_with_the_request_body_from_and_captures_the_response_body(String jsonFile) throws IOException {
	    home.sendPostRequestWithPayload(jsonFile);
	}
	@Then("The response code for HomeScreen should be {string}")
	public void the_response_code_for_home_screen_should_be(String statusCode) {
	    home.verifyResponseStatusValue(home.result, Integer.parseInt(statusCode));
	}
	@Then("The response body for HomeScreen should contain the following key-value pairs:")
	public void the_response_body_for_home_screen_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		home.verifyResponseKeyValues(dataTable, home.result);
	
	}
}
