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
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String color, Long userId) {
        return cartItemRepository.isCartItemExist(cart, product, color, userId);
    }

    @Override
    public String removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
            return "Deleted Cart Item Successfully";
        }
        else {
            throw new UserException("You can't remove another users item");
        }
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
