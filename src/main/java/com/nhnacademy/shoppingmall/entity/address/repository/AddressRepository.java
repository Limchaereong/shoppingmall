package com.nhnacademy.shoppingmall.entity.address.repository;

import com.nhnacademy.shoppingmall.entity.address.domain.Address;

import java.util.List;


public interface AddressRepository {
    int save(Address address);
    void update(Address address);
    void deleteByUserId(String userId);
    Address findByUserId(String userId);
    List<Address> getAllAddresses();
}
