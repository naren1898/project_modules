package com.EcomProductService.service;


import com.EcomProductService.dto.ProductListResponseDTO;
import com.EcomProductService.dto.ProductResponseDTO;
import com.EcomProductService.Utils.ProductNotFoundException;
import com.EcomProductService.dto.ProductRequestDTO;
import com.EcomProductService.model.Product;


public interface ProductService {
    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id) throws ProductNotFoundException;
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    boolean deleteProduct(int id);

    Product updateProduct(int id, Product updatedProduct);
    ProductResponseDTO findProductByTitle (String title);



}
