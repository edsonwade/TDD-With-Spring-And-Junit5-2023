package code.vanilson.startup.config;

import code.vanilson.startup.StartupApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = StartupApplication.class) // Replace YourApplication with your Spring Boot application class
public class CucumberSpringConfiguration {

}