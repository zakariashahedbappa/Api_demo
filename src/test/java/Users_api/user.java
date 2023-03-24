package Users_api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class user {

    @Test
    public void user_details(){
        RestAssured.baseURI = "https://reqres.in/api";

        // Make a GET request to /users?page=2
        Response response = given()
                .param("page", "2")
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        // Validate total_pages in response is always equal to 2
        response.then().assertThat().body("total_pages", equalTo(2));

        // Validate status code is 200
        response.then().assertThat().statusCode(200);

        // Validate email contains "byron.fields@reqres.in"
        response.then().assertThat().body("data[0].email", equalTo("byron.fields@reqres.in"));

    }
}
