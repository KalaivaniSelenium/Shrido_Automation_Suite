package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.ReviewEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class ReviewStepDefinitions {

	private ReviewEndpoints review;

	public ReviewStepDefinitions(ReviewEndpoints review) {
		this.review = review;
	}
	
	@Given("The User sets the base URL as {string} for Review")
	public void The_User_sets_the_base_URL_as_for_Review(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
    }

	@When("The User sends a POST request for Review with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_review_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		review.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for Review should be {string}")
	public void the_response_code_for_review_should_be(String statusCode) {
		review.verifyResponseStatusValue(review.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for Review should contain the following key-value pairs:")
	public void the_response_body_for_review_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		review.verifyResponseKeyValues(dataTable, review.result);
	}

	@When("The User sends a GET request for Review API from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_review_from_and_captures_the_response_body(String APIName) {
		review.sendGetRequest(APIName);

	}

}
