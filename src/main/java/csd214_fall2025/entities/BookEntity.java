package csd214_fall2025.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import java.util.Objects;


@Entity
public class BookEntity extends PublicationEntity {
    @Column(name = "author")
    private String author;


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public BookEntity() {
    }


    public BookEntity(String title, double price, int copies, String isbn_10, String description) {
        super(title, price, copies, isbn_10, description);
    }

    public BookEntity(String title, double price, int copies, String isbn_10, String description, String author) {
        super(title, price, copies, isbn_10, description);
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAuthor(), that.getAuthor());
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor());
    }


    @Override
    public void edit() {
        System.out.println("--- Editing Book " + (getTitle() != null ? "'" + getTitle() + "'" : "") + (getId() != null ? " (ID: " + getId() + ")" : "") + " ---");
        System.out.print("Enter new title (current: '" + (getTitle() == null ? "" : getTitle()) + "'): ");
        setTitle(super.getInput(getTitle() == null ? "" : getTitle()));


        System.out.print("Enter new author (current: '" + (this.author == null ? "" : this.author) + "'): ");
        setAuthor(super.getInput(this.author == null ? "" : this.author));


        System.out.print("Enter new price (current: " + getPrice() + "): ");
        setPrice(super.getInput(getPrice()));


        System.out.print("Enter new copies (current: " + getCopies() + "): ");
        setCopies(super.getInput(getCopies()));
        System.out.println("Book updated.");
    }




    @Override
    public void initialize() {
        System.out.println("--- Initializing New Book ---");

        System.out.print("Enter title: ");
        setTitle(super.getInput("")); // Default empty string

        System.out.print("Enter author: ");
        setAuthor(super.getInput("")); // Default empty string

        System.out.print("Enter ISBN-10: ");
        setIsbn_10(super.getInput(""));

        System.out.print("Enter description: ");
        setDescription(super.getInput(""));

        System.out.print("Enter price: ");
        setPrice(super.getInput(0.0)); // Default 0.0

        System.out.print("Enter copies: ");
        setCopies(super.getInput(0)); // Default 0
        System.out.println("Book initialized.");
    }


    @Override
    public void sellItem() {
        if (getCopies() > 0) {
            setCopies(getCopies() - 1);
            System.out.println("Sold Book: '" + getTitle() + "' by " + author + ". Copies left: " + getCopies());
        } else {
            System.out.println("Book '" + getTitle() + "' is out of stock.");
        }
    }
    @Override
    public String toString() {
        return "BookEntity{" +
                "author='" + author + '\'' +
                "} " + super.toString();
    }


}