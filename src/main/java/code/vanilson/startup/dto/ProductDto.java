package code.vanilson.startup.dto;

import code.vanilson.startup.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@Getter
@Setter
public class ProductDto implements Serializable {
    Integer productId;
    @NotNull
    @Size(max = 45)
    String name;
    @NotNull
    @Size(max = 1000)
    Integer quantity;
    Integer version;

    public ProductDto() {
        // Default constructor
    }

    public ProductDto(Integer productId, String name, Integer quantity, Integer version) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.version = version;
    }

    public ProductDto(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}