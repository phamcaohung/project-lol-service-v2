package com.example.service.service;

import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.model.Rating;
import com.example.service.model.User;
import com.example.service.repository.RatingRepository;
import com.example.service.request.RatingRequest;
import com.example.service.service.implementation.ProductService;
import com.example.service.service.implementation.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingImplementation implements RatingService {

    private RatingRepository ratingRepository;

    private ProductService productService;

    public RatingImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
