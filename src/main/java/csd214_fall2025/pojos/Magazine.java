package csd214_fall2025.pojos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author fcarella
 */

public class Magazine extends Publication {

    private int orderQty;
    private LocalDate  currIssue;

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public LocalDate getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(LocalDate currIssue) {
        this.currIssue = currIssue;
    }

    public Magazine(String title, double price, int copies, String isbn_10, String description, int orderQty, LocalDate currIssue) {
        super(title, price, copies, isbn_10, description);
        this.orderQty = orderQty;
        this.currIssue = currIssue;
    }

    public Magazine() {
    }


    @Override
    public void edit() {
        System.out.println("Edit Title (" + getTitle() + " [enter for no changes])");
        setTitle(getInput(getTitle()));
        System.out.println("Edit copies (" + getCopies() + " [enter for no changes])");
        setCopies(getInput(getCopies()));
        System.out.println("Edit price (" + getPrice() + " [enter for no changes])");
        setPrice(getInput(getPrice()));
        System.out.println("Edit Order Quantity (" + getOrderQty() + " [enter for no changes])");
        setOrderQty(getInput(getOrderQty()));
        System.out.println("Edit Current Issue (" + getCurrIssue() + " in \"dd-MMM-yyyy\" format [enter for no changes])");
        setCurrIssue(getInput(getCurrIssue()));
    }

    @Override
    public void initialize() {
        System.out.println("Enter Title:");
        setTitle(getInput("no title"));
        System.out.println("Enter copies:");
        setCopies(getInput(0));
        System.out.println("Enter price:");
        setPrice(getInput(0.0d));
        System.out.println("Enter Order Quantity:");
        setOrderQty(getInput(0));
        System.out.println("Enter Current Issue:");
        setCurrIssue(getInput(LocalDate.now()));
    }

    @Override
    public String toString() {
        String format="Order Qty: %-20s Current Issue: %-20s";
        return super.toString() + " " + String.format(format, getOrderQty(), getCurrIssue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(orderQty, magazine.orderQty) && Objects.equals(currIssue, magazine.currIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderQty, currIssue);
    }
}