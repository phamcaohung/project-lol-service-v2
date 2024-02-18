package com.example.service.controller.interfaces;


import com.example.service.dto.CartItemDTO;
import com.example.service.exception.CartItemException;
import com.example.service.exception.UserException;
import com.example.service.model.CartItem;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
public interface ICartItemController {
    @DeleteMapping("/{cartItemId}")
    ApiResponse deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException;

    @PutMapping("/{cartItemId}")
    CartItemDTO updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
                               @RequestHeader("Authorization") String jwt) throws UserException, CartItemException;
}


