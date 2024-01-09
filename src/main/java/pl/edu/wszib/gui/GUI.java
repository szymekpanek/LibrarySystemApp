package pl.edu.wszib.gui;

import pl.edu.wszib.model.Book;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GUI {

    public static final Scanner scan = new Scanner(System.in);

    public static String showMenuAndReadChoose() {
        System.out.println('\n' +"1. List books");
        System.out.println("2. Add book"); //tylko admin
        System.out.println("3. Return book");
        System.out.println("4. Search book");
        System.out.println("5. Exit" + '\n');

        return scan.nextLine();
    }

    public static void listOfBooks(Map<String, Book> books) {
        System.out.println('\n' + "List of all books:");
        String booksInfo = books.values().stream()
                .map(book -> "Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", ISBN: " + book.getISBN())
                .collect(Collectors.joining("\n"));
        System.out.println(booksInfo + '\n');
    }



}
