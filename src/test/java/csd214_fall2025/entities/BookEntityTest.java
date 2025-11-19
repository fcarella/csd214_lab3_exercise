package csd214_fall2025.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookEntity Unit Tests")
class BookEntityTest {

    // To test methods that use a Scanner on System.in, we need to redirect the standard I/O streams.
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream testOutput;

    @BeforeEach
    void setUp() {
        // Capture System.out to a byte array
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    @AfterEach
    void tearDown() {
        // Restore original System.in and System.out streams
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    @DisplayName("Should set and get author correctly")
    void testGetAndSetAuthor() {
        BookEntity book = new BookEntity();
        String expectedAuthor = "J.R.R. Tolkien";
        book.setAuthor(expectedAuthor);
        assertEquals(expectedAuthor, book.getAuthor(), "The author should be correctly set and retrieved.");
    }

    @Test
    @DisplayName("initialize() should correctly populate a BookEntity from user input")
    void testInitialize() {
        // 1. Prepare simulated user input
        String simulatedInput = "The Hobbit\n"          // Title
                + "J.R.R. Tolkien\n"        // Author
                + "978-0345339683\n"        // ISBN-10
                + "A classic fantasy.\n"   // Description
                + "14.99\n"                 // Price
                + "10\n";                   // Copies

        // 2. Create the book and set its input stream
        BookEntity book = new BookEntity();
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        book.setSystemInput(testIn); // This correctly sets System.in AND re-initializes the book's scanner

        // 3. Call the method to be tested
        book.initialize();

        // 4. Assert that the book's properties match the input
        assertEquals("The Hobbit", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertEquals("978-0345339683", book.getIsbn_10());
        assertEquals("A classic fantasy.", book.getDescription());
        assertEquals(14.99, book.getPrice());
        assertEquals(10, book.getCopies());
    }

    @Test
    @DisplayName("edit() should correctly update fields and respect empty input")
    void testEdit() {
        // 1. Create a pre-existing book
        BookEntity book = new BookEntity("Dune", 17.99, 8, "978-0441013593", "Sci-fi classic.", "Frank Herbert");
        book.setId(1L); // Set an ID to simulate an existing item

        // 2. Prepare simulated input: update title and price, but keep author and copies the same (empty input)
        String simulatedInput = "Dune Messiah\n" // New Title
                + "\n"               // Keep Author (empty input)
                + "20.50\n"          // New Price
                + "\n";              // Keep Copies

        // 3. Set the input stream for the book object
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        book.setSystemInput(testIn); // Re-initialize the scanner with our test input

        // 4. Call the method to be tested
        book.edit();

        // 5. Assert that the properties have been updated correctly
        assertEquals("Dune Messiah", book.getTitle(), "Title should be updated.");
        assertEquals("Frank Herbert", book.getAuthor(), "Author should remain unchanged.");
        assertEquals(20.50, book.getPrice(), "Price should be updated.");
        assertEquals(8, book.getCopies(), "Copies should remain unchanged.");

        // 6. Assert that the output prompts were displayed correctly
        String output = testOutput.toString();
        assertTrue(output.contains("--- Editing Book 'Dune' (ID: 1) ---"));
        assertTrue(output.contains("Enter new title (current: 'Dune'):"));
        assertTrue(output.contains("Enter new author (current: 'Frank Herbert'):"));
    }

    @Test
    @DisplayName("sellItem() should decrement copies when stock is available")
    void testSellItemWithStock() {
        BookEntity book = new BookEntity();
        book.setCopies(5);
        book.setTitle("1984");

        book.sellItem();

        assertEquals(4, book.getCopies(), "Copies should be decremented by 1.");
        assertTrue(testOutput.toString().contains("Sold Book: '1984'"));
    }

    @Test
    @DisplayName("sellItem() should not decrement copies when out of stock")
    void testSellItemOutOfStock() {
        BookEntity book = new BookEntity();
        book.setCopies(0);
        book.setTitle("Animal Farm");

        book.sellItem();

        assertEquals(0, book.getCopies(), "Copies should remain 0 when out of stock.");
        assertTrue(testOutput.toString().contains("Book 'Animal Farm' is out of stock."));
    }

    @Test
    @DisplayName("equals() and hashCode() should be consistent")
    void testEqualsAndHashCode() {
        BookEntity book1 = new BookEntity("Title", 10.0, 5, "123", "Desc", "Author");
        BookEntity book2 = new BookEntity("Title", 10.0, 5, "123", "Desc", "Author");
        BookEntity book3 = new BookEntity("Different Title", 10.0, 5, "123", "Desc", "Author");

        // Test for equality
        assertEquals(book1, book2, "Two books with identical properties should be equal.");
        assertEquals(book1.hashCode(), book2.hashCode(), "Hash codes should be equal for equal objects.");

        // Test for inequality
        assertNotEquals(book1, book3, "Two books with different properties should not be equal.");
        assertNotEquals(book1.hashCode(), book3.hashCode(), "Hash codes should ideally be different for non-equal objects.");
    }
}