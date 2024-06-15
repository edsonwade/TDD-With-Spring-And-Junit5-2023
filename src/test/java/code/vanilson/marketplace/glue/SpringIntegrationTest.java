package code.vanilson.marketplace.glue;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringIntegrationTest
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class SpringIntegrationTest {
}