package com.example.service.service;

import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.model.Review;
import com.example.service.model.User;
import com.example.service.repository.ProductRepository;
import com.example.service.repository.ReviewRepository;
import com.example.service.request.ReviewRequest;
import com.example.service.service.implementation.ProductService;
import com.example.service.service.implementation.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewImplementation implements ReviewService {

    private ReviewRepository reviewRepository;

    private ProductRepository productRepository;

    private ProductService productService;

    public ReviewImplementation(ReviewRepository reviewRepository,
                                ProductRepository productRepository,
                                ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
