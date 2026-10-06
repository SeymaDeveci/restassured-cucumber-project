package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import models.UpdateUserRequest;
import utils.ConfigReader;
import utils.TestDataReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    @Given("API base URL ayarlanmıştır")
    public void apiBaseUrlAyarlanmistir() {
        RestAssured.baseURI = ConfigReader.get("base.url.reqres");
    }

    @When("kullanıcı {string} endpoint'ine GET isteği gönderir")
    public void kullaniciEndpointineGetIstegiGonderir(String endpoint) {
        TestContext.response = given()
                .when()
                .get(endpoint);
    }

    @Then("response status code {int} olmalıdır")
    public void responseStatusCodeOlmalidir(int expectedStatusCode) {
        TestContext.response.then().statusCode(expectedStatusCode);
    }

    @And("response {string} alanı {int} olmalıdır")
    public void responseAlaniOlmalidir(String fieldName, int expectedValue) {
        TestContext.response.then().body(fieldName, equalTo(expectedValue));
    }

    @When("kullanıcı {string} endpoint'ine {string} ismiyle PUT isteği gönderir")
    public void kullaniciEndpointinePutIstegiGonderir(String endpoint, String name) throws JsonProcessingException {
        UpdateUserRequest requestData = TestDataReader.readTestData(
                "src/test/resources/testdata/user_payload.json",
                UpdateUserRequest.class
        );

        requestData.setName(name); // Gherkin'den gelen ismi POJO'ya set ediyoruz

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(requestData); // POJO'yu tekrar JSON string'e çeviriyoruz

        TestContext.response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @When("kullanıcı {string} endpoint'ine DELETE isteği gönderir")
    public void kullaniciEndpointineDeleteIstegiGonderir(String endpoint) {
        TestContext.response = given()
                .when()
                .delete(endpoint);
    }
}