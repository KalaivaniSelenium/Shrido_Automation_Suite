package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.DrivingEssentialsEndpoints;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class DrivingEssentialsStepDefinitions {

	private DrivingEssentialsEndpoints drive;

	public DrivingEssentialsStepDefinitions(DrivingEssentialsEndpoints drive) {
		this.drive = drive;
	}

	@Given("The user sets the base URL as {string} for Driving Essentials")
	public void the_driving_essentials_sets_the_base_url_as_for_driving_essentials(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}
	@When("The Driving Essentials sends a POST request for Driving Essentials with the request body from {string} and captures the response body")
	public void the_driving_essentials_sends_a_post_request_for_driving_essentials_with_the_request_body_from_and_captures_the_response_body(String jsonFileName) throws IOException {
	   drive.sendPostRequestWithPayload(jsonFileName);
	}
	@Then("The response code for Driving Essentials should be {string}")
	public void the_response_code_for_driving_essentials_should_be(String statusCode) {
		drive.verifyResponseStatusValue(drive.result, Integer.parseInt(statusCode));
	}
	@Then("The response body for Driving Essentials should contain the following key-value pairs:")
	public void the_response_body_for_driving_essentials_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		drive.verifyResponseKeyValues(dataTable, drive.result);
	}
}
