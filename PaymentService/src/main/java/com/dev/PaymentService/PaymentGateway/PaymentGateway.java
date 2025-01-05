package com.dev.PaymentService.PaymentGateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatepaymentLink(Long orderid, Long Amount, String phoneNumber) throws RazorpayException, StripeException;
}
