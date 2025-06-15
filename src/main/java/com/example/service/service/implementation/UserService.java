package com.example.service.service.implementation;

import com.example.service.exception.UserException;
import com.example.service.model.User;

public interface UserService {
    User findUserById(Long userId) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;


}
