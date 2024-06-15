package com.nhnacademy.shoppingmall.entity.address.repository.Impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.AddressRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal.getConnection;

public class AddressRepositoryImpl implements AddressRepository {

    @Override
    public int save(Address address) {
        Connection connection = getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "INSERT INTO user_addresses (user_id, zip_code, address, address_detail) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, address.getUserId());
            statement.setString(2, address.getZipCode());
            statement.setString(3, address.getAddress());
            statement.setString(4, address.getAddressDetail());

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Address address) {
        Connection connection = getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "UPDATE user_addresses SET zip_code = ?, address = ?, address_detail = ? WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, address.getZipCode());
            statement.setString(2, address.getAddress());
            statement.setString(3, address.getAddressDetail());
            statement.setString(4, address.getUserId());

            statement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        Connection connection = getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "DELETE FROM user_addresses WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);

            statement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public Address findByUserId(String userId) {
        Connection connection = getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "SELECT * FROM user_addresses WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Address(
                            resultSet.getString("user_id"),
                            resultSet.getString("zip_code"),
                            resultSet.getString("address"),
                            resultSet.getString("address_detail")
                    );
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null; // Or you can throw an exception to indicate that the address was not found
    }

    @Override
    public List<Address> getAllAddresses() {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        List<Address> addressList = new ArrayList<>();
        String sql = "SELECT * FROM user_addresses"; // 주소 정보를 가져오는 쿼리

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String zipCode = resultSet.getString("zip_code");
                String address = resultSet.getString("address");
                String addressDetail = resultSet.getString("address_detail");

                Address addr = new Address();
                addr.setZipCode(zipCode);
                addr.setAddress(address);
                addr.setAddressDetail(addressDetail);

                addressList.add(addr);
            }

            // 주소 목록 로그 출력
            for (Address address : addressList) {
                System.out.println("Zip Code: " + address.getZipCode() + ", Address: " + address.getAddress() + ", Address Detail: " + address.getAddressDetail());
            }

            return addressList;
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }


    private void handleSQLException(SQLException e) {
        // SQLException 처리를 원하는 방식으로 수행
        e.printStackTrace(); // 예제로 간단히 출력하는 방식으로 처리
    }
}