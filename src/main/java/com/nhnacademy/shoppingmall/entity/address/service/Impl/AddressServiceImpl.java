package com.nhnacademy.shoppingmall.entity.address.service.Impl;

import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.entity.address.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressRepository.update(address);
    }

    @Override
    public void deleteAddressByUserId(String userId) {
        addressRepository.deleteByUserId(userId);
    }

    @Override
    public Address findAddressByUserId(String userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.getAllAddresses();
    }
}
