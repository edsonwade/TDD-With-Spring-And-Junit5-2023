package code.vanilson.startup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_orders")
@Getter
@Setter
@JsonPropertyOrder({"orderId", "customer", "localDateTime", "orderItems"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class Order implements Serializable {
    private static final long serialVersionUID = -234123578L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "order_date")
    private LocalDateTime localDateTime;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    public Order() {
        //default constructor
    }

    public Order(Long orderId, LocalDateTime localDateTime) {
        this.orderId = orderId;
        this.localDateTime = localDateTime;
    }

    public Order(Long orderId, LocalDateTime localDateTime, Customer customer, Set<OrderItem> orderItems) {
        this.orderId = orderId;
        this.localDateTime = localDateTime;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // Set the back reference to the Order
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Order order = (Order) o;

        if (!Objects.equals(orderId, order.orderId)) {return false;}
        if (!Objects.equals(localDateTime, order.localDateTime)) {return false;}
        if (!Objects.equals(customer, order.customer)) {return false;}
        return Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);
        return result;
    }

}
