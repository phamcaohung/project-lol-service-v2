package com.example.service.controller.interfaces;

import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.request.AddItemRequest;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public interface ICartController {
    @GetMapping("/")
    Cart findUserCart(@RequestHeader("Authorization") String jwt) throws UserException;

    @PutMapping("/add")
    ApiResponse addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException;


}
