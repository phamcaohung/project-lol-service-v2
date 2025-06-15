package com.example.service.repository;

import com.example.service.model.Address;
import com.example.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressById(Long id);

    List<Address> findAddressByUser(User user);

}
