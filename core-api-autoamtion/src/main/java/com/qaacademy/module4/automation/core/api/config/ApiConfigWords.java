package com.qaacademy.module4.automation.core.api.config;

/**
 * Custom reserved words enum.
 */
public enum ApiConfigWords {
    ENVIRONMENT_NAME("environmentName"),
    API_NAME("api.name"),
    API_VERSION_NAME("api.versionName"),
    API_AUTHENTICATION_TYPE("api.authenticationType"),
    API_AUTHENTICATION_USER_TYPE("api.authenticationUserType");

    private final String word;

    /**
     * Initializes Reserved Words enum.
     *
     * @param word original word.
     */
    ApiConfigWords(final String word) {
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
