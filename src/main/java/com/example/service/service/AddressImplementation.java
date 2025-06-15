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
        addressRepository.delete(address);
        return "Deleted address successfully";
    }

    @Override
    public List<Address> getAddressByUser(User user) {
        return addressRepository.findAddressByUser(user);
    }

    @Override
    public Address editAddress(Long addressId) {
        return addressRepository.findAddressById(addressId);
    }

    @Override
    public Address createAddress(User user, Address req) {
        Address address = new Address();
        address.setStreetAddress(req.getStreetAddress());
        address.setCity(req.getCity());
        address.setState(req.getState());
        address.setZipCode(req.getZipCode());
        address.setMobile(req.getMobile());
        address.setFirstName(req.getFirstName());
        address.setLastName(req.getLastName());
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address updateUserAddress(Long addressId, Address req) {
        Address address = addressRepository.findAddressById(addressId);
        address.setStreetAddress(req.getStreetAddress());
        address.setCity(req.getCity());
        address.setState(req.getState());
        address.setZipCode(req.getZipCode());
        address.setMobile(req.getMobile());
        address.setFirstName(req.getFirstName());
        address.setLastName(req.getLastName());
        return addressRepository.save(address);
    }
}
