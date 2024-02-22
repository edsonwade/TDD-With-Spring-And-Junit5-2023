package code.vanilson.startup.dto;

import code.vanilson.startup.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link code.vanilson.startup.model.Order}
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