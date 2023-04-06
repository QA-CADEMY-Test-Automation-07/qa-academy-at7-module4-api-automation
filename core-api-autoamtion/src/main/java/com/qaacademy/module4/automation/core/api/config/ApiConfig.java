package com.qaacademy.module4.automation.core.api.config;


import com.qaacademy.module4.automation.core.utils.properties.PropertiesFileReader;

import java.util.Objects;

import static com.qaacademy.module4.automation.core.api.config.ApiConfigWords.API_AUTHENTICATION_TYPE;
import static com.qaacademy.module4.automation.core.api.config.ApiConfigWords.API_AUTHENTICATION_USER_TYPE;
import static com.qaacademy.module4.automation.core.api.config.ApiConfigWords.API_NAME;
import static com.qaacademy.module4.automation.core.api.config.ApiConfigWords.API_VERSION_NAME;
import static com.qaacademy.module4.automation.core.api.config.ApiConfigWords.ENVIRONMENT_NAME;

public final class ApiConfig {
    private static final String API_PROP_FILE = "./gradle.properties";
    private static ApiConfig instance;

    private final PropertiesFileReader propertiesFileReader;

    /**
     * Initializes an instance of {@link ApiConfig}.
     */
    private ApiConfig() {
        propertiesFileReader = new PropertiesFileReader(API_PROP_FILE);
    }

    /**
     * Initializes the singleton UI Config instance.
     *
     * @return singleton instance.
     */
    public static synchronized ApiConfig getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ApiConfig();
        }
        return instance;
    }

    /**
     * Gets the test execution environment name.
     *
     * @return execution environment name.
     */
    public String getEnvironment() {
        return propertiesFileReader.getPropertyValue(ENVIRONMENT_NAME.val());
    }

    /**
     * Gets the api name.
     *
     * @return api name.
     */
    public String getApiName() {
        return propertiesFileReader.getPropertyValue(API_NAME.val());
    }

    /**
     * Gets the api version name.
     *
     * @return api version name.
     */
    public String getApiVersionName() {
        return propertiesFileReader.getPropertyValue(API_VERSION_NAME.val());
    }

    /**
     * Gets the api authentication type.
     *
     * @return api authentication type.
     */
    public String getApiAuthenticationType() {
        return propertiesFileReader.getPropertyValue(API_AUTHENTICATION_TYPE.val());
    }

    /**
     * Gets the api authentication user type.
     *
     * @return api authentication user type.
     */
    public String getApiAuthenticationUserType() {
        return propertiesFileReader.getPropertyValue(API_AUTHENTICATION_USER_TYPE.val());
    }
}
