package com.example.service.service;


import com.example.service.model.OrderItem;
import com.example.service.repository.OrderItemRepository;
import com.example.service.service.implementation.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItemImplementation(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
