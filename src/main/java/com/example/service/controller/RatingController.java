package com.example.service.controller;

import com.example.service.controller.interfaces.IRatingController;
import com.example.service.exception.ProductException;
import com.example.service.exception.UserException;
import com.example.service.model.Rating;
import com.example.service.model.User;
import com.example.service.request.RatingRequest;
import com.example.service.service.implementation.RatingService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RatingController implements IRatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;


    @Override
    public Rating createRating(RatingRequest req, String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        return ratingService.createRating(req, user);
    }

    @Override
    public List<Rating> getProductsRating(Long productId, String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        return ratingService.getProductsRating(productId);
    }
}
