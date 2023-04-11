package com.qaacademy.module4.automation.trello.api.steps;

import com.qaacademy.module4.automation.core.api.client.ApiRequest;
import com.qaacademy.module4.automation.core.api.client.ApiResponse;
import com.qaacademy.module4.automation.core.api.client.RequestManager;
import com.qaacademy.module4.automation.core.api.environment.EnvironmentManager;
import com.qaacademy.module4.automation.trello.api.validator.RequestManagerValidator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class BoardsSteps {
    private final HashMap<String, String> context;
    private final EnvironmentManager environmentManager;
    private final HashMap<String, String> params;
    private final HashMap<String, String> headers;
    private final ApiRequest apiRequest;
    private ApiResponse response;

    public BoardsSteps(final HashMap<String, String> context) {
        this.context = context;
        environmentManager = EnvironmentManager.getInstance();
        params = new HashMap<>();
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        apiRequest = new ApiRequest();
    }

    @Given("I have a valid key and token")
    public void haveAValidKeyAndToken() {
        params.put("key", environmentManager.getKey());
        params.put("token", environmentManager.getToken());
    }

    @When("I send a request to GET boards endpoint {string}")
    public void iSendARequestToGETBoardsEndpoint(String endpoint) {
        var url = environmentManager.getUrl().concat(endpoint);
        apiRequest.setParams(params);
        response = RequestManager.get(apiRequest, url);
    }

    @When("I send a request to GET a board endpoint {string}")
    public void sendARequestToGETABoardEndpoint(String endpoint) {
        var id = context.get("boardId");
        var url = environmentManager.getUrl().concat(endpoint.replace("{id}", id));
        apiRequest.setParams(params);
        response = RequestManager.get(apiRequest, url);
    }

    @When("I send a request to POST boards endpoint {string} with name {string}")
    public void sendARequestToPOSTBoardsEndpointWithName(String endpoint, String name) {
        var url = environmentManager.getUrl().concat(endpoint);
        apiRequest.setParams(params);
        apiRequest.setHeaders(headers);
        var bodyRequest = """
                {
                    "name": "%s"
                }
                """;
        apiRequest.setBody(String.format(bodyRequest, name));
        response = RequestManager.post(apiRequest, url);
        var id = RequestManagerValidator.getId(response.getBody());
        context.put("boardName", name);
        context.put("boardId", id);
    }

    @When("I send a request to PUT boards endpoint {string} with name {string}")
    public void sendARequestToPUTBoardsEndpointWithName(String endpoint, String name) {
        var id = context.get("boardId");
        var url = environmentManager.getUrl().concat(endpoint.replace("{id}", id));
        apiRequest.setParams(params);
        apiRequest.setHeaders(headers);
        var bodyRequest = """
                {
                    "name": "%s"
                }
                """;
        apiRequest.setBody(String.format(bodyRequest, name));
        response = RequestManager.put(apiRequest, url);
        context.put("boardName", name);
    }

    @When("I send a request to DELETE boards endpoint {string}")
    public void sendARequestToDELETEBoardsEndpoint(String endpoint) {
        var id = context.get("boardId");
        var url = environmentManager.getUrl().concat(endpoint.replace("{id}", id));
        apiRequest.setParams(params);
        response = RequestManager.delete(apiRequest, url);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @And("the response should be contain {string} field")
    public void theResponseShouldBeContainField(String field) {
        Assertions.assertTrue(response.getBody().contains(field));
    }

    @And("the response should have a field {string} with value {string}")
    public void theResponseShouldHaveAFieldWithValue(String field, String value) {
        var boardName = context.get("boardName");
        value = value.replace("{name}", boardName);
        Assertions.assertEquals(value, RequestManagerValidator.getBoardName(response.getBody(), field, value));
    }
}
