package code.vanilson.marketplace.dto;

import code.vanilson.marketplace.model.Order;
import code.vanilson.marketplace.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Value
public class OrderDto implements Serializable {
    Long orderId;
    LocalDateTime localDateTime;
    @NotNull
    CustomerDto customer;
    @NotNull
    List<OrderItem> orderItems;
}