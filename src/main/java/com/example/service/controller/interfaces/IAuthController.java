package com.example.service.controller.interfaces;

import com.example.service.exception.UserException;
import com.example.service.model.User;
import com.example.service.request.LoginRequest;
import com.example.service.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public interface IAuthController {
    @PostMapping("/signup")
    AuthResponse createUserHandler(@RequestBody User user) throws UserException;

    @PostMapping("/signin")
    ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest);

    Authentication authenticate(String username, String password);
}
