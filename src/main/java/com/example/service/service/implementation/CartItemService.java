package com.example.service.service.implementation;

import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.CartItem;
import com.example.service.model.Product;

public interface CartItemService{

    CartItem updateCartItem(Long id, int quantity) throws CartItemException;

    CartItem isCartItemExist(Cart cart, Product product, String nameColor);

    String removeCartItem(Long cartItemId) throws CartItemException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
