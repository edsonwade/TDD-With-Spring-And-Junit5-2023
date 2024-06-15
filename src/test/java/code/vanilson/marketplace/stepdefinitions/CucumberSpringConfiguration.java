package code.vanilson.marketplace.stepdefinitions;

/**
 * CucumberSpringConfiguration
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */

import code.vanilson.marketplace.MarketplaceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MarketplaceApplication.class)
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}