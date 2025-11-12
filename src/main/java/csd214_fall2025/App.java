package csd214_fall2025;


import csd214_fall2025.entities.BookEntity;
import csd214_fall2025.entities.ProductEntity;
import csd214_fall2025.entities.TicketEntity;
import csd214_fall2025.pojos.Editable;
import csd214_fall2025.pojos.SaleableItem;
import csd214_fall2025.repositories.Repository;
import csd214_fall2025.services.ProductService;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class App {
    private final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";

    private final int currentItem = 0;


    private final Scanner input;
    private final PrintStream out;
    private final ProductService productService;


    /**
     * Constructs the App with all its required dependencies.
     * @param in The input stream for user interaction (e.g., System.in).
     * @param out The output stream for displaying information (e.g., System.out).
     * @param productService The service that handles business logic.
     */
    public App(InputStream in, PrintStream out, ProductService productService) {
        this.input = new Scanner(in);
        this.out = out;
        this.productService = productService;
    }


    public void run() {
        while (true) {
            out.print(menu);
            String choiceStr = input.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    editItem();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    sellItem();
                    break;
                case 5:
                    listAny();
                    break;
                case 99:
                    out.println("Exiting application. Goodbye!");
                    return; // Exit the run method
                default:
                    out.println("Invalid choice, please try again.");
            }
        }
    }
    public boolean findItemExists(SaleableItem item){return false;};
    public SaleableItem findItem(SaleableItem item){return null;}
    public void editItem(){
        listAny(); // Show the user the items they can edit
        out.print("\nEnter the ID of the item to edit: ");
        String idStr = input.nextLine();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            out.println("Invalid ID format. Please enter a number.");
            return;
        }

        Optional<ProductEntity> productOpt = productService.getProductById(id);

        if (productOpt.isPresent()) {
            ProductEntity productToEdit = productOpt.get();

            // Delegate the editing process to the object itself
            productToEdit.edit();

            // Save the updated object back to the repository
            productService.saveProduct(productToEdit);
            out.println("Item with ID " + id + " updated successfully.");
        } else {
            out.println("Item with ID " + id + " not found.");
        }
    }
    public void editItem(Editable item){}
    public void deleteItem(){
        listAny(); // Show the user all available items
        out.print("\nEnter the ID of the item to delete: ");
        String idStr = input.nextLine();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            out.println("Invalid ID format. Please enter a number.");
            return;
        }

        // Find the item first to confirm it exists before deleting
        Optional<ProductEntity> productOpt = productService.getProductById(id);

        if (productOpt.isPresent()) {
            ProductEntity productToDelete = productOpt.get();
            out.println("You are about to delete the following item:");
            // Display item details for confirmation
            if (productToDelete instanceof BookEntity book) {
                out.printf(" -> ID: %d | Type: Book | Title: %s%n", book.getId(), book.getTitle());
            } else if (productToDelete instanceof TicketEntity ticket) {
                out.printf(" -> ID: %d | Type: Ticket | Description: %s%n", ticket.getId(), ticket.getDescription());
            }

            out.print("Are you sure you want to delete this item? (y/n): ");
            String confirmation = input.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                productService.deleteProduct(id);
                out.println("Item with ID " + id + " was deleted successfully.");
            } else {
                out.println("Deletion canceled.");
            }
        } else {
            out.println("Item with ID " + id + " not found.");
        }
    }
    public void populate(){
        out.println("\n--- Populating with initial data... ---");

        // Create 3 Books
        productService.createBook("The Hobbit", 14.99, 10, "978-0345339683", "A classic fantasy novel.", "J.R.R. Tolkien");
        productService.createBook("1984", 9.99, 15, "978-0451524935", "A dystopian social science fiction novel.", "George Orwell");
        productService.createBook("Dune", 17.99, 8, "978-0441013593", "A landmark science fiction novel.", "Frank Herbert");

        // Create 3 Tickets
        productService.createTicket("Concert Ticket - The Rockers", 75.50);
        productService.createTicket("Movie Premiere - Space Odyssey 2", 25.00);
        productService.createTicket("Museum Entry - Natural History", 30.00);

        out.println("--- Population complete. ---");
    }
    public void listAny(){
        out.println("\n--- All Items in Inventory ---");
        List<ProductEntity> products = productService.getAllProducts();

        if (products.isEmpty()) {
            out.println("No items found.");
        } else {
            for (ProductEntity product : products) {
                if (product instanceof BookEntity book) {
                    out.printf("ID: %d | Type: Book   | Title: %s | Author: %s | Price: $%.2f | Copies: %d%n",
                            book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getCopies());
                } else if (product instanceof TicketEntity ticket) {
                    out.printf("ID: %d | Type: Ticket | Description: %s | Price: $%.2f%n",
                            ticket.getId(), ticket.getDescription(), ticket.getPrice());
                } else {
                    // Fallback for any other product types
                    out.println(product.toString());
                }
            }
        }
        out.println("------------------------------");
    }
    public SaleableItem getItem(SaleableItem item){return null;}
    public void sellItem(){}
    public void listI(Object items){}

    public void addItem() {
        out.println("\n--- Add New Item ---");
        out.println("1. Book");
        out.println("2. Ticket");
        out.print("Enter choice: ");
        String choiceStr = input.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            out.println("Invalid choice. Please enter a number.");
            return;
        }

        try {
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addTicket();
                    break;
                default:
                    out.println("Invalid choice. Returning to main menu.");
            }
        } catch (Exception e) {
            out.println("An error occurred while adding the item: " + e.getMessage());
            out.println("Please try again.");
        }
    }

    private void addBook() {
        BookEntity newBook = new BookEntity();
        newBook.initialize(); // This method will prompt the user for all details

        // The entity is now populated, save it using the service
        ProductEntity savedBook = productService.saveProduct(newBook);
        out.println("Book added successfully with ID: " + savedBook.getId());
    }

    private void addTicket() {
        out.println("\n--- Enter New Ticket Details ---");

        out.print("Enter description: ");
        String ticketDesc = input.nextLine();

        double ticketPrice = 0;
        while (true) {
            try {
                out.print("Enter price: ");
                ticketPrice = Double.parseDouble(input.nextLine());
                if (ticketPrice < 0) {
                    out.println("Price cannot be negative.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                out.println("Invalid price. Please enter a valid number (e.g., 25.50).");
            }
        }

        TicketEntity savedTicket = productService.createTicket(ticketDesc, ticketPrice);
        out.println("Ticket added successfully with ID: " + savedTicket.getId());
    }
    public void addItem(SaleableItem item){}


}