import java.io.IOException; // Add this import statement
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private Library library;
    private Scanner scanner;

    public MenuHandler(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Users");
            System.out.println("7. Search Book by Title");
            System.out.println("8. Search User by Name");
            System.out.println("9. Borrowing Report");
            System.out.println("10. Exit");

            int choice = Utility.getIntInput(scanner, "Choose an option (1-10): ", 1, 10);
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    lendBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    viewAllBooks();
                    break;
                case 6:
                    viewAllUsers();
                    break;
                case 7:
                    searchBookByTitle();
                    break;
                case 8:
                    searchUserByName();
                    break;
                case 9:
                    generateBorrowingReport();
                    break;
                case 10:
                    return;
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();
        library.addBook(title, author, category);
        System.out.println("Book added successfully.");
    }

    private void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        library.registerUser(name);
        System.out.println("User registered successfully.");
    }

    private void lendBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        if (library.lendBook(bookId, userId)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Failed to lend book.");
        }
    }

    private void returnBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        if (library.returnBook(bookId, userId)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return book.");
        }
    }

    private void viewAllBooks() {
        System.out.println("\n=== All Books ===");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private void viewAllUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : library.getUsers()) {
            System.out.println(user);
        }
    }

    private void searchBookByTitle() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        List<Book> results = library.searchBooksByTitle(title);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private void searchUserByName() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        List<User> results = library.searchUsersByName(name);
        if (results.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : results) {
                System.out.println(user);
            }
        }
    }

    private void generateBorrowingReport() {
        System.out.println("\n=== Borrowing Report ===");
        for (User user : library.getUsers()) {
            System.out.println(user.getName() + "'s Borrowing History:");
            for (String record : user.getBorrowHistory()) {
                System.out.println("  " + record);
            }
        }
    }

    public void loadLibraryData() {
        try {
            library.loadBooksFromFile();
            library.loadUsersFromFile();
        } catch (IOException e) {
            System.out.println("Failed to load data.");
        }
    }

    public void saveLibraryData() {
        try {
            library.saveBooksToFile();
            library.saveUsersToFile();
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}
