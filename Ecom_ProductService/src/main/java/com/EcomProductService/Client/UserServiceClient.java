package com.EcomProductService.Client;

import com.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.EcomProductService.dto.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class UserServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String userServiceAPIURL;
    @Value("${userService.api.path.validate}")
    private String  userServiceValidatePath;
    public UserServiceClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${userService.api.url}") String userServiceAPIURL) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceAPIURL = userServiceAPIURL;
    }
    public String validateToken(ValidateTokenDTO validateTokenDTO){
        String userTokenVerifyURL = userServiceAPIURL + userServiceValidatePath;
        System.out.println(userTokenVerifyURL);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> validateResponse = restTemplate.postForEntity(userTokenVerifyURL,validateTokenDTO,String.class);
        return validateResponse.getBody();
    }
}
