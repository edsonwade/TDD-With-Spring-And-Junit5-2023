package code.vanilson.startup.functionaltest;

import code.vanilson.startup.StartupApplication;
import code.vanilson.startup.model.Product;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

@CucumberContextConfiguration
@ContextConfiguration(classes = {FunctionalTestConfiguration.class})
@SpringBootTest(classes = StartupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductSteps {
    private ResponseEntity<Product[]> response;
    RestTemplate restTemplate = new RestTemplate();


    @BeforeAll
    public static void cenas() {
        System.out.println("Before All Scenarios - Executed before all scenarios in the feature");

    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All Scenarios - Executed after all scenarios in the feature");

    }


    @Given("the product service is available")
    public void theProductServiceIsAvailable() {
        assertTrue(true);
    }

    @When("a GET request is made to {string}")
    public void aGETRequestIsMadeTo(String endpoint) {


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        this.response = restTemplate.exchange(
                "http://localhost:8081" + endpoint,
                HttpMethod.GET,
                entity,
                Product[].class
        );

    }


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals("Unexpected response status code", expectedStatusCode, response.getStatusCodeValue());
    }

    @And("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String expectedContentType) {
        assertTrue("Response should contain the expected content type",
                Objects.requireNonNull(response.getHeaders().getContentType()).toString().contains(expectedContentType));
    }

    @Then("the response should contain the following products:")
    public void responseShouldContainProducts(List<Product> expectedProducts) {
        Product[] actualProducts = response.getBody();
        assertNotNull("Response should not be null", actualProducts);

        // Verify each expected product in the response.
        for (Product expectedProduct : expectedProducts) {
            boolean found = false;
            for (Product actualProduct : actualProducts) {
                if (Objects.equals(expectedProduct.getProductId(), actualProduct.getProductId()) &&
                        expectedProduct.getName().equals(actualProduct.getName()) &&
                        Objects.equals(expectedProduct.getQuantity(), actualProduct.getQuantity()) &&
                        Objects.equals(expectedProduct.getVersion(), actualProduct.getVersion())) {
                    found = true;
                    break;
                }
            }
            assertTrue("Expected product not found in the response: " + expectedProduct.toString(), found);
        }
    }

    @DataTableType
    public Product convert(Map<String, String> entry) {
        return new Product(
                Integer.parseInt(entry.get("id")),
                entry.get("name"),
                Integer.parseInt(entry.get("quantity")),
                Integer.parseInt(entry.get("version"))
        );
    }
}
