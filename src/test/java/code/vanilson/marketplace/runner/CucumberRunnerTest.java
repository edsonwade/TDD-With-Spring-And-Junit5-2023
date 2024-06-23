
package code.vanilson.marketplace.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

/**
 * CucumberTestRunner
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */


@Suite
@SelectClasspathResource("features")  // Adjust this package name as necessary
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "code.vanilson.marketplace.stepdefinitions")
@IncludeEngines("cucumber")
public class CucumberRunnerTest {
}