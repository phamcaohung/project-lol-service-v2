package com.example.service.service.implementation;

import com.example.service.exception.ProductException;
import com.example.service.model.Review;
import com.example.service.model.User;
import com.example.service.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest req, User user) throws ProductException;

    List<Review> getAllReview(Long productId);
}
