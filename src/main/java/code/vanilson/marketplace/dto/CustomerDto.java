package code.vanilson.marketplace.dto;

import code.vanilson.marketplace.model.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */

@Value
@Getter
@Setter
public class CustomerDto implements Serializable {
    Long customerId;
    @NotNull
    String name;
    @NotNull
    @Size(max = 45)
    String email;
    @NotNull
    String address;

    // Annotated constructor to specify how Jackson should deserialize JSON into your object
    @JsonCreator
    public CustomerDto(
            @JsonProperty("customerId") Long customerId,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

}