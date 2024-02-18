package com.example.service.service.implementation;

import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.CartItem;
import com.example.service.model.Product;

public interface CartItemService{
    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    CartItem isCartItemExist(Cart cart, Product product, String color, Long userId);

    String removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
