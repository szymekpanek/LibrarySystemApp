package pl.edu.wszib.repository;

import pl.edu.wszib.model.Book;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class FileControler {
    public void readFromFile(Map<String, Book> books) {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/books.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String isbn = parts[2].trim();
                    Book book = new Book(title, author, isbn);
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
                writer.write(book.getTitle() + ";" + book.getAuthor() + ";" + book.getISBN());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
