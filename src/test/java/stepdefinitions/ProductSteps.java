package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import utils.ConfigReader;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductSteps {

    @Given("DummyJSON base URL ayarlanmıştır")
    public void dummyJsonBaseUrlAyarlanmistir() {
        RestAssured.baseURI = ConfigReader.get("base.url.dummyjson");
    }

    @Then("response şemaya uygun olmalıdır")
    public void responseSemayaUygunOlmalidir(){
        TestContext.response.then().body(matchesJsonSchemaInClasspath("schemas/product_schema.json"));
    }
}