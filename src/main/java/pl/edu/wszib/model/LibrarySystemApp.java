package pl.edu.wszib.model;

import pl.edu.wszib.authorization.Authenticator;
import pl.edu.wszib.gui.GUI;
import pl.edu.wszib.repository.BookRepo;
import pl.edu.wszib.repository.FileController;

public class LibrarySystemApp {
    public static void main(String[] args) {
        BookRepo bookRepo = new BookRepo();
        FileController fileController = new FileController();
        Authenticator authenticator = new Authenticator();
        boolean run = false;
        int counter = 0;

        while (!run && counter < 3) {
            authenticator.authenticate(GUI.readAuthData());
            run = Authenticator.loggedUser != null;
            counter++;
        }


        while (true) {
            String choice = GUI.showMenuAndReadChoose();

            switch (choice) {
                case "1" -> GUI.listOfBooks(bookRepo.getBooks());
                case "2" -> authenticator.isAdmin(() -> bookRepo.addBook(GUI.scan));
                case "3" -> fileController.returnBook(bookRepo.getBooks(), GUI.scan);
                case "4" -> {

                    bookRepo.searchBooksByAuthorOrTitleOrISBN(GUI.scan);
                }
                case "5" -> authenticator.isAdmin(() -> GUI.printBorrowedBooks(bookRepo.getBooks()));
                case "6" -> fileController.borrowBook(GUI.scan, bookRepo.getBooks());
                case "7" -> GUI.listOverdueBooks(bookRepo.getBooks());
                case "8" -> {
                    System.out.println("Exiting the library system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

