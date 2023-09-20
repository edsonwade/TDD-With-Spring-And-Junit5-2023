package code.vanilson.startup.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_customers")
@Getter
@Setter
@JsonPropertyOrder({"customerId","name","email","address"})
public class Customer implements Serializable {
    private static final long serialVersionUID = 123578L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",nullable = false)
    private Long customerId;
    private String name;
    @Column(unique = true, length = 45)
    private String email;
    private String address;

    public Customer() {
        //default constructor
    }

    public Customer(Long customerId, String name, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(customerId, customer.customerId)) return false;
        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
