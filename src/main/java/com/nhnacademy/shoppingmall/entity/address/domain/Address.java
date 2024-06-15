package com.nhnacademy.shoppingmall.entity.address.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String userId;
    private String zipCode;
    private String address;
    private String addressDetail;

    public Address(String zipCode, String address, String addressDetail) {
        //
    }
    public Address() {
        //
    }

    public Address(String userId, String zipCode, String address, String addressDetail) {
        this.userId = userId;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userId=" + userId +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}
