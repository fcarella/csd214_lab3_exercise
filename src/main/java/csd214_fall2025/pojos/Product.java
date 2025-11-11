package csd214_fall2025.pojos;

import java.io.Serializable;
import java.util.Objects;


/**
 * DTO for {@link csd214.bookstore.f25.entities.ProductEntity}
 */
/**
 * DTO for {@link csd214_fall2025.entities.ProductEntity}
 */
public abstract class Product extends Editable implements Serializable, SaleableItem {
    private String productId;

    public Product(String productId) {
        this.productId = productId;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getProductId(), product.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProductId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                "} " + super.toString();
    }
}
