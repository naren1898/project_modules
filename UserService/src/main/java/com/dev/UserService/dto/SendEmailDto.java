package com.dev.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto {
    private String from;
    private String to;
    private String subject;
    private String Body;
}
