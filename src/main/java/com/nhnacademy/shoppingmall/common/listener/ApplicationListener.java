package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        DbConnectionThreadLocal.initialize();
        if(userService.getUser("admin") == null) {
            User user = new User("admin", "admin", "12345", "19990422", User.Auth.ROLE_ADMIN, 0, LocalDateTime.now(), null);
            userService.saveUser(user);
            log.debug("Add Administrator");
        } else {
            log.debug("User already exists");
        }

        if (userService.getUser("user") == null) {
            User user = new User("user", "user", "12345", "19990422", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null);
            userService.saveUser(user);
            log.debug("Add User");
        } else {
            log.debug("User already exists");
        }
        DbConnectionThreadLocal.reset();
    }
}
