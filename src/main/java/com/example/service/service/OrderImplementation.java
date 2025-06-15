package com.example.service.service;

import com.example.service.exception.OrderException;
import com.example.service.model.*;
import com.example.service.repository.AddressRepository;
import com.example.service.repository.OrderItemRepository;
import com.example.service.repository.OrderRepository;
import com.example.service.repository.UserRepository;
import com.example.service.request.AddressRequest;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.OrderItemService;
import com.example.service.service.implementation.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderImplementation implements OrderService {

    private CartService cartService;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;


    public OrderImplementation(CartService cartService, OrderRepository orderRepository,
                               AddressRepository addressRepository, UserRepository userRepository,
                               OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    @Transactional
    public Order createOrder(User user, AddressRequest shippingAddress) {
        Cart cart = cartService.findUserCart(user.getId());

        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setTotalItem(cart.getTotalItem());

        for (CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setName(item.getName());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setImageUrl(item.getImageUrl());
            orderItem.setImageColor(item.getImageColor());
            orderItem.setNameColor(item.getNameColor());
            orderItem.setColor(item.getColor());
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            orderItem.setCategory(item.getCategory());
            orderItem.setProduct(item.getProduct());
            orderItem.setOrder(createdOrder);

            createdOrder.getOrderItem().add(orderItem);
        }

        if (shippingAddress.getSave()) {
            Address address = new Address();
            address.setZipCode(shippingAddress.getZipCode());
            address.setStreetAddress(shippingAddress.getStreetAddress());
            address.setState(shippingAddress.getState());
            address.setCity(shippingAddress.getCity());
            address.setMobile(shippingAddress.getMobile());
            address.setFirstName(shippingAddress.getFirstName());
            address.setLastName(shippingAddress.getLastName());
            address.setUser(user);
            user.getAddress().add(address);
            userRepository.save(user);
        }

        AddressOrder addressOrder = new AddressOrder();
        addressOrder.setCity(shippingAddress.getCity());
        addressOrder.setStreetAddress(shippingAddress.getStreetAddress());
        addressOrder.setState(shippingAddress.getState());
        addressOrder.setZipCode(shippingAddress.getZipCode());
        addressOrder.setFirstName(shippingAddress.getFirstName());
        addressOrder.setLastName(shippingAddress.getLastName());
        addressOrder.setMobile(shippingAddress.getMobile());
        addressOrder.setStatus("PENDING");
        createdOrder.setShippingAddress(addressOrder);

        createdOrder.setNote(shippingAddress.getNote());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");

        return orderRepository.save(createdOrder);
    }

    @Override
    public Order findOrderById(UUID orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findOrderByPublicId(orderId);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new OrderException("order not exist with id : " + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus("PLACED");
//        order.getPaymentDetails().setStatus("COMPLETED");
//        return order;
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus("CONFIRMED");
//
//        return orderRepository.save(order);
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus("SHIPPED");
//
//        return orderRepository.save(order);
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus("DELIVERED");
//
//        return orderRepository.save(order);
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus("CANCELED");
//
//        return orderRepository.save(order);
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order changeOrderStatus(Long orderId, String status) throws OrderException {
//        Order order = findOrderById(orderId);
//        order.setOrderStatus(status);
//
//        return orderRepository.save(order);
        return null;
    }
}
