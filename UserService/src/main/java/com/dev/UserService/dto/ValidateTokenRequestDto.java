package com.dev.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateTokenRequestDto {
    private String token;
    private Long Id;
}
