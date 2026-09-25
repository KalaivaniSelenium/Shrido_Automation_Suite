package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.RidesEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class RidesStepDefinitions {

	private RidesEndpoints rides;

	public RidesStepDefinitions(RidesEndpoints rides) {
		this.rides = rides;
	}
	

		@Given("The User sets the base URL as {string} for Rides")
		public void the_user_sets_the_base_url_as_for_rides(String baseUrl) {
			RestAssured.baseURI = baseUrl;
		}
		@When("The User sends a POST request for Rides with the request body from {string} and captures the response body")
		public void the_user_sends_a_post_request_for_rides_with_the_request_body_from_and_captures_the_response_body(String jsonFile) throws IOException {
		    rides.sendPostRequestWithPayload(jsonFile);
		}
		@Then("The response code for Rides should be {string}")
		public void the_response_code_for_rides_should_be(String statusCode) {
			rides.verifyResponseStatusValue(rides.result, Integer.parseInt(statusCode));
		}
		@Then("The response body for Rides should contain the following key-value pairs:")
		public void the_response_body_for_rides_should_contain_the_following_key_value_pairs(DataTable dataTable) {
			rides.verifyResponseKeyValues(dataTable, rides.result);
		}
		@When("The User sends a DELETE request for Rides from {string} and captures the response body")
		public void the_user_sends_a_delete_request_for_rides_from_and_captures_the_response_body(String APIName) {
		    rides.sendDeleteRequest(APIName);
		}
		@When("The User sends a PATCH request for Rides with the request body from {string} and captures the response body")
		public void the_user_sends_a_patch_request_for_rides_from_and_captures_the_response_body(String jsonFile) throws IOException {
		    rides.sendPatchRequestWithPayload(jsonFile);
		}
		@When("The User sends a GET request for Rides API from {string} and captures the response body")
		public void the_user_sends_a_get_request_for_rides_from_and_captures_the_response_body(String APIName) throws Exception {
		    rides.sendGetRequest(APIName);
		}
		 

}
