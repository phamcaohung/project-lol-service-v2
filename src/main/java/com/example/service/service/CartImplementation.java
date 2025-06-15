package com.example.service.service;

import com.example.service.exception.ProductException;
import com.example.service.model.*;
import com.example.service.repository.CartItemRepository;
import com.example.service.repository.CartRepository;
import com.example.service.repository.CategoryRepository;
import com.example.service.request.AddItemRequest;
import com.example.service.service.implementation.CartItemService;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class CartImplementation implements CartService {

    private ProductService productService;

    private CartRepository cartRepository;

    private CartItemService cartItemService;

    private CartItemRepository cartItemRepository;

    private CategoryRepository categoryRepository;

    public CartImplementation(CartRepository cartRepository,
                              ProductService productService,
                              CartItemService cartItemService,
                              CartItemRepository cartItemRepository,
                              CategoryRepository categoryRepository) {
        this.cartItemService = cartItemService;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Cart createCart(User user) {
//        Cart cart = new Cart();
//        cart.setUser(user);
//        return cartRepository.save(cart);
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findCartByUserId(userId);
        Product product = productService.findProductById(req.getProductId());
        Category category = categoryRepository.findByName(req.getCategory());


        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getNameColor());

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setName(req.getName());
            cartItem.setQuantity(req.getQuantity());
            cartItem.setImageUrl(req.getImageUrl());
            cartItem.setNameColor(req.getNameColor());
            cartItem.setImageColor(req.getImageColor());
            cartItem.setColor(req.getColor());
            cartItem.setPrice(req.getPrice());
            cartItem.setDiscountedPrice(req.getDiscountedPrice());
            cartItem.setDiscountPercent(req.getDiscountPercent());
            cartItem.setCategory(category);
            cartItem.setCreateAt(LocalDateTime.now());
            cartItem.setUpdateAt(LocalDateTime.now());
            cart.getCartItems().add(cartItem);
        }
        else {
            isPresent.setQuantity(isPresent.getQuantity() + 1);
            isPresent.setPrice(isPresent.getPrice() * 2);
        }
        cartRepository.save(cart);
        return "Item Add To Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);

        cart.getCartItems().sort(Comparator.comparing(CartItem::getCreateAt).reversed());
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
