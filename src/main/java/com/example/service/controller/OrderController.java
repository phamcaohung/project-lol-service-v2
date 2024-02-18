package com.example.service.controller;

import com.example.service.controller.interfaces.IOrderController;
import com.example.service.dto.OrderDTO;
import com.example.service.exception.OrderException;
import com.example.service.exception.UserException;
import com.example.service.mapper.OrderMapper;
import com.example.service.model.Address;
import com.example.service.model.Order;
import com.example.service.model.User;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.AddressService;
import com.example.service.service.implementation.OrderService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController implements IOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Override
    public Order createOrder(Address shippingAddress, String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);

        return orderService.createOrder(user, shippingAddress);
    }

    @Override
    public List<OrderDTO> usersOrderHistory(String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());
        return OrderMapper.INSTANCE.mapList(orders);
    }

    @Override
    public OrderDTO findOrderById(Long orderId, String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        return OrderMapper.INSTANCE.map(order);
    }

    @Override
    public ApiResponse deleteAddress(Long addressId) throws UserException {
        addressService.deleteAddress(addressId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("delete address successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }

    @Override
    public List<Address> getAddressByUser(String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        return addressService.getAddressByUser(user);
    }
}
