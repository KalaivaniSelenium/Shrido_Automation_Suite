package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.LiveTripTrackingEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class LiveTripTrackingStepDefinitions {

	private LiveTripTrackingEndpoints trip;

	public LiveTripTrackingStepDefinitions(LiveTripTrackingEndpoints trip) {
		this.trip = trip;
	}
	
	@Given("The user sets the base URL as {string} for Live Trip Tracking")
	public void the_user_sets_the_base_url_as_for_live_trip_tracking(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}
	@When("The User sends a POST request for Trip Tracking with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_trip_tracking_with_the_request_body_from_and_captures_the_response_body(String jsonFile) throws IOException {
		trip.sendPostRequestWithPayload(jsonFile);
	}
	@Then("The response code for Trip Tracking should be {string}")
	public void the_response_code_for_trip_tracking_should_be(String statusCode) {
		trip.verifyResponseStatusValue(trip.result, Integer.parseInt(statusCode));
	}
	@Then("The response body for Trip Tracking should contain the following key-value pairs:")
	public void the_response_body_for_trip_tracking_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		trip.verifyResponseKeyValues(dataTable, trip.result);
	}

	@When("The User sends a GET request for Trip Tracking from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_trip_tracking_from_and_captures_the_response_body(String jsonFile) throws Exception {
		trip.sendGetRequestwithJsonPayload(jsonFile);
	}
	
	
	
}
