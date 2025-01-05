package com.EcomProductService.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String categoryName;
    //@OneToMany
    //@JoinColumn(name="category_id")
    //private List<Product> products;
}
