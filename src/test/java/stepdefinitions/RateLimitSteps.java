package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimitSteps {

    private List<Integer> statusCodes = new ArrayList<>();

    @When("kullanıcı {string} endpoint'ine art arda {int} istek gönderir")
    public void kullanıcıArtArdaIstekGonderir(String endpoint, int requestCount) {
        statusCodes.clear();

        for (int i = 0; i < requestCount; i++) {
            int statusCode = given()
                    .when()
                    .get(endpoint)
                    .getStatusCode();

            statusCodes.add(statusCode);
        }
    }

    @Then("tüm isteklerdeki response status code {int} olmalıdır")
    public void enAzBirResponseStatusCodeOlmalıdır(int expectedStatusCode) {
        assertTrue(
                statusCodes.contains(expectedStatusCode),
                "Beklenen status code (" + expectedStatusCode + ") hiçbir response'ta bulunamadı. Alınan kodlar: " + statusCodes
        );
    }
}
