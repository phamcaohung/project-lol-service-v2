package com.example.service.controller;

import com.example.service.controller.interfaces.IUserController;
import com.example.service.exception.UserException;
import com.example.service.model.User;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class UserController implements IUserController {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<User> getUserProfile(String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
}
