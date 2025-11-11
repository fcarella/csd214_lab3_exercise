package csd214_fall2025.pojos;

import java.util.Objects;

/**
 * @author fcarella
 */

/**
 * DTO for {@link csd214.bookstore.f25.entities.PublicationEntity}
 */
/**
 * DTO for {@link csd214_fall2025.entities.PublicationEntity}
 */
public abstract class Publication extends Product {
    private String title;
    private double price;
    private int copies;
    private String isbn_10;
    private String description;


    public Publication(String title, double price, int copies, String isbn_10, String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.isbn_10 = isbn_10;
        this.description = description;
    }

    public Publication() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Publication that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getPrice(), that.getPrice()) == 0 && getCopies() == that.getCopies() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getIsbn_10(), that.getIsbn_10()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getPrice(), getCopies(), getIsbn_10(), getDescription());
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", isbn_10='" + isbn_10 + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }


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

    @Override
    public void sellItem() {
        copies--;
    }

    //    @Override
//    public String toString() {
//        String format="%-20s Title: %-20s Price: $%-4.2f Copies: %-2d";
//        return String.format(format,this.getClass().getSimpleName(), title, price, copies);
//
//    }
}