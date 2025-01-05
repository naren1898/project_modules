package com.EcomProductService.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String bookName;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;


    public Book(String bookName, Author author) {
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {
    }
}
