package com.qaacademy.module4.automation.core.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public final class RequestManager {

    /**
     * Default private constructor.
     */
    private RequestManager() {
    }


    public static ApiResponse get(ApiRequest apiRequest, String endpoint) {
        Response response = RestAssured.given()
                .when()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .body(apiRequest.getBody())
                .get(endpoint);

        return new ApiResponse(response);
    }

    public static ApiResponse post(ApiRequest apiRequest, String endpoint) {
        Response response = RestAssured.given()
                .when()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .body(apiRequest.getBody())
                .post(endpoint);

        return new ApiResponse(response);
    }

    public static ApiResponse put(ApiRequest apiRequest, String endpoint) {
        Response response = RestAssured
                .given()
                .when()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .body(apiRequest.getBody())
                .put(endpoint);

        return new ApiResponse(response);
    }

    public static ApiResponse delete(ApiRequest apiRequest, String endpoint) {
        Response response = RestAssured
                .given()
                .when()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .body(apiRequest.getBody())
                .delete(endpoint);
        return new ApiResponse(response);
    }
}
