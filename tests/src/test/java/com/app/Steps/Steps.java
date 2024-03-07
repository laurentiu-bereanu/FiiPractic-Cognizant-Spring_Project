package com.app.Steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {

    @Given("the test step")
    public void testSimpleGetRequest() {
        RestAssured.baseURI = "http://localhost:8080";

        // Send a GET request to "/posts/1"
        Response response = given()
                .when()
                .get("/user");

        // Assertions
        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected content type");

        // You can perform additional assertions based on the response body, headers, etc.
        // For example, you might want to validate the response body using JSONPath.
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                response.jsonPath().getString("title"),
                "Unexpected title in the response");
    }
}
