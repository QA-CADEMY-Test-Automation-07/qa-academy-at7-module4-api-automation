package com.qaacademy.module4.automation.core.api.client;

import java.util.HashMap;
import java.util.Map;

/**
 * Models request data.
 */
public class ApiRequest {
    public static final String EMPTY_STRING = "";
    private Map<String, String> params;
    private Map<String, String> headers;
    private String body;

    /**
     * Initializes an instance of {@link ApiRequest}.
     */
    public ApiRequest() {
        params = new HashMap<>();
        headers = new HashMap<>();
        body = EMPTY_STRING;
    }

    /**
     * Adds params to request.
     *
     * @param params request parameters.
     */
    public void setParams(final Map<String, String> params) {
        this.params.putAll(params);
    }

    /**
     * Gets request parameters.
     *
     * @return request parameters.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Gets request parameters.
     *
     * @return request parameters.
     */
    public Map<String, String> getQueryParams() {
        return params;
    }


    /**
     * Gets body request.
     *
     * @return body request.
     */
    public String getBody() {
        return body;
    }

    /**
     * Adds body to request.
     *
     * @param body body request.
     */
    public void setBody(String body) {
        this.body = body;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
