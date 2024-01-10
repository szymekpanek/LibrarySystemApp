package pl.edu.wszib.repository;

import pl.edu.wszib.model.Book;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class FileController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void readFromFile(Map<String, Book> books) {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/books.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String isbn = parts[2].trim();
                    boolean isBorrowed = parts.length >= 4 && Boolean.parseBoolean(parts[3].trim());
                    String borrowedBy = parts.length >= 5 ? parts[4].trim() : "";
                    String borrowDate = parts.length >= 6 ? parts[5].trim() : "";
                    String returnDate = parts.length >= 7 ? parts[6].trim() : "";

                    Book book = new Book(title, author, isbn, isBorrowed, borrowedBy, borrowDate, returnDate);
                    books.put(isbn, book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(Map<String, Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/books.txt"))) {
            for (Book book : books.values()) {
                writer.write(book.getTitle() + ";" + book.getAuthor() + ";" + book.getISBN() +
                        ";" + book.isBorrowed() + ";" + book.getBorrowedBy() + ";" + book.getBorrowDate() + ";" + book.getReturnDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(Scanner scanner, Map<String, Book> books) {
        System.out.print("Enter ISBN of the book you want to borrow: ");
        String isbn = scanner.nextLine();

        Book book = books.get(isbn);

        if (book != null) {
            if (!book.isBorrowed()) {
                System.out.print("Enter your full name: ");
                String borrower = scanner.nextLine();

                book.setBorrowed(true);
                book.setBorrowedBy(borrower);
                book.setBorrowDate(dateFormat.format(new Date()));

                Date returnDate = new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000L);
                book.setReturnDate(dateFormat.format(returnDate));

                System.out.println("Book borrowed successfully!");


                saveToFile(books);
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(Map<String, Book> books, Scanner scanner) {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        if (books.containsKey(isbn)) {
            Book book = books.get(isbn);

            if (book.isBorrowed()) {
                book.setBorrowed(false);
                book.setBorrowedBy("");
                book.setBorrowDate("");
                book.setReturnDate("");

                System.out.println("Book returned successfully!");

                saveToFile(books); // Nadpisz plik po zwróceniu książki
            } else {
                System.out.println("This book is not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
}
