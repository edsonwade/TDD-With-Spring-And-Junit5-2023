package code.vanilson.startup;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:config/runConfiguration.properties"})
public class ProductCucumberConfiguration {
}
