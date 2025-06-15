package com.example.service.controller.interfaces;

import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
public interface ICartItemController {
    @DeleteMapping("/{cartItemId}")
    ApiResponse deleteCartItem(@PathVariable Long cartItemId) throws CartItemException;

    @PutMapping("/{cartItemId}")
    ApiResponse updateCartItem(@RequestBody int quantity, @PathVariable Long cartItemId) throws CartItemException;
}


