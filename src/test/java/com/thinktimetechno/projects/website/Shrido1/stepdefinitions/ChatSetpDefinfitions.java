package com.thinktimetechno.projects.website.Shrido1.stepdefinitions;

import java.io.IOException;

import com.thinktimetechno.Shrido.endpoints.ChatEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class ChatSetpDefinfitions {

	private ChatEndpoints chat;

	public ChatSetpDefinfitions(ChatEndpoints chat) {
		this.chat = chat;
	}


    @Given("The User sets the base URL as {string} for Chat")
	public void the_user_sets_the_base_url_as_for_chat(String baseUrl) {
    	RestAssured.baseURI = baseUrl;
	}
	@When("The User sends a POST request for Chat with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_for_chat_with_the_request_body_from_and_captures_the_response_body(String jsonFileName) throws IOException {
	    chat.sendPostRequestWithPayload(jsonFileName);
	}
	@Then("The response code for Chat should be {string}")
	public void the_response_code_for_chat_should_be(String statusCode) {
	    chat.verifyResponseStatusValue(chat.result, Integer.parseInt(statusCode));
	}
	@Then("The response body for Chat should contain the following key-value pairs:")
	public void the_response_body_for_chat_should_contain_the_following_key_value_pairs(DataTable dataTable) {
		chat.verifyResponseKeyValues(dataTable, chat.result);
	}
	@When("The User sends a GET request for Chat from {string} and captures the response body")
	public void the_user_sends_a_get_request_for_chat_from_and_captures_the_response_body(
	        String APIName) {

	    chat.sendGetRequest(APIName);
	}
}