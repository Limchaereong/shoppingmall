package com.nhnacademy.shoppingmall.entity.address.service;

import com.nhnacademy.shoppingmall.entity.address.domain.Address;

import java.util.List;

public interface AddressService {
    void saveAddress(Address address);
    void updateAddress(Address address);
    void deleteAddressByUserId(String userId);
    Address findAddressByUserId(String userId);
    List<Address> getAllAddresses();
}
