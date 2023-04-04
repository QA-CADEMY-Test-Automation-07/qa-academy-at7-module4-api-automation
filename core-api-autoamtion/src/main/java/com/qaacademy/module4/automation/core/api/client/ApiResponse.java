package com.qaacademy.module4.automation.core.api.client;

import io.restassured.response.Response;

import java.util.Collection;

/**
 * Defines Api response wrapper class.
 */
public class ApiResponse {

    private final Response response;

    public ApiResponse(final Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBody() {
        return response.getBody().asString();
    }
}
