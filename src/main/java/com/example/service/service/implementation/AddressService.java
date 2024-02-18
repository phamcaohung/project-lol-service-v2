package com.example.service.service.implementation;

import com.example.service.model.Address;
import com.example.service.model.User;

import java.util.List;

public interface AddressService {
    String deleteAddress(Long addressId);

    List<Address> getAddressByUser(User user);
}
