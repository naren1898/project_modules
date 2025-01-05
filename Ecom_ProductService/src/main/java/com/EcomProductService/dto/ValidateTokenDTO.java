package com.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDTO {
    private int Id;
    private String token;

    public ValidateTokenDTO(int userId, String token) {
        this.Id = userId;
        this.token = token;
    }
}
