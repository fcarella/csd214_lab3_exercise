package csd214_fall2025.entities;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class TicketEntity extends ProductEntity {
    private String description;
    private double price;

    public TicketEntity() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void edit() {
        System.out.println("--- Editing Ticket " + (getId() != null ? " (ID: " + getId() + ")" : "") + " ---");
        System.out.print("Enter new description (current: '" + (this.description == null ? "" : this.description) + "'): ");
        setDescription(super.getInput(this.description == null ? "" : this.description));
        System.out.print("Enter new price (current: " + getPrice() + "): ");
        setPrice(super.getInput(getPrice()));
        System.out.println("Ticket updated.");
    }

    @Override
    public void initialize() {
        System.out.println("--- Initializing New Ticket ---");
        System.out.print("Enter description: ");
        setDescription(super.getInput(""));
        System.out.print("Enter price: ");
        setPrice(super.getInput(0.0));
        System.out.println("Ticket initialized.");
    }

    @Override
    public void sellItem() {
        // Tickets are typically single-use, so there are no "copies" to decrement.
        System.out.println("Sold Ticket: '" + getDescription() + "'.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription(), getPrice());
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "description='" + description + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }
}