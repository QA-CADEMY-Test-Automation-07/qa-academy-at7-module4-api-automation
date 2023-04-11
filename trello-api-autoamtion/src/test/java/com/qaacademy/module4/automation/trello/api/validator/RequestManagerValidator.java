package com.qaacademy.module4.automation.trello.api.validator;

import com.qaacademy.module4.automation.core.utils.json.JsonPath;

public final class RequestManagerValidator {
    private RequestManagerValidator() {
    }

    public static String getBoardName(String json, String field, String value) {
        return JsonPath.getResultList(json, String.format("$[?(@.name == '%s')].".concat(field), value)).get(0).toString();
    }

    public static String getId(String json) {
        return JsonPath.getResult(json, "$.id").toString();
    }
}
