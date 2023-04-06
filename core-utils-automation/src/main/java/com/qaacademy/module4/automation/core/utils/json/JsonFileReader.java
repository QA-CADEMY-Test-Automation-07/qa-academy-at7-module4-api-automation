package com.qaacademy.module4.automation.core.utils.json;

import com.qaacademy.module4.automation.core.utils.commons.ReservedWords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reads data from .json file.
 */
public final class JsonFileReader {
    private static final Logger LOGGER = LogManager.getLogger(JsonFileReader.class.getSimpleName());
    private static final String ENCODING = "UTF-8";

    /**
     * Initializes an instance of {@link JsonFileReader}.
     */
    private JsonFileReader() {
    }

    /**
     * Loads content from a json file and returns it as JSONObject.
     *
     * @param jsonFilePath the path of json file.
     * @return json object.
     */
    public static JSONObject loadJsonObjectFromFile(final String jsonFilePath) {
        JSONObject jsonObject = new JSONObject();
        String jsonPath = jsonFilePath;
        if (!new File(jsonFilePath).exists()) {
            jsonPath = jsonFilePath.replace(ReservedWords.DOUBLE_DOT_AND_SLASH.val(),
                    ReservedWords.STRING_EMPTY.val());
        }
        try (InputStreamReader inputFile = new InputStreamReader(new FileInputStream(jsonPath), ENCODING)) {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(inputFile);
        } catch (IOException e) {
            LOGGER.warn("Problem while reading the file", e);
        } catch (ParseException e) {
            LOGGER.warn("Problem while parsing Json object", e);
        }
        return jsonObject;
    }
}
