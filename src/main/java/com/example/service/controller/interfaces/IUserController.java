package com.example.service.controller.interfaces;


import com.example.service.dto.UserDTO;
import com.example.service.exception.UserException;
import com.example.service.model.Address;
import com.example.service.model.User;
import com.example.service.request.AddressRequest;
import com.example.service.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public interface IUserController {
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException;

    @PostMapping("/address")
    public ApiResponse createUserAddress(@RequestHeader("Authorization") String jwt, @RequestBody Address address) throws UserException;

    @PutMapping("/address/{addressId}")
    public ApiResponse updateUserAddress(@RequestBody Address address, @PathVariable Long addressId);
}
