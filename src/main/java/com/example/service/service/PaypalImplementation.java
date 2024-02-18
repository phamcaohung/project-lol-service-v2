package com.example.service.service;

import com.example.service.model.Cart;
import com.example.service.model.Order;
import com.example.service.model.User;
import com.example.service.repository.CartItemRepository;
import com.example.service.repository.CartRepository;
import com.example.service.repository.OrderRepository;
import com.example.service.response.ApiResponse;
import com.example.service.response.PaymentLinkResponse;
import com.example.service.service.implementation.PaypalService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaypalImplementation implements PaypalService {
    @Value("${paypal.client.app}")
    String apiKey;

    @Value("${paypal.client.secret}")
    String apiSecret;

    @Value("${paypal.mode}")
    String mode;

    private OrderRepository orderRepository;

    private CartItemRepository cartItemRepository;

    private CartRepository cartRepository;

    public PaypalImplementation(OrderRepository orderRepository, CartItemRepository cartItemRepository,
                                CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(apiKey, apiSecret, mode);

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecute);
    }

    @Override
    public PaymentLinkResponse createPayment(Order order) throws PayPalRESTException {
        APIContext apiContext = new APIContext(apiKey, apiSecret, mode);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(order.getTotalPrice()/10));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Payment description");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://localhost:3000/payment/" + order.getId());
        redirectUrls.setCancelUrl("http://localhost:3000/payment/" + order.getId() + "/cancel");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(List.of(transaction));
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);


        List<Links> links = createdPayment.getLinks();
        String paymentLink = null;

        for (Links link : links) {
            if (link.getRel().equals("approval_url")) {
                paymentLink = link.getHref();
                break;
            }
        }

        PaymentLinkResponse res = new PaymentLinkResponse();
        res.setPaymentLinkId(createdPayment.getId());
        res.setPaymentLinkUrl(paymentLink);
        res.setPayer(createdPayment.getPayer());
        res.setPaymentMethod("paypal");

        return res;
    }

    @Override
    public ApiResponse checkPaymentApprove(Order order, Payment payment, String paymentId,
                                           String payerId, String token, Cart cart) throws PayPalRESTException {
        if (payment.getState().equals("approved")) {
            order.getPaymentDetails().setPaymentId(paymentId);
            order.getPaymentDetails().setStatus("COMPLETED");
            order.getPaymentDetails().setToken(token);
            order.getPaymentDetails().setPayerId(payerId);
            order.setOrderStatus("PLACED");
            orderRepository.save(order);

            cart.clearItemsCard();
            cartRepository.save(cart);
        }

        ApiResponse res = new ApiResponse();
        res.setMessage("Your order get placed");
        res.setStatus(true);

        return res;
    }
}
