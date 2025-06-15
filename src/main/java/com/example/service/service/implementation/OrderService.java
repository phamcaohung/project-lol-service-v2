package com.example.service.service.implementation;

import com.example.service.exception.OrderException;
import com.example.service.model.Address;
import com.example.service.model.Order;
import com.example.service.model.User;
import com.example.service.request.AddressRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(User user, AddressRequest shippingAddress);

    Order findOrderById(UUID OrderId) throws OrderException;

    List<Order> usersOrderHistory(Long userId);

    Order placeOrder(Long orderId) throws OrderException;

    Order confirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canceledOrder(Long orderId) throws OrderException;

    List<Order> getAllOrders();

    void deleteOrder(Long orderId) throws OrderException;

    Order changeOrderStatus(Long orderId, String status) throws OrderException;
}
