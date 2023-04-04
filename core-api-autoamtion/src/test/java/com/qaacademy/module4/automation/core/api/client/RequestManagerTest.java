package com.qaacademy.module4.automation.core.api.client;

import com.qaacademy.module4.automation.core.api.client.validators.RequestManagerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

@DisplayName("Request Manager")
@Tag("UnitTest")
public class RequestManagerTest {

    @Test
    @DisplayName("Verifies if GET method call correctly")
    public void verifiesIfGetMethodCallCorrectly() {
        // Given
        var url = "https://api.trello.com";
        var key = "5542bb122572b0a33d33fc15891f1a43";
        var token = "ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5";
        var endpoint = url.concat("/1/members/me/boards");
        var params = new HashMap<String, String>();
        params.put("key", key);
        params.put("token", token);
        var apiRequest = new ApiRequest();
        apiRequest.setParams(params);
        var id = "57db15c1c132bd3afa42b55c";


        // When
        var apiResponse = RequestManager.get(apiRequest, endpoint);

        System.out.println("Response status cade: " + apiResponse.getStatusCode());
        System.out.println("Response name of body: " + RequestManagerValidator.getName(apiResponse.getBody(), id));

        // Then
        Assertions.assertEquals(200, apiResponse.getStatusCode(),
                String.format("Response status code is not 200. Response status cade: %s", apiResponse.getStatusCode()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(apiResponse.getBody().contains("members"),
                        String.format("Response body does not contain 'members' string. Response id: %s", id)),
                () -> Assertions.assertEquals("AT01", RequestManagerValidator.getName(apiResponse.getBody(), id),
                        String.format("Response body does not contain 'AT01' string. Response body id: %s", id))
        );
    }

    @Test
    @DisplayName("Verifies if POST method call correctly")
    public void verifiesIfPostMethodCallCorrectly() {
        // Given
        var url = "https://api.trello.com";
        var key = "5542bb122572b0a33d33fc15891f1a43";
        var token = "ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5";
        var endpoint = url.concat("/1/boards");
        var params = new HashMap<String, String>();
        params.put("key", key);
        params.put("token", token);
        var boardName = "Automation-test-02";
        var body = """
                {
                    "name" : "%s"
                }""";
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        var apiRequest = new ApiRequest();
        apiRequest.setParams(params);
        apiRequest.setBody(String.format(body, boardName));
        apiRequest.setHeaders(headers);


        // When
        ApiResponse apiResponse = RequestManager.post(apiRequest, endpoint);

        // Then
        Assertions.assertEquals(200, apiResponse.getStatusCode(),
                String.format("Response status code is not 200. Response status cade: %s", apiResponse.getStatusCode()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(apiResponse.getBody().contains("members"),
                        String.format("Response body does not contain 'members' string. Response body: %s", apiResponse.getBody())),
                () -> Assertions.assertEquals(boardName, RequestManagerValidator.getBoardName(apiResponse.getBody()),
                        String.format("Response body does not contain '%s' string. Response body: %s", boardName, apiResponse.getBody()))
        );
    }
}
