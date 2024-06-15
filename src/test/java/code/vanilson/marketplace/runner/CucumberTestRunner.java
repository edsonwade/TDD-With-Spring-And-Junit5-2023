
package code.vanilson.marketplace.runner;

/**
 * CucumberTestRunner
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        glue = "code.vanilson.marketplace.stepdefinitions",
        tags = "not @ignore_customer_management"
)
public class CucumberTestRunner {
}