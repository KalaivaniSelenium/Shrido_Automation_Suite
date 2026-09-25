package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.MatesContactsSocialEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class MatesContactsSocialStepDefinitions {

	private MatesContactsSocialEndpoints mates;

	public MatesContactsSocialStepDefinitions(MatesContactsSocialEndpoints mates) {
		this.mates = mates;
		
	}
	
	@Given("The User sets the base URL as {string} for Mates")
	public void the_user_sets_the_base_url_as_for_Mates(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	}

	@When("The User sends a POST request for Mates with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_mates_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		mates.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for Mates should be {string}")
	public void the_response_code_for_mates_should_be(String statusCode) {
		mates.verifyResponseStatusValue(mates.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Mates should contain the following key-value pairs:")
	public void the_response_body_for_mates_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		mates.verifyResponseKeyValues(dataTable, mates.result);
	}

	@When("The User sends a GET request for Mates from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_mates_from_and_captures_the_response_body(String APIName) {
		mates.sendGetRequest(APIName);

	}

}
