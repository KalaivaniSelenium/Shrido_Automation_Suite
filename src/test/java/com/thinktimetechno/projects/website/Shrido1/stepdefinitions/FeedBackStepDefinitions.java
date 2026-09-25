package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.FeedBackEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class FeedBackStepDefinitions {

	private  FeedBackEndpoints others;

	public FeedBackStepDefinitions( FeedBackEndpoints others) {
		this.others = others;
	}
	
	@Given("The User sets the base URL as {string} for Feedback")
	public void the_user_sets_the_base_url_as_for_Feedback(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	}

	@When("The User sends a POST request for Others with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_others_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		others.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for Others should be {string}")
	public void the_response_code_for_others_should_be(String statusCode) {
		others.verifyResponseStatusValue(others.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Others should contain the following key-value pairs:")
	public void the_response_body_for_others_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		others.verifyResponseKeyValues(dataTable, others.result);
	}



}
