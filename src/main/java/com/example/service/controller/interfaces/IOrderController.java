package com.example.service.controller.interfaces;


import com.example.service.dto.OrderDTO;
import com.example.service.dto.OrderHistoryDTO;
import com.example.service.exception.OrderException;
import com.example.service.exception.UserException;
import com.example.service.model.Address;
import com.example.service.model.Order;
import com.example.service.request.AddressRequest;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/orders")
public interface IOrderController {

    @PostMapping("/")
    OrderDTO createOrder(@RequestBody AddressRequest shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException;

    @GetMapping("/history")
    List<OrderHistoryDTO> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException;

    @GetMapping("/{id}")
    OrderDTO findOrderById(@PathVariable("id") UUID orderId, @RequestHeader("Authorization") String jwt) throws OrderException;

    @GetMapping("/address/{addressId}")
    Address editAddress(@PathVariable Long addressId);

    @DeleteMapping("/address/{addressId}")
    ApiResponse deleteAddress(@PathVariable Long addressId);

    @GetMapping("/address")
    List<Address> getAddressByUser(@RequestHeader("Authorization") String jwt) throws UserException;
}
