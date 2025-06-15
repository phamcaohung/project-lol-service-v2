package com.example.service.controller.interfaces;


import com.example.service.exception.OrderException;
import com.example.service.exception.UserException;
import com.example.service.response.ApiResponse;
import com.example.service.response.PaymentLinkResponse;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
public interface IPaymentController {

    @PostMapping("/{orderId}")
    PaymentLinkResponse createPaymentLink(@PathVariable UUID orderId, @RequestHeader("Authorization") String jwt) throws OrderException, PayPalRESTException;

    @GetMapping("")
    ApiResponse redirect(@RequestParam(name = "payment_id") String paymentId,
                         @RequestParam(name = "token") String token,
                         @RequestParam(name = "payer_id") String payerId,
                         @RequestParam(name = "order_id") UUID orderId,
                         @RequestHeader("Authorization") String jwt) throws OrderException, PayPalRESTException, UserException;
}
