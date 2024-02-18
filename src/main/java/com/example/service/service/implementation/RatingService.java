package com.example.service.service.implementation;

import com.example.service.exception.ProductException;
import com.example.service.model.Rating;
import com.example.service.model.User;
import com.example.service.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequest req, User user) throws ProductException;

    List<Rating> getProductsRating(Long productId);
}
