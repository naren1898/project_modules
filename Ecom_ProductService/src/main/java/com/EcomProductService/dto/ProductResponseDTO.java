package com.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
