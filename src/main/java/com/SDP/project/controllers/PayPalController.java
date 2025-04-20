package com.SDP.project.controllers;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class PayPalController {

    @Autowired
    private PayPalHttpClient payPalClient;

    @Value("${paypal.returnUrl}")
    private String returnUrl;

    @Value("${paypal.cancelUrl}")
    private String cancelUrl;

    @PostMapping("/createOrder")
    public String createOrder() {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.requestBody(buildRequestBody());

        try {
            Order order = payPalClient.execute(request).result();
            return order.id();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private OrderRequest buildRequestBody() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        orderRequest.applicationContext(new com.paypal.orders.ApplicationContext()
                .brandName("YourBrandName")
                .landingPage("BILLING")
                .cancelUrl(cancelUrl)
                .returnUrl(returnUrl)
                .userAction("PAY_NOW"));

        AmountWithBreakdown amount = new AmountWithBreakdown()
                .currencyCode("USD")
                .value("100.00");

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(amount);

        orderRequest.purchaseUnits(Collections.singletonList(purchaseUnitRequest));

        return orderRequest;
    }

    @PostMapping("/captureOrder/{orderId}")
    public String captureOrder(@PathVariable String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);

        try {
            Order order = payPalClient.execute(request).result();
            return "Payment successfully captured for order ID: " + order.id();
        } catch (Exception e) {
            return "Error capturing payment: " + e.getMessage();
        }
    }
}