package com.dev.PaymentService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
    private Long OrderId;
    private Long amount;
    private String phoneNumber;
}
