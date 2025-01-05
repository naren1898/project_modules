package com.EcomProductService.service;

import com.EcomProductService.Client.FakeStoreAPIClient;
import com.EcomProductService.dto.*;
import com.EcomProductService.Utils.ProductNotFoundException;
import com.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.EcomProductService.Mapper.ProductMapper.fakeStoreProductResponseDTOtoproductResponseDTO;
import static com.EcomProductService.Mapper.ProductMapper.productRequestDTOtofakeStoreProductRequestDTO;
import static com.EcomProductService.Utils.ProductUtils.isNull;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreAPIClient fakeStoreAPIClient;
    private RedisTemplate<String,FakeStoreProductResponseDTO> redisTemplate;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreAPIClient fakeStoreAPIClient,
                                       RedisTemplate redisTemplate){
        this.restTemplateBuilder= restTemplateBuilder;
        this.fakeStoreAPIClient=fakeStoreAPIClient;
        this.redisTemplate=redisTemplate;
    }
    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO reponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO productResponse : fakeStoreProductResponseDTOS){
            reponseDTO.getProducts().add(fakeStoreProductResponseDTOtoproductResponseDTO(productResponse));
        }
        return reponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        //FakeStoreProductResponseDTO fakeStoreProductResponseDTO =null;
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS",id);
        if(fakeStoreProductResponseDTO != null){
            return fakeStoreProductResponseDTOtoproductResponseDTO(fakeStoreProductResponseDTO);
        }
        fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO))
            throw new ProductNotFoundException("Product Not found:" + id);
        redisTemplate.opsForHash().put("PRODUCTS",id,fakeStoreProductResponseDTO);
        return fakeStoreProductResponseDTOtoproductResponseDTO(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestDTOtofakeStoreProductRequestDTO(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO =fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
            return fakeStoreProductResponseDTOtoproductResponseDTO(fakeStoreProductResponseDTO);
        }

    @Override
        public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        return null;
    }
}
