package com.EcomProductService.Mapper;

import com.EcomProductService.dto.*;
import com.EcomProductService.model.Product;

import java.util.List;
import java.util.UUID;

public class ProductMapper {
    public static FakeStoreProductRequestDTO productRequestDTOtofakeStoreProductRequestDTO(ProductRequestDTO productRequestDTO){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeStoreProductResponseDTOtoproductResponseDTO(FakeStoreProductResponseDTO fakeStoreProductResponseDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        //productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setId(UUID.randomUUID());
        return productResponseDTO;
    }
    public static ProductListResponseDTO productsDTOtoproductListResponseDTO(List<Product> products){
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(Product p : products){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            //productResponseDTO.setCategory(p.getCategory());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setId(p.getId());
            productResponseDTO.setImage(p.getImage());
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }
    public static ProductResponseDTO productsDTOtoproductResponseDTO(Product p){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            //productResponseDTO.setCategory(p.getCategory());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setId(p.getId());
            productResponseDTO.setImage(p.getImage());
        return productResponseDTO;
    }


}
