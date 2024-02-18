package com.example.service.controller;

import com.example.service.config.JwtProvider;
import com.example.service.controller.interfaces.IAuthController;
import com.example.service.exception.UserException;
import com.example.service.model.Cart;
import com.example.service.model.User;
import com.example.service.repository.UserRepository;
import com.example.service.request.LoginRequest;
import com.example.service.response.AuthResponse;
import com.example.service.service.CustomerUserImplementation;
import com.example.service.service.implementation.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.security.Security;


@Controller
public class AuthController implements IAuthController {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;
    private CustomerUserImplementation customerUserImplementation;

    private CartService cartService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                            JwtProvider jwtProvider, CustomerUserImplementation customerUserImplementation,
                          CartService cartService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.customerUserImplementation = customerUserImplementation;
        this.cartService = cartService;
    }

    @Override
    public AuthResponse createUserHandler(User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();;
        String lastName = user.getLastName();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist != null)
            throw new UserException("Email Is Already Used With Another Account");

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser = userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Success");

        return authResponse;
    }

    @Override
    public ResponseEntity<AuthResponse> loginUserHandler(LoginRequest loginRequest) {
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signin Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @Override
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserImplementation.loadUserByUsername(username);

        if(userDetails == null)
            throw new BadCredentialsException("Invalid UserName...");
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid Password...");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
