package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.TravelSafetyEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class TravelSafetyStepDefinitions {

	private TravelSafetyEndpoints travel;

	public TravelSafetyStepDefinitions(TravelSafetyEndpoints travel) {
		this.travel = travel;
	}

	@Given("The User sets the base URL as {string} for TravelSafety")
	public void the_user_sets_the_base_url_for_trave_safety(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}

	@When("The User sends a POST request for TravelSafety with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_trave_safety_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		travel.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for TravelSafety should be {string}")
	public void the_response_code_for_trave_safety_should_be(String statusCode) {
		travel.verifyResponseStatusValue(travel.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for TravelSafety should contain the following key-value pairs:")
	public void the_response_body_for_user_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		travel.verifyResponseKeyValues(dataTable, travel.result);
	}
}
