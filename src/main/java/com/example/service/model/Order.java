package com.example.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue
    private UUID publicId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem = new ArrayList<>();

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_order_id")
    private AddressOrder shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetails paymentDetails = new PaymentDetails();

    private double totalPrice;

    private Integer totalDiscountedPrice;

    private String orderStatus;

    private int totalItem;

    private String note;


    public Order() {

    }

    public Order(Long id,UUID publicId ,User user, List<OrderItem> orderItem, LocalDateTime orderDate, LocalDateTime deliveryDate, AddressOrder shippingAddress, PaymentDetails paymentDetails, double totalPrice, Integer totalDiscountedPrice, String orderStatus, int totalItem, String note) {
        this.id = id;
        this.publicId = publicId;
        this.user = user;
        this.orderItem = orderItem;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.paymentDetails = paymentDetails;
        this.totalPrice = totalPrice;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.orderStatus = orderStatus;
        this.totalItem = totalItem;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public AddressOrder getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressOrder shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    public void setTotalDiscountedPrice(Integer totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @PrePersist
    public void generatePublicId() {
        if (publicId == null)
            publicId = UUID.randomUUID();
    }
}
