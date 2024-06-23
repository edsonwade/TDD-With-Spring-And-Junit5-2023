package code.vanilson.marketplace.stepdefinitions;

/**
 * CucumberSpringConfiguration
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@CucumberContextConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:config/application-test.yml"})
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
}