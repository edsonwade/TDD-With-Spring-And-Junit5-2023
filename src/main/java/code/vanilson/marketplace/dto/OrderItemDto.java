package code.vanilson.marketplace.dto;

import code.vanilson.marketplace.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    Long orderItemId;
    @NotNull
    OrderDto order;
    @NotNull
    ProductDto product;
    int quantity;
}