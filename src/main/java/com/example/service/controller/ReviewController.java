package com.example.service.controller;

import com.example.service.controller.interfaces.IReviewController;
import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Review;
import com.example.service.model.User;
import com.example.service.request.ReviewRequest;
import com.example.service.service.implementation.ReviewService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class ReviewController implements IReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;


    @Override
    public Review createReview(ReviewRequest req, String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        return reviewService.createReview(req, user);
    }

    @Override
    public List<Review> getProductReview(Long productId) throws UserException, ProductException {
        return reviewService.getAllReview(productId);
    }
}
