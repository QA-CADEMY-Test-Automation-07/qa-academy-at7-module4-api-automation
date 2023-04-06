package com.qaacademy.module4.automation.trello.api.steps;

import com.qaacademy.module4.automation.core.api.client.ApiRequest;
import com.qaacademy.module4.automation.core.api.client.ApiResponse;
import com.qaacademy.module4.automation.core.api.client.RequestManager;
import com.qaacademy.module4.automation.core.api.environment.EnvironmentManager;
import com.qaacademy.module4.automation.trello.api.validator.RequestManagerValidator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class BoardsSteps {

    private final EnvironmentManager environmentManager;
    private final HashMap<String, String> params;
    private final HashMap<String, String> headers;
    private final ApiRequest apiRequest;
    private ApiResponse response;

    public BoardsSteps() {
        environmentManager = EnvironmentManager.getInstance();
        params = new HashMap<>();
        params.put("key", environmentManager.getKey());
        params.put("token", environmentManager.getToken());
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        apiRequest = new ApiRequest();
    }

    @When("I send a request to GET boards endpoint {string}")
    public void iSendARequestToGETBoardsEndpoint(String endpoint) {
        var url = environmentManager.getUrl().concat(endpoint);
        apiRequest.setParams(params);
        response = RequestManager.get(apiRequest, url);
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
        Assertions.assertEquals(value, RequestManagerValidator.getBoardName(response.getBody(), field, value));
    }
}
