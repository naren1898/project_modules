package com.EcomProductService.Client;

import com.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.EcomProductService.dto.FakeStoreProductRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreAPIClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String fakeStoreAPIURL;
    @Value("${fakestore.api.path.product}")
    private String fakeStoreAPIPathProduct;

    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${fakestore.api.url}") String fakeStoreAPIURL) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
    }

    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreRequestDTO){
        String getAllProductsURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductResponseDTO = restTemplate.postForEntity(getAllProductsURL,fakeStoreRequestDTO,FakeStoreProductResponseDTO.class);
        return fakeStoreProductResponseDTO.getBody();
    }
    public FakeStoreProductResponseDTO getProductById(int id) {
        String getAllProductsURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(getAllProductsURL,FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        String getAllProductsURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductsURL, FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody());
    }
    public void deleteProduct(int id){
            String deleteAllProductsURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
            RestTemplate restTemplate = restTemplateBuilder.build();
            restTemplate.delete(deleteAllProductsURL);
        }


}
