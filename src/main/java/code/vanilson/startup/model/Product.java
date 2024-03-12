package code.vanilson.startup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "tb_products")
@JsonPropertyOrder({"id","name","quantity","version"})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    @JsonProperty("id")
    private Integer productId;
    private String name;
    private Integer quantity;
    private Integer version;


    public Product() {
        // default constructor
    }

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Product(Integer id, String name, Integer quantity, Integer version) {
       this.productId = id;
        this.name = name;
        this.quantity = quantity;
        this.version = version;
    }

    public Product(Integer id, String name, Integer quantity) {
       this.productId = id;
        this.name = name;
        this.quantity = quantity;
    }

    public void setProductId(Integer id) {
       this.productId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", version=" + version +
                '}';
    }
}
