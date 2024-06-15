package com.nhnacademy.shoppingmall.entity.user.service.impl;

import com.nhnacademy.shoppingmall.entity.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.entity.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.entity.user.domain.User;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (userRepository.countByUserId(user.getUserId()) == 0)
            userRepository.save(user);
        else
            throw new UserAlreadyExistsException("유저 ID가 존재합니다.");
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (userRepository.countByUserId(user.getUserId()) == 1)
            userRepository.update(user);
        else
            throw new UserNotFoundException("업데이트할 유저가 없습니다.");
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (userRepository.countByUserId(userId) == 1)
            userRepository.deleteByUserId(userId);
        else
            throw new UserNotFoundException("삭제할 유저가 없습니다.");
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        User user = userRepository.findByUserIdAndUserPassword(userId, userPassword).orElse(null);
        if (user == null) {
            log.info("로그인 실패");
            return null;
        } else
            userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        return user;
    }

}