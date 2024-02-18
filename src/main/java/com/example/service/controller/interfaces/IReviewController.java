package com.example.service.controller.interfaces;


import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Review;
import com.example.service.request.ReviewRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public interface IReviewController {
    @PostMapping("/create")
    Review createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException;

    @GetMapping("/product/{productId}")
    List<Review> getProductReview(@PathVariable Long productId) throws UserException, ProductException;
}
