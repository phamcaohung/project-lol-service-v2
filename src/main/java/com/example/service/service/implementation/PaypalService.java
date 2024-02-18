package com.example.service.service.implementation;

import com.example.service.model.Cart;
import com.example.service.model.Order;
import com.example.service.model.User;
import com.example.service.response.ApiResponse;
import com.example.service.response.PaymentLinkResponse;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    PaymentLinkResponse createPayment(Order order) throws PayPalRESTException;

    ApiResponse checkPaymentApprove(Order order, Payment payment, String paymentId, String payerId, String token, Cart cart) throws PayPalRESTException;
}
