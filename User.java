import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private int borrowedBooksCount;
    private final int MAX_BORROW_LIMIT = 5;
    private List<String> borrowHistory;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.borrowedBooksCount = 0;
        this.borrowHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }

    public boolean canBorrowMoreBooks() {
        return borrowedBooksCount < MAX_BORROW_LIMIT;
    }

    public void borrowBook(String bookId) {
        borrowedBooksCount++;
        borrowHistory.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBooksCount--;
        borrowHistory.add("Returned: " + bookId);
    }

    public List<String> getBorrowHistory() {
        return borrowHistory;
    }

    @Override
    public String toString() {
        return "User [ID=" + id + ", Name=" + name + ", Borrowed Books=" + borrowedBooksCount + "]";
    }
}
