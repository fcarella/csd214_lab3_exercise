package csd214_fall2025;


import csd214_fall2025.entities.ProductEntity;
import csd214_fall2025.repositories.InMemoryProductRepository;
import csd214_fall2025.repositories.MySQLProductRepository;
import csd214_fall2025.repositories.Repository;
import csd214_fall2025.services.ProductService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the repository to use:");
        System.out.println("1. In-Memory (data is temporary, resets on restart)");
        System.out.println("2. MySQL (data is persistent)");
        System.out.print("Enter choice: ");

        String choice = scanner.nextLine();
        Repository<ProductEntity> repository = null;

        switch (choice) {
            case "1":
                repository = new InMemoryProductRepository();
                System.out.println("Using In-Memory repository.");
                break;
            case "2":
                try {
                    repository = new MySQLProductRepository();
                    System.out.println("Using MySQL repository.");
                } catch (Exception e) {
                    System.out.println("Error connecting to MySQL database: " + e.getMessage());
                    System.out.println("Please ensure the database is running and configured correctly in persistence.xml.");
                    return; // Exit if DB connection fails
                }
                break;
            default:
                System.out.println("Invalid choice. Exiting application.");
                return;
        }

        try {
            // 1. Create the service with the selected repository
            ProductService productService = new ProductService(repository);

            // 2. Create the App, injecting the service
            App app = new App(System.in, System.out, productService);

            // 3. Populate with data ONLY if using the in-memory repository
            if (repository instanceof InMemoryProductRepository) {
                app.populate();
            }

            // 4. Run the application
            app.run();

        } finally {
            // 5. When the app is finished, close the repository resources
            if (repository != null) {
                repository.close();
                System.out.println("\nRepository resources have been released. Application terminated.");
            }
        }
    }
}