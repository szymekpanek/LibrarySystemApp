package pl.edu.wszib.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book {
    private String Title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;
    private String borrowedBy;
    private String borrowDate;
    private String returnDate;
}


