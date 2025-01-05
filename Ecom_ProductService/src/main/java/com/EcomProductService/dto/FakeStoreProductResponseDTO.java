package com.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductResponseDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
