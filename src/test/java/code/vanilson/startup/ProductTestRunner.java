package code.vanilson.startup;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources",
        glue ={"code.vanilson.startup"},
        publish = true)
public class ProductTestRunner {

}
