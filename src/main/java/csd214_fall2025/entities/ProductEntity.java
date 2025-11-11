package csd214_fall2025.entities;

import csd214_fall2025.pojos.Editable;
import csd214_fall2025.pojos.SaleableItem;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_entity")
public abstract class ProductEntity extends Editable implements Serializable, SaleableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductEntity() {
    }

    public ProductEntity(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId());
    }
}