package com.dev.PaymentService.PaymentGateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
@Primary
@Service
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatepaymentLink(Long orderid, Long Amount, String phoneNumber) throws StripeException {
        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        PriceCreateParams Pricecreateparams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(Pricecreateparams);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) // Callback
                                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://www.scaler.com/academy/mentee-dashboard/todos" +
                                                        "")
                                                .build()
                                        )
                                        .build()

                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.toString();
    }
}
