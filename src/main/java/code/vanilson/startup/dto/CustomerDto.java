package code.vanilson.startup.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

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

    List<OrderDto> orders;
}