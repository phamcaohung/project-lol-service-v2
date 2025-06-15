package com.example.service.controller;

import com.example.service.controller.interfaces.IPaymentController;
import com.example.service.exception.OrderException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.Order;
import com.example.service.model.User;
import com.example.service.response.ApiResponse;
import com.example.service.response.PaymentLinkResponse;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.OrderService;
import com.example.service.service.implementation.PaypalService;
import com.example.service.service.implementation.UserService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;


@Controller
public class PaymentController implements IPaymentController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Override
    public PaymentLinkResponse createPaymentLink(UUID orderId, String jwt) throws OrderException, PayPalRESTException {
        Order order = orderService.findOrderById(orderId);

        try {
            return paypalService.createPayment(order);
        } catch (Exception e) {
            throw new PayPalRESTException(e.getMessage());
        }
    }

    @Override
    public ApiResponse redirect(String paymentId, String token, String payerId, UUID orderId, String jwt) throws OrderException, PayPalRESTException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        Order order = orderService.findOrderById(orderId);

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);

            return paypalService.checkPaymentApprove(order, payment, paymentId, payerId, token, cart);
        } catch (Exception e) {
            throw new PayPalRESTException(e.getMessage());
        }
    }
}
