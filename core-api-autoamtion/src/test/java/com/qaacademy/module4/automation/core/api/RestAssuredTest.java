package com.qaacademy.module4.automation.core.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

@DisplayName("Rest Assured")
@Tag("UnitTest")
public class RestAssuredTest {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;
    private static String key;
    private static String token;

    @BeforeAll
    public static void setupAll() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        key = "5542bb122572b0a33d33fc15891f1a43";
        token = "ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5";
    }

    @Test
    public void testTrelloSimple() {
        var url = "https://api.trello.com";
        var endpoint = url.concat("/1/members/me/boards?key=5542bb122572b0a33d33fc15891f1a43&token=ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5");
        RestAssured
                .given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testTrelloWithRequestSpecification() {
        var endpoint = "/1/members/me/boards?key=5542bb122572b0a33d33fc15891f1a43&token=ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5";
        RestAssured
                .given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testTrelloWhitResponseSpecification() {
        var endpoint = "/1/members/me/boards?key=5542bb122572b0a33d33fc15891f1a43&token=ATTAf525f4a28dfaeb036832f3d3fa84babd06f93fd6b8654f7e6f8a297273d2a2aeD0BCF9C5";
        RestAssured
                .given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpecification);
    }

    @Test
    public void testTrelloWhitCustomValidation() {
        var endpoint = "/1/boards";
        var boardName = "Automation-test-01";
        var body = """
                {
                    "name" : "%s"
                }
                """;
        RestAssured
                .given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .body(String.format(body, boardName))
                .post(endpoint)
                .then()
                .spec(responseSpecification)
                .body("name", Matchers.equalTo(boardName));
    }

    @Test
    public void testTrelloWhitJUnit() {
        var endpoint = "/1/members/me/boards";
        Response response = RestAssured
                .given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .queryParam("key", key)
                .queryParam("token", token)
                .get(endpoint);

        var statusCodeExpected = 200;
        var statusCodeActual = response.getStatusCode();

        Assertions.assertEquals(statusCodeExpected, statusCodeActual);
    }

    @Test
    public void testTrelloWhitJsonSchema() {
        var endpoint = "/1/boards";
        var boardName = "Automation-test-01";
        var body = """
                {
                    "name" : "%s"
                }
                """;
        RestAssured
                .given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .body(String.format(body, boardName))
                .post(endpoint)
                .then()
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/schemas/schemastest.json")));
    }
}
