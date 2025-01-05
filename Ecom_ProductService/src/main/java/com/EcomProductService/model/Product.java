package com.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@Getter
@Setter
@Entity(name = "PRODUCT")
@Document(indexName = "products")
public class Product extends BaseModel{
        private String title;
        private String description;
        private String image;
        @ManyToOne
        private Category category;
        @OneToOne
        private Price price;
        @ManyToMany(mappedBy="products")
        private List<Order> orders;
    }

