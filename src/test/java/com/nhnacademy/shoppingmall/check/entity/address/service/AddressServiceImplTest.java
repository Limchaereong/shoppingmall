package com.nhnacademy.shoppingmall.check.entity.address.service;

import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.entity.address.service.Impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveAddressTest() {
        Address address = new Address("userId", "12345", "Street 1", "Apt 101");
        when(addressRepository.save(address)).thenReturn(1);

        addressService.saveAddress(address);

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void updateAddressTest() {
        Address address = new Address("userId", "12345", "Street 1", "Apt 101");

        addressService.updateAddress(address);

        verify(addressRepository, times(1)).update(address);
    }

    @Test
    void deleteAddressByUserIdTest() {
        String userId = "userId";

        addressService.deleteAddressByUserId(userId);

        verify(addressRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void findAddressByUserIdTest() {
        String userId = "userId";
        Address expectedAddress = new Address(userId, "12345", "Street 1", "Apt 101");
        when(addressRepository.findByUserId(userId)).thenReturn(expectedAddress);

        Address resultAddress = addressService.findAddressByUserId(userId);

        assertEquals(expectedAddress, resultAddress);
    }

    @Test
    void getAllAddressesTest() {
        List<Address> expectedAddresses = new ArrayList<>();
        expectedAddresses.add(new Address("userId1", "12345", "Street 1", "Apt 101"));
        expectedAddresses.add(new Address("userId2", "67890", "Street 2", "Apt 202"));
        when(addressRepository.getAllAddresses()).thenReturn(expectedAddresses);

        List<Address> resultAddresses = addressService.getAllAddresses();

        assertEquals(expectedAddresses.size(), resultAddresses.size());
        for (int i = 0; i < expectedAddresses.size(); i++) {
            assertEquals(expectedAddresses.get(i), resultAddresses.get(i));
        }
    }
}
