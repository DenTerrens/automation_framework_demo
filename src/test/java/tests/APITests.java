package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITests {

    @Test
    public void testGetRequest() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .param("page", 2)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .extract()
                .response();
    }

    @Test
    public void testPostRequest() {
        RestAssured.baseURI = "https://reqres.in";

        String requestBody = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)  // Check if the resource was created
                .body("name", equalTo("morpheus"))  // Verify the title in the response
                .extract()
                .response();
    }
}