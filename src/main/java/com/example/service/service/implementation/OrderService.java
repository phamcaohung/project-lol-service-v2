package com.example.service.service.implementation;

import com.example.service.exception.OrderException;
import com.example.service.model.Address;
import com.example.service.model.Order;
import com.example.service.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);

    Order findOrderById(Long OrderId) throws OrderException;

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
