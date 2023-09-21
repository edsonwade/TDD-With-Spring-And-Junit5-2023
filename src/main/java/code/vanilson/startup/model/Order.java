package code.vanilson.startup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@JsonPropertyOrder({"orderId", "customer", "localDateTime"})
public class Order implements Serializable {
    private static final long serialVersionUID = -234123578L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    private LocalDateTime localDateTime;
    @JsonIgnore
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;


    public Order() {
        //default constructor
    }

    public Order(Long orderId, LocalDateTime localDateTime) {
        this.orderId = orderId;
        this.localDateTime = localDateTime;
    }

    public Order(Long orderId, Customer customer, LocalDateTime localDateTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!Objects.equals(orderId, order.orderId)) return false;
        if (!Objects.equals(customer, order.customer)) return false;
        return Objects.equals(localDateTime, order.localDateTime);
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
