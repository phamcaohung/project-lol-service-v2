package com.example.service.controller;


import com.example.service.controller.interfaces.ICartController;
import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.User;
import com.example.service.request.AddItemRequest;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.CartService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartController implements ICartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Override
    public Cart findUserCart(String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        return cartService.findUserCart(user.getId());
    }

    @Override
    public ApiResponse addItemToCart(AddItemRequest req, String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("add item to cart successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }
}
