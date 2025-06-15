package com.example.service.service;

import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.CartItem;
import com.example.service.model.Product;
import com.example.service.model.User;
import com.example.service.repository.CartItemRepository;
import com.example.service.repository.CartRepository;
import com.example.service.service.implementation.CartItemService;
import com.example.service.service.implementation.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartItemImplementation implements CartItemService {

    private CartItemRepository cartItemRepository;

    private UserService userService;

    private CartRepository cartRepository;

    public CartItemImplementation(CartItemRepository cartItemRepository,
                                  UserService userService,
                                  CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public CartItem updateCartItem(Long id, int quantity) throws CartItemException {
        CartItem item = findCartItemById(id);
        item.setQuantity(quantity);
        item.setPrice(item.getQuantity() * item.getProduct().getPrice());
        item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String nameColor) {
        return cartItemRepository.isCartItemExist(cart, product, nameColor);
    }

    @Override
    public String removeCartItem(Long cartItemId) throws CartItemException {
        CartItem cartItem = findCartItemById(cartItemId);
        cartItemRepository.deleteById(cartItemId);
        return "Deleted Cart Item Successfully";
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id : " + cartItemId);
    }
}
