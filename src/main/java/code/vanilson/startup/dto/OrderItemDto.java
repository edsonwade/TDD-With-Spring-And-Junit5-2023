package code.vanilson.startup.dto;

import code.vanilson.startup.model.OrderItem;
import lombok.Value;

import javax.validation.constraints.NotNull;
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