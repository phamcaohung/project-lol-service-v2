package com.example.service.controller.interfaces;


import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Rating;
import com.example.service.request.RatingRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public interface IRatingController {
    @PostMapping("/create")
    Rating createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException;

    @GetMapping("/product/{productId}")
    List<Rating> getProductsRating(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException, ProductException;
}
