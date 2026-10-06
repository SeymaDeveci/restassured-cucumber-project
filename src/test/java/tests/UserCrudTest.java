package tests;

import io.restassured.RestAssured;
import models.UpdateUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    /*
    GET - Sayfalama Testi
     */
    @Test
    @DisplayName("Sayfalama Testi")
    public void getUserListTest() {
        given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .body("data.size()", greaterThan(0));
    }

    /*
    POST - Yeni Kullanıcı Oluşturma
     */
    @Test
    @DisplayName("Kullanıcı Ekleme Testi")
    public void createUserTest() {
        String requestBody = "{ \"name\": \"Seyma\", \"job\": \"QA Engineer\" }";
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Seyma"))
                .body("job", equalTo("QA Engineer"))
                .body("id", notNullValue());
    }

    /*
    PUT - Kullanıcı Güncelleme
     */
    @Test
    @DisplayName("Kullanıcı Güncelleme Testi")
    public void updateUserTest() {
        UpdateUserRequest requestData = TestDataReader.readTestData(
                "src/test/resources/testdata/user_payload.json",
                UpdateUserRequest.class
        );

        given()
                .contentType("application/json")
                .body(requestData)   // Rest Assured, POJO'yu otomatik JSON'a çevirebilir!
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(requestData.getName()));
    }

    /*
    DELETE - Kullanıcı Silme
     */
    @Test
    @DisplayName("Kullanıcı Silme Testi")
    public void deleteUserTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void getNonExistentUserTest() {
        given()
                .when()
                .get("/users/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void checkLocale() {
        System.out.println("Current locale: " + java.util.Locale.getDefault());
    }
}
