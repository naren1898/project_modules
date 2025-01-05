package com.EcomProductService.controller;

import com.EcomProductService.Client.UserServiceClient;
import com.EcomProductService.Exception.InvalidTokenException;
import com.EcomProductService.Utils.ProductNotFoundException;
import com.EcomProductService.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class ProductController {
    private final ProductService productService;
    private final UserServiceClient userServiceClient;

    @Autowired // Autowired for constructor injection is optional from Spring 4.x+ onwards
    public ProductController(@Qualifier("ProductService_DB") ProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    @CrossOrigin(origins="http://localhost:8080")
    @GetMapping("/products")
    public ResponseEntity getAllProducts() throws Exception {
        //validateUser(token); //@RequestHeader("token") String token
        ProductListResponseDTO response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductFromId(@PathVariable int id) throws ProductNotFoundException {
        /*
        ProductResponseDTO p3 = new ProductResponseDTO();
        p3.setId(1);
        p3.setTitle("Iphone 15 pro");
        p3.setCategory("Electronics");
        p3.setImage("www.google.com/images/iphone");
        p3.setDescription("Phone");
        p3.setPrice(150000);

        ProductResponseDTO p2 = new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook");
        p2.setCategory("Electronics");
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Laptop");
        p2.setPrice(250000);

        List<ProductResponseDTO> p = Arrays.asList(p3,p2);
        return ResponseEntity.ok(p);

         */
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/title/{title}")
    public ResponseEntity getProductFromTitle(@PathVariable String title) throws ProductNotFoundException {
        /*
        ProductResponseDTO p3 = new ProductResponseDTO();
        p3.setId(1);
        p3.setTitle("Iphone 15 pro");
        p3.setCategory("Electronics");
        p3.setImage("www.google.com/images/iphone");
        p3.setDescription("Phone");
        p3.setPrice(150000);

        ProductResponseDTO p2 = new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook");
        p2.setCategory("Electronics");
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Laptop");
        p2.setPrice(250000);

        List<ProductResponseDTO> p = Arrays.asList(p3,p2);
        return ResponseEntity.ok(p);

         */
        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean responseDTO = productService.deleteProduct(id);
        return ResponseEntity.ok(responseDTO);
    }

    private void validateUser(String token) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        System.out.println(header);
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        JwtPayloadDTO jwtPayload = mapper.readValue(payload, JwtPayloadDTO.class);
        int userId = jwtPayload.getUserId();
        ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(userId, token);
        String result = userServiceClient.validateToken(validateTokenDTO);
        if (!result.contains(SessionStatus.ACTIVE.name())) {
            throw new InvalidTokenException("Token is not valid");
        }

    }
}
