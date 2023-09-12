package code.vanilson.startup.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
public class InventoryRecord implements Serializable {
    private static final long serialVersionUID=3445645778L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    @JsonProperty("id")
    private Integer productId;
    private Integer quantity;
    private String productName;
    private String productCategory;

    public InventoryRecord() {
    }

    public InventoryRecord(int productId, Integer quantity, String productName, String productCategory) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productCategory = productCategory;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "InventoryRecord{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}