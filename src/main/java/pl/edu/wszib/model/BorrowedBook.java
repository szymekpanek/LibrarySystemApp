package pl.edu.wszib.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowedBook extends Book {
    private boolean isBorrowed;
    private String borrower;
    private String borrowDate;
    private String returnDate;


    public BorrowedBook(String bookName, String author, String ISBN) {
        super(bookName, author, ISBN);
    }
}
