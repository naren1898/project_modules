package com.dev.PaymentService.Service;

import com.dev.PaymentService.PaymentGateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(Long orderId, Long amount, String phoneNumber) throws RazorpayException, StripeException {
        return paymentGateway.generatepaymentLink(orderId,amount,phoneNumber);
    }
}
