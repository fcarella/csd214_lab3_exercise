package csd214_fall2025.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public abstract class PublicationEntity extends ProductEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "copies")
    private int copies;

    @Column(name = "isbn_10")
    private String isbn_10;

    @Column(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PublicationEntity() {
    }

    public PublicationEntity(String title, double price, int copies, String isbn_10, String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.isbn_10 = isbn_10;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PublicationEntity{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", isbn_10='" + isbn_10 + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }

}