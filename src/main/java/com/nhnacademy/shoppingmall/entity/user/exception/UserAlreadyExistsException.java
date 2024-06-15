package com.nhnacademy.shoppingmall.entity.user.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userId){
        super(String.format("user already exist:%s",userId));
    }
}
