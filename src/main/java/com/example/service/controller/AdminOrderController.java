package com.example.service.controller;

import com.example.service.controller.interfaces.IAdminOrderController;
import com.example.service.exception.OrderException;
import com.example.service.model.Order;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class AdminOrderController implements IAdminOrderController {

    @Autowired
    private OrderService orderService;


    @Override
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    @Override
    public Order confirmedOrder(Long orderId, String jwt) throws OrderException {
        return orderService.confirmedOrder(orderId);
    }

    @Override
    public Order shippedOrder(Long orderId, String jwt) throws OrderException {
        return orderService.shippedOrder(orderId);
    }

    @Override
    public Order deliverOrder(Long orderId, String jwt) throws OrderException {
        return orderService.deliveredOrder(orderId);
    }

    @Override
    public Order cancelOrder(Long orderId, String jwt) throws OrderException {
        return orderService.canceledOrder(orderId);
    }

    @Override
    public ApiResponse deleteOrder(Long orderId, String jwt) throws OrderException {
        orderService.deleteOrder(orderId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setMessage("delete order successfully");

        return apiResponse;
    }

    @Override
    public Order changeOrderStatus(Long orderId, String jwt, String status) throws OrderException {
        return orderService.changeOrderStatus(orderId, status);
    }
}
