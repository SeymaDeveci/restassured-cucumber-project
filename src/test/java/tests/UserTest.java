package tests;

import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class UserTest {

    @Test
    public void getUserByIdTest(){
        Response response = given().baseUri("https://reqres.in/api")
                .when().get("/users/2");
        User user = response.jsonPath().getObject("data", User.class);
        System.out.println(user.getEmail());
    }
}
