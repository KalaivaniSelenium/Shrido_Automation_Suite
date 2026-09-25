package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.SetPricingEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class SetPricingStepDefinitions {

	private SetPricingEndpoints setPrice;

	public SetPricingStepDefinitions(SetPricingEndpoints setPrice) {
		this.setPrice = setPrice;
	}
	
	@Given("The User sets the base URL as {string} for Set Pricing")
	public void The_User_sets_the_base_URL_as_for_setprice(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
    } 

	@When("The User sends a POST request for SetPricing with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_setprice_with_the_request_body_from_and_captures_the_response_body(
			String jsonFile) throws IOException {
		setPrice.sendPostRequestWithPayload(jsonFile);

	}

	@Then("The response code for SetPricing should be {string}")
	public void the_response_code_for_setprice_should_be(String statusCode) {
		setPrice.verifyResponseStatusValue(setPrice.result, Integer.parseInt(statusCode));
	}

	@Then("The response body for SetPricing should contain the following key-value pairs:")
	public void the_response_body_for_setprice_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		setPrice.verifyResponseKeyValues(dataTable, setPrice.result);
	}

	
}
