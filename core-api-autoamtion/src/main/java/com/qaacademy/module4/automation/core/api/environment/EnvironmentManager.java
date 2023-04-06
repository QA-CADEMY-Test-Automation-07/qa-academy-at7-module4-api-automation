package com.qaacademy.module4.automation.core.api.environment;

import org.json.simple.JSONObject;

import java.util.Objects;

import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.API_AUTHENTICATION_TYPE;
import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.API_AUTHENTICATION_USER_TYPE;
import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.API_NAME;
import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.API_VERSION_NAME;
import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.ENVIRONMENT_NAME;
import static com.qaacademy.module4.automation.core.api.environment.EnvironmentWords.ENVIRONMENT_PATH;
import static com.qaacademy.module4.automation.core.utils.json.JsonFileReader.loadJsonObjectFromFile;
import static com.qaacademy.module4.automation.core.utils.json.JsonPath.getResults;


public final class EnvironmentManager {
    public static final String ENVIRONMENTS_NAME_FILTER = "$.Environments[?(@.Name == '%s')]";
    public static final String API_NAME_FILTER = "Apis[?(@.Name == '%s')]";
    private static final String VERSION_NAME_FILTER = "Versions[?(@.Name == '%s')]";
    private static final String AUTHENTICATION_TYPE_FILTER = "Authentications[?(@.Type == '%s')]";
    private static final String USERS_TYPE_FILTER = "Users[?(@.Type == '%s')]";
    private static final String FILTER_USERNAME = "Username";
    private static final String FILTER_PASSWORD = "Password";
    private static final String FILTER_TOKEN = "Token";
    private static final String FILTER_KEY = "Key";

    private static EnvironmentManager instance;
    private final JSONObject jsonObject;
    private final String jsonPathEnvironmentFilter;
    private final int index;
    private String jsonPathFilter;
    private String jsonPathApiFilter;

    /**
     * Initializes an instance of {@link EnvironmentManager}.
     */
    private EnvironmentManager() {
        index = 0;
        jsonObject = loadJsonObjectFromFile(ENVIRONMENT_PATH.val());
        jsonPathEnvironmentFilter = String.format(ENVIRONMENTS_NAME_FILTER, ENVIRONMENT_NAME.val());
    }

    /**
     * Initializes a singleton Environment Manager instance.
     *
     * @return singleton instance of {@link EnvironmentManager}.
     */
    public static EnvironmentManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new EnvironmentManager();
        }
        return instance;
    }

    public String getBaseUrl() {
        return getBaseUrl(API_NAME.val());
    }

    public String getBaseUrl(String apiName) {
        jsonPathApiFilter = String.format(API_NAME_FILTER, apiName);
        jsonPathFilter = String.format("%s.%s.BaseUrl",
                jsonPathEnvironmentFilter, jsonPathApiFilter);
        return getResults(jsonObject, jsonPathFilter).get(index);
    }

    public String getVersion() {
        return getVersion(API_NAME.val(), API_VERSION_NAME.val());
    }

    public String getVersion(final String apiName, final String apiVersionName) {
        jsonPathApiFilter = String.format(API_NAME_FILTER, apiName);
        final String jsonPathVersionFilter = String.format(VERSION_NAME_FILTER, apiVersionName);
        jsonPathFilter = String.format("%s.%s.%s.Version",
                jsonPathEnvironmentFilter, jsonPathApiFilter, jsonPathVersionFilter);
        return getResults(jsonObject, jsonPathFilter).get(index);
    }

    public String getUrl() {
        return getBaseUrl().concat(getVersion());
    }

    public String getUrl(final String apiName, final String apiVersionName) {
        return getBaseUrl(apiName).concat(getVersion(apiName, apiVersionName));
    }

    public String getUrl(String apiName) {
        return getBaseUrl(apiName).concat(getVersion());
    }

    public String getUsername() {
        return getUserType(FILTER_USERNAME);
    }

    public String getUsername(final String apiAuthenticationType,
                              final String apiAuthenticationUserType,
                              final String apiName) {
        return getUserType(FILTER_USERNAME, apiAuthenticationType, apiAuthenticationUserType, apiName);
    }

    public String getPassword() {
        return getUserType(FILTER_PASSWORD);
    }

    public String getPassword(final String apiAuthenticationType,
                              final String apiAuthenticationUserType,
                              final String apiName) {
        return getUserType(FILTER_PASSWORD, apiAuthenticationType, apiAuthenticationUserType, apiName);
    }

    public String getToken() {
        return getUserType(FILTER_TOKEN);
    }
    public String getKey() {
        return getUserType(FILTER_KEY);
    }

    public String getToken(final String apiAuthenticationType,
                           final String apiAuthenticationUserType,
                           final String apiName) {
        return getUserType(FILTER_TOKEN, apiAuthenticationType, apiAuthenticationUserType, apiName);
    }

    private String getUserType(final String filterName) {
        return getUserType(filterName,
                API_AUTHENTICATION_TYPE.val(),
                API_AUTHENTICATION_USER_TYPE.val(),
                API_NAME.val());
    }

    private String getUserType(final String filterName,
                               final String apiAuthenticationType,
                               final String apiAuthenticationUserType,
                               final String apiName) {
        var jsonPathAuthenticationFilter =
                String.format(AUTHENTICATION_TYPE_FILTER, apiAuthenticationType);
        var jsonPathUserFilter = String.format(USERS_TYPE_FILTER, apiAuthenticationUserType);
        jsonPathApiFilter = String.format(API_NAME_FILTER, apiName);
        jsonPathFilter = String.format("%s.%s.%s.%s.".concat(filterName),
                jsonPathEnvironmentFilter, jsonPathApiFilter, jsonPathAuthenticationFilter, jsonPathUserFilter);
        return getResults(jsonObject, jsonPathFilter).get(index);
    }
}
