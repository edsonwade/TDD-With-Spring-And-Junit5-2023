package code.vanilson.startup.datasets;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RunConfiguration {

    @Value("${browser}")
    public String browser;
    @Value("${textType}")
    private String texType;

}
