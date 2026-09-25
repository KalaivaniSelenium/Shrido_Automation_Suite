package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.WalletEndpoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class WalletSetpDefinfitions {

	private WalletEndpoints wallet;

	public WalletSetpDefinfitions(WalletEndpoints wallet) {
		this.wallet = wallet;
	}
	@Given("The User sets the base URL as {string} for Wallet")
	public void the_user_sets_the_base_url_as_for_wallet(String baseUrl) {
		RestAssured.baseURI = baseUrl;
	}
	@When("The User sends a POST request for Wallet with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_wallet_with_the_request_body_from_and_captures_the_response_body(String jsonFileName) throws IOException {
	   wallet.sendPostRequestWithPayload(jsonFileName);
	}
	@Then("The response code for Wallet should be {string}")
	public void the_response_code_for_wallet_should_be(String statusCode) {
	    wallet.verifyResponseStatusValue(wallet.result, Integer.parseInt(statusCode));
	}
	@Then("The response body for Wallet should contain the following key-value pairs:")
	public void the_response_body_for_wallet_should_contain_the_following_key_value_pairs(DataTable dataTable) {
	   wallet.verifyResponseKeyValues(dataTable, wallet.result);
	}
	@When("The User sends a GET request for Wallet with the endpoint {string} and captures the response body")
	public void the_user_sends_a_get_request_for_wallet_with_the_endpoint_and_captures_the_response_body(String APIName) {
	    wallet.sendGetRequest(APIName);
	}
	@When("The User sends a PUT request for Wallet with the request body from {string} and captures the response body")
	public void the_user_sends_a_put_request_for_wallet_with_the_request_body_from_and_captures_the_response_body(String jsonFile) throws IOException {
	    wallet.sendPutRequestWithPayload(jsonFile);
	}


 
}