package com.dev.PaymentService.Controller;

import com.dev.PaymentService.Service.PaymentService;
import com.dev.PaymentService.dto.InitiatePaymentRequestDto;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException, StripeException {
        return paymentService.initiatePayment(requestDto.getOrderId(),requestDto.getAmount(),requestDto.getPhoneNumber());
    }
}
