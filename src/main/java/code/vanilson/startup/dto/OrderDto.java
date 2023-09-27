package code.vanilson.startup.dto;

import code.vanilson.startup.model.OrderItem;
import lombok.Value;

import javax.validation.constraints.NotNull;
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