package com.example.service.service;

import com.example.service.exception.ProductException;
import com.example.service.model.*;
import com.example.service.repository.CartItemRepository;
import com.example.service.repository.CartRepository;
import com.example.service.request.AddItemRequest;
import com.example.service.service.implementation.CartItemService;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CartImplementation implements CartService {

    private ProductService productService;

    private CartRepository cartRepository;

    private CartItemService cartItemService;

    private CartItemRepository cartItemRepository;

    public CartImplementation(CartRepository cartRepository,
                              ProductService productService,
                              CartItemService cartItemService,
                              CartItemRepository cartItemRepository) {
        this.cartItemService = cartItemService;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getColor(), userId);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            cartItem.setPrice(req.getQuantity() * product.getDiscountedPrice());
            cartItem.setColor(req.getColor());
            cartItem.setImageColor(req.getImageColor());
            cartItem.setCreateAt(LocalDateTime.now());

            CartItem createCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createCartItem);
        }
        else {
            isPresent.setQuantity(isPresent.getQuantity() + 1);
            isPresent.setPrice(isPresent.getPrice() + isPresent.getProduct().getPrice());
            cartItemRepository.save(isPresent);
        }

        return "Item Add To Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem :cart.getCartItems()) {
            totalPrice = totalPrice + cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }
}
