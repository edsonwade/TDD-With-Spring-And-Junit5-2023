package code.vanilson.startup.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@JsonPropertyOrder({"id", "name", "quantity", "price"})
public class Product implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;
    @Column(
            precision = 8,
            scale = 4)
    private double price;

    public Product() {
    }

    public Product(Integer id, String name, Integer quantity, Integer version, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.version = version;
        this.price = price;
    }

    public Product(String name, Integer quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Product setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", version=" + version +
                ", price=" + price +
                '}';
    }
}
