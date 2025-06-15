package com.example.service.controller;

import com.example.service.controller.interfaces.ICartItemController;
import com.example.service.exception.CartItemException;
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
    public ApiResponse deleteCartItem(Long cartItemId) throws CartItemException {
        cartItemService.removeCartItem(cartItemId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Delete Cart Item Successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }

    @Override
    public ApiResponse updateCartItem(int quantity, Long cartItemId) throws CartItemException {
        cartItemService.updateCartItem(cartItemId, quantity);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Update Quantity Successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }
}
