package com.qaacademy.module4.automation.core.api.environment;


import com.qaacademy.module4.automation.core.api.config.ApiConfig;

/**
 * Custom reserved words enum.
 */
public enum EnvironmentWords {
    ENVIRONMENT_PATH("src/test/resources/Environment.json"),
    ENVIRONMENT_NAME(ApiConfig.getInstance().getEnvironment()),
    API_NAME(ApiConfig.getInstance().getApiName()),
    API_VERSION_NAME(ApiConfig.getInstance().getApiVersionName()),
    API_AUTHENTICATION_TYPE(ApiConfig.getInstance().getApiAuthenticationType()),
    API_AUTHENTICATION_USER_TYPE(ApiConfig.getInstance().getApiAuthenticationUserType());

    private final String word;

    /**
     * Initializes Reserved Words enum.
     *
     * @param word original word.
     */
    EnvironmentWords(final String word) {
        this.word = word;
    }

    /**
     * Gets the enum word value.
     *
     * @return original word.
     */
    public String val() {
        return word;
    }
}
