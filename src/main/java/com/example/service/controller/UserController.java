package com.example.service.controller;

import com.example.service.controller.interfaces.IUserController;
import com.example.service.dto.UserDTO;
import com.example.service.exception.UserException;
import com.example.service.mapper.UserMapper;
import com.example.service.model.Address;
import com.example.service.model.User;
import com.example.service.request.AddressRequest;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.AddressService;
import com.example.service.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class UserController implements IUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    @Override
    public ResponseEntity<UserDTO> getUserProfile(String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        UserDTO userDTO = UserMapper.INSTANCE.map(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponse createUserAddress(String jwt, Address addressRequest) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        addressService.createAddress(user, addressRequest);

        ApiResponse response = new ApiResponse();
        response.setMessage("Create User Address Successfully");
        response.setStatus(true);
        return response;
    }

    @Override
    public ApiResponse updateUserAddress(Address address, Long addressId) {
        addressService.updateUserAddress(addressId, address);

        ApiResponse response = new ApiResponse();
        response.setMessage("Update User Address Successfully");
        response.setStatus(true);
        return response;
    }
}
