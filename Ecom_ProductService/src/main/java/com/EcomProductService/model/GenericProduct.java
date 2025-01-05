package com.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
@Getter
@Setter
//@Document(indexName = "genericproducts")
public class GenericProduct extends BaseModel{
    private String title;
    private String description;
    private String image;
}
