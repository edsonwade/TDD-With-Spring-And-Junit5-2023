package code.vanilson.startup.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_orderItems")
@Getter
@Setter
@JsonPropertyOrder({"orderItemId", "order", "product","quantity"})
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 54667873578L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long orderItemId;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    private int quantity;

    public OrderItem() {
        //default constructor
    }

    public OrderItem(Long orderItemId, int quantity) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
    }

    public OrderItem(Long orderItemId, Order order, Product product, int quantity) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        if (!Objects.equals(orderItemId, orderItem.orderItemId))
            return false;
        if (!Objects.equals(order, orderItem.order)) return false;
        return Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        int result = orderItemId != null ? orderItemId.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
