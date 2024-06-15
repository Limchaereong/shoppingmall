package com.nhnacademy.shoppingmall.check.entity.address.repository;

import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.entity.address.repository.Impl.AddressRepositoryImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryImplTest {
    private static AddressRepository addressRepository;

    @BeforeAll
    static void setUp() {
        addressRepository = new AddressRepositoryImpl();
    }

    @Test
    void saveAddressTest() {
        Address address = new Address("user", "25252", "광주광역시 서구 풍암동", "가나다마아파트 204동");

        int result = addressRepository.save(address);

        assertEquals(1, result);
    }

    @Test
    void updateAddressTest() {
        Address address = new Address("user", "25252", "광주광역시 서구 풍암동", "가나다마아파트 204동");

        addressRepository.update(address);

        Address updatedAddress = addressRepository.findByUserId("user");

        assertNotNull(updatedAddress);
        assertEquals("user", updatedAddress.getUserId());
        assertEquals("25252", updatedAddress.getZipCode());
        assertEquals("광주광역시 서구 풍암동", updatedAddress.getAddress());
        assertEquals("가나다마아파트 204동", updatedAddress.getAddressDetail());
    }

    @Test
    void deleteAddressByUserIdTest() {
        String userId = "testUserId";

        addressRepository.deleteByUserId(userId);

        Address deletedAddress = addressRepository.findByUserId(userId);

        assertNull(deletedAddress);
    }

    @Test
    void findByUserIdTest() {
        String userId = "user";

        Address address = addressRepository.findByUserId(userId);

        assertNotNull(address);
        assertEquals("user", address.getUserId());
        // Add assertions for other fields if needed
    }

    @Test
    void getAllAddressesTest() {
        List<Address> addressList = addressRepository.getAllAddresses();

        assertNotNull(addressList);
        assertTrue(addressList.size() > 0);
        // Add assertions for the contents of the address list if needed
    }
}
