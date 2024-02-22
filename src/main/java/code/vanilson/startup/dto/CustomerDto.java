package code.vanilson.startup.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link code.vanilson.startup.model.Customer}
 */

@Value
public class CustomerDto implements Serializable {
    Long customerId;
    @NotNull
    String name;
    @NotNull
    @Size(max = 45)
    String email;
    @NotNull
    String address;

}