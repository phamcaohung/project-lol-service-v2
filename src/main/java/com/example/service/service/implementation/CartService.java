package com.example.service.service.implementation;

import com.example.service.exception.ProductException;
import com.example.service.model.Cart;
import com.example.service.model.User;
import com.example.service.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
