public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        MenuHandler menu = new MenuHandler(library);
        menu.loadLibraryData();
        menu.displayMainMenu();
        menu.saveLibraryData();
    }
}
