package com.example.service.service;


import com.example.service.config.JwtProvider;
import com.example.service.exception.UserException;
import com.example.service.model.User;
import com.example.service.repository.UserRepository;
import com.example.service.service.implementation.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserImplementation implements UserService {

    private UserRepository userRepository;

    private JwtProvider jwtProvider;

    public UserImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserException("user not found with id : " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("user not found with email : " + email);
        }
        return user;
    }
}
