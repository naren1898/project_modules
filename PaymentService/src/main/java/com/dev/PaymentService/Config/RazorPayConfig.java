package com.dev.PaymentService.Config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.key.id}")
    private String razorPayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorPaySecret;

    @Bean
    public RazorpayClient createRazorPayClient() throws RazorpayException {
        return new RazorpayClient(razorPayKeyId,razorPaySecret);
    }

}
