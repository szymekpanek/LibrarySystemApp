package pl.edu.wszib.gui;

import pl.edu.wszib.authorization.Authenticator;
import pl.edu.wszib.model.Book;
import pl.edu.wszib.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class GUI {

    public static final Scanner scan = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String showMenuAndReadChoose() {
        System.out.println('\n' + "1. List books");
        System.out.println("2. Add book"); //tylko admin
        System.out.println("3. Return book");
        System.out.println("4. Search book");
        System.out.println("5. Print borrowed books");
        System.out.println("6. Borrow book");
        System.out.println("7. List overdue books");
        System.out.println("8. Exit" + '\n');

        return scan.nextLine();
    }

    public static void listOfBooks(Map<String, Book> books) {

        System.out.println('\n' + "List of all books:");
        String booksInfo = books.values().stream()
                .map(book -> "Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", ISBN: " + book.getISBN() +
                        ", Borrowed: " + book.isBorrowed() +
                        ", Borrowed by: " + book.getBorrowedBy() +
                        ", Borrow Date: " + book.getBorrowDate() +
                        ", Return Date: " + book.getReturnDate())
                .collect(Collectors.joining("\n"));
        System.out.println(booksInfo + '\n');
    }


    public static void printBorrowedBooks(Map<String, Book> books) {
        System.out.println("\nBorrowed Books:");
        books.values().stream()
                .filter(Book::isBorrowed)
                .map(book -> "Title: " + book.getTitle() +
                        ", ISBN: " + book.getISBN() +
                        ", Borrower: " + book.getBorrowedBy() +
                        ", Borrow Date: " + book.getBorrowDate() +
                        ", Return Date: " + book.getReturnDate())
                .forEach(System.out::println);
    }

    public static void listOverdueBooks(Map<String, Book> books) {
        System.out.println("Overdue books:");
        books.values().stream()
                .filter(Book::isBorrowed)
                .filter(book -> isOverdue(book.getReturnDate()))
                .forEach(book -> {
                    System.out.println("Title: " + book.getTitle() +
                            ", Author: " + book.getAuthor() +
                            ", ISBN: " + book.getISBN() +
                            ", Borrowed by: " + book.getBorrowedBy() +
                            ", Return date: " + book.getReturnDate());
                });
    }

    private static boolean isOverdue(String returnDateStr) {
        try {
            Date returnDate = dateFormat.parse(returnDateStr);
            Date currentDate = new Date();
            return returnDate.before(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static User readAuthData() {
        System.out.println("Login:");
        String login = scan.nextLine();
        System.out.println("Password:");
        return new User(login, scan.nextLine());
    }

}
