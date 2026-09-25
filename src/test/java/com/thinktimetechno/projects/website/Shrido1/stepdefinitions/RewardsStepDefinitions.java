package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.RewardsEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class RewardsStepDefinitions {

	private RewardsEndpoints rewards;

	public RewardsStepDefinitions(RewardsEndpoints rewards) {
		this.rewards = rewards;
	}
    
	@Given("The User sets the base URL as {string} for Rewards")
	public void the_user_sets_the_base_url_as_for_Rewards(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	}
	
	@When("The User sends a POST request for Rewards with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_Rewards_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		rewards.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for Rewards should be {string}")
	public void the_response_code_for_Rewards_should_be(String statusCode) {
		rewards.verifyResponseStatusValue(rewards.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Rewards should contain the following key-value pairs:")
	public void the_response_body_for_Rewards_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		rewards.verifyResponseKeyValues(dataTable, rewards.result);
	}

	@When("The User sends a GET request for Rewards API from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_Rewards_from_and_captures_the_response_body(String APIName) {
		rewards.sendGetRequest(APIName);

	}

}
