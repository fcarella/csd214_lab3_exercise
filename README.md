# CSD214 Lab 3 Exercise
## Bookstore Inventory Management System

This project is a console-based inventory management application for a bookstore. It is built using Java and Maven, and it demonstrates a clean, multi-layered architecture that separates concerns for maintainability and scalability.

The application allows users to manage different types of saleable items, such as books and tickets, and supports multiple data storage options (in-memory and MySQL) that can be selected at runtime.

## Features

-   **Add Items**: Add new books or tickets to the inventory.
-   **Edit Items**: Modify the details of existing items using their ID.
-   **List Items**: Display a formatted list of all items currently in the inventory.
-   **Dynamic Data Source**: Choose between a temporary in-memory database or a persistent MySQL database at startup.
-   **Data Population**: Automatically populate the in-memory database with sample data for demonstration.

*(Note: "Delete Items" and "Sell Items" are menu options but are not yet implemented.)*

## Architectural Highlights

This project was designed to showcase modern software development best practices:

-   **Layered Architecture**: The code is organized into distinct layers:
    -   **Entities (`entities`)**: JPA-annotated classes that map to database tables.
    -   **POJOs/DTOs (`pojos`)**: Plain Old Java Objects used for data transfer, decoupling the business logic from the database schema.
    -   **Repository Layer (`repositories`)**: Implements the Repository Pattern to abstract data access, making the application independent of the database technology.
    -   **Service Layer (`services`)**: Contains the core business logic, orchestrating calls to the repository layer.
-   **Dependency Injection (DI)**: The application's entry point (`Main.java`) acts as the "Composition Root," creating and injecting the necessary dependencies (like the chosen repository and the service) into the main `App` class.
-   **JPA & Hibernate**: Object-Relational Mapping (ORM) is managed by Hibernate through the Jakarta Persistence API (JPA), simplifying database interactions.
-   **Dual Persistence Units**: The `persistence.xml` file is configured with two separate persistence units:
    -   `default`: For the persistent MySQL database (`hibernate.hbm2ddl.auto="update"`).
    -   `h2`: For the temporary in-memory database (`hibernate.hbm2ddl.auto="create-drop"`), perfect for testing and development.

## Technologies Used

-   **Language**: Java 23
-   **Build Tool**: Apache Maven
-   **Database ORM**: JPA / Hibernate
-   **Databases**:
    -   MySQL (for persistent storage)
    -   H2 (for in-memory storage)
-   **Containerization**: Docker and Docker Compose (for running MySQL)
-   **Testing**: JUnit 5

## Prerequisites

Before you begin, ensure you have the following installed on your system:
-   Java Development Kit (JDK) 21 or newer
-   Apache Maven
-   Docker and Docker Compose (recommended for the database setup)

## How to Run

Follow these steps to get the application running.

### 1. Set Up the Database (Using Docker)

The easiest way to run the required MySQL database is with Docker.

1.  Open a terminal in the project's root directory (where `docker-compose.yml` is located).
2.  Run the following command to start the MySQL container in the background:
    ```sh
    docker-compose up -d
    ```
    This will start a MySQL 8.0 server on your local machine.

### 2. Verify Database Configuration

The application is pre-configured to connect to the Docker container. You can verify the settings in `src/main/resources/META-INF/persistence.xml`.

-   **URL**: `jdbc:mysql://localhost:3333/csd214-lab0`
-   **Username**: `csd214`
-   **Password**: `itstudies12345`

The `docker-compose.yml` file maps port `3333` on your host machine to port `3306` inside the container. The `persistence.xml` should connect to `localhost:3333`.

*(Note: The database name in `docker-compose.yml` is `csd214-lab0`, but `persistence.xml` points to `bookstore`. For it to work, change the database URL in `persistence.xml` to `jdbc:mysql://localhost:3333/csd214-lab0` or update the `MYSQL_DATABASE` value in `docker-compose.yml` to `bookstore` before running `docker-compose up`.)*

### 3. Build and Run the Application

1.  Use Maven to build the project and install dependencies:
    ```sh
    mvn clean install
    ```
2.  Run the application using the Maven exec plugin:
    ```sh
    mvn exec:java -Dexec.mainClass="csd214_fall2025.Main"
    ```
3.  When the application starts, you will be prompted to choose a repository.
    -   Enter `1` to use the **In-Memory** repository. The data will be populated with samples but will be lost when the app closes.
    -   Enter `2` to use the **MySQL** repository. The data will be saved permanently in the database.

## Project Structure

```
├── pom.xml                 # Maven build configuration
├── docker-compose.yml      # Docker configuration for MySQL
└── src
    ├── main
    │   ├── java
    │   │   └── csd214_fall2025
    │   │       ├── App.java            # Main application loop and UI logic
    │   │       ├── Main.java           # Application entry point (composition root)
    │   │       ├── entities            # JPA entity classes (BookEntity, etc.)
    │   │       ├── pojos               # DTOs and business objects (Book, Ticket, etc.)
    │   │       ├── repositories        # Data access layer (Repository pattern interfaces and implementations)
    │   │       └── services            # Business logic layer (ProductService)
    │   └── resources
    │       └── META-INF
    │           └── persistence.xml     # JPA/Hibernate configuration
    └── test
        └── java                        # (Test source files would go here)
```