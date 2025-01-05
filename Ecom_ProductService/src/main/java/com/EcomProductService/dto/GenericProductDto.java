package com.EcomProductService.dto;

import com.EcomProductService.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
    //private int inventoryCount;

    public static GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setPrice(product.getPrice().getAmount());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setId(product.getId());
        //genericProductDto.setInventoryCount(product.getInventoryCount());
        return genericProductDto;
    }
}
