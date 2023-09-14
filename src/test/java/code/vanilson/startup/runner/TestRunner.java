//package code.vanilson.startup.runner;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features",
//        glue = {"code.vanilson.startup"},
//        plugin = {"pretty","html:target/cucumber-report", "json:target/cucumber.json"},
//        monochrome = true,
//        publish = true)
//public class TestRunner extends AbstractTestNGCucumberTests {
//
//    @BeforeClass
//    public static void setup() {
//        System.out.println("Ran the before");
//    }
//
//    @AfterClass
//    public static void teardown() {
//        System.out.println("Ran the after");
//    }
//
//}
