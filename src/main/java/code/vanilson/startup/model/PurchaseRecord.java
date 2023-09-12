package code.vanilson.startup.model;

import lombok.Getter;

import java.io.Serializable;
@Getter
public class PurchaseRecord implements Serializable {

    private static final long serialVersionUID=234664224L;
    private Integer productId;
    private Integer quantityPurchased;

    public PurchaseRecord() {
    }

    public PurchaseRecord(Integer productId, Integer quantityPurchased) {
        this.productId = productId;
        this.quantityPurchased = quantityPurchased;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }
}
