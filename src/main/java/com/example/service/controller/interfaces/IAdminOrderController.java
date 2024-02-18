package com.example.service.controller.interfaces;


import com.example.service.exception.OrderException;
import com.example.service.model.Order;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin/orders")
@RestController
public interface IAdminOrderController {

    @GetMapping("/")
    List<Order> getAllOrder();

    @PutMapping("/{orderId}/confirmed")
    Order confirmedOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @PutMapping("/{orderId}/ship")
    Order shippedOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @PutMapping("/{orderId}/deliver")
    Order deliverOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @PutMapping("/{orderId}/cancel")
    Order cancelOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @DeleteMapping("/{orderId}/delete")
    ApiResponse deleteOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @PutMapping("/{orderId}/status")
    Order changeOrderStatus(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt, @RequestParam String status) throws OrderException;
}
