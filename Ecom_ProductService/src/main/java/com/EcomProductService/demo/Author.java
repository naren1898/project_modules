package com.EcomProductService.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, fetch= FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Book> books;
    public Author(){

    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
