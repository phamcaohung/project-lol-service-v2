package com.example.service.dto;


import com.example.service.model.User;
import java.util.HashSet;
import java.util.Set;

public class CartDTO {
    private Long id;

    private int discounted;

    private User user;

    private Set<CartItemDTO> cartItems = new HashSet<>();

    private double totalPrice;

    private int totalItem;

    private int totalDiscountedPrice;

    public CartDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscounted() {
        return discounted;
    }

    public void setDiscounted(int discounted) {
        this.discounted = discounted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    public void setTotalDiscountedPrice(int totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }
}
