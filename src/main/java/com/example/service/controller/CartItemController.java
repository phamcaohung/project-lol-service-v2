package com.example.service.controller;

import com.example.service.controller.interfaces.ICartItemController;
import com.example.service.dto.CartItemDTO;
import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.mapper.CartItemMapper;
import com.example.service.model.CartItem;
import com.example.service.model.User;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.CartItemService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartItemController implements ICartItemController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public ApiResponse deleteCartItem(Long cartItemId, String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("delete cart item successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }

    @Override
    public CartItemDTO updateCartItem(CartItem cartItem, Long cartItemId, String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem cart_item = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return CartItemMapper.INSTANCE.map(cart_item);
    }
}
