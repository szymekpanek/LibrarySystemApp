package pl.edu.wszib.repository;

import lombok.Getter;
import pl.edu.wszib.model.Book;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



@Getter
public class BookRepo {
    private final Map<String, Book> books = new HashMap<>();
    FileController fileControler = new FileController();

    public BookRepo() {
        fileControler.readFromFile(books); // Odczytaj dane z pliku
    }

    public void addBook(Scanner scanner) {
        System.out.println("Enter book details:");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn, false, null, null, null);
        books.put(isbn, newBook);

        fileControler.saveToFile(books);

        System.out.println("Book added successfully!");
    }


    public void searchBooksByAuthorOrTitleOrISBN(Scanner scanner) {
        System.out.print("Enter partial author, title, or ISBN: ");
        String searchTerm = scanner.nextLine();

        System.out.println("Books matching '" + searchTerm + "':");

        Collection<Book> matchingBooks = books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())
                        || book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())
                        || book.getISBN().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();

        if (matchingBooks.isEmpty()) {
            System.out.println("No books found for the given author, title, or ISBN.");
        }
        else {
            matchingBooks.forEach(book -> {
                String availability = book.isBorrowed() ? " (Borrowed)" : " (AVAILABLE)";
                System.out.println(book + availability);
            });
        }
    }


}
