package com.example.service.controller.interfaces;


import com.example.service.dto.OrderDTO;
import com.example.service.exception.OrderException;
import com.example.service.exception.UserException;
import com.example.service.model.Address;
import com.example.service.model.Order;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public interface IOrderController {

    @PostMapping("/")
    Order createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException;

    @GetMapping("/user")
    List<OrderDTO> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException;

    @GetMapping("/{id}")
    OrderDTO findOrderById(@PathVariable("id") Long orderId, @RequestHeader("Authorization") String jwt) throws UserException, OrderException;

    @DeleteMapping("/address/{addressId}")
    ApiResponse deleteAddress(@PathVariable Long addressId) throws UserException;

    @GetMapping("/address")
    List<Address> getAddressByUser(@RequestHeader("Authorization") String jwt) throws UserException;
}
