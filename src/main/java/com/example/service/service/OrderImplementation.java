package com.example.service.service;

import com.example.service.exception.OrderException;
import com.example.service.model.*;
import com.example.service.repository.AddressRepository;
import com.example.service.repository.OrderItemRepository;
import com.example.service.repository.OrderRepository;
import com.example.service.repository.UserRepository;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.OrderItemService;
import com.example.service.service.implementation.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Order createOrder(User user, Address shippingAddress) {
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setColor(item.getColor());
            orderItem.setImageColor(item.getImageColor());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createOrderItem);
        }

        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItem(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscoute(cart.getDiscounted());
        createdOrder.setTotalItem(cart.getTotalItem());

        if (shippingAddress.getId() == null) {
            shippingAddress.setStatus("ACTIVE");
            shippingAddress.setUser(user);
            Address address = addressRepository.save(shippingAddress);
            user.getAddress().add(address);
            userRepository.save(user);
            createdOrder.setShippingAddress(address);
        }
        createdOrder.setShippingAddress(shippingAddress);

        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreateAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(createdOrder);

        for (OrderItem item:orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById(orderId);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new OrderException("order not exist with id : " + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderRepository.getUsersOrders(userId);
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");

        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");

        return orderRepository.save(order);
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELED");

        return orderRepository.save(order);
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
        Order order = findOrderById(orderId);
        order.setOrderStatus(status);

        return orderRepository.save(order);
    }
}
