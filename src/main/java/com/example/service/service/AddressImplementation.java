package com.example.service.service;

import com.example.service.model.Address;
import com.example.service.model.User;
import com.example.service.repository.AddressRepository;
import com.example.service.service.implementation.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressImplementation implements AddressService {

    private AddressRepository addressRepository;

    public AddressImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address address = addressRepository.findAddressById(addressId);
        address.setStatus("CANCEL");
        addressRepository.save(address);
        return "Deleted address successfully";
    }

    @Override
    public List<Address> getAddressByUser(User user) {
        return addressRepository.findAddressByUserAndStatusNot(user, "CANCEL");
    }
}
