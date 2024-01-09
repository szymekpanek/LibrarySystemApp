package pl.edu.wszib.model;

import pl.edu.wszib.gui.GUI;
import pl.edu.wszib.repository.BookRepo;


public class LibrarySystemApp {
    public static void main(String[] args) {
        BookRepo bookRepo = new BookRepo();

        while (true) {
            String choice = GUI.showMenuAndReadChoose();

            switch (choice) {
                case "1" -> GUI.listOfBooks(bookRepo.getBooks());
                case "2" -> bookRepo.addBook(GUI.scan);
                case "3" -> bookRepo.addBook(GUI.scan);// Implementacja zwracania książki
                case "4" -> {
                    System.out.print("Enter partial author or title: ");
                    bookRepo.searchBooksByAuthorOrTitleOrISBN(GUI.scan);
                }
                case "5" -> {
                    System.out.println("Exiting the library system. Goodbye!");
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

