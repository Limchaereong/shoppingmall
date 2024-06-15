package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value ="/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String password = req.getParameter("user_password");
        req.setAttribute("loginFail", false);
        User user = userService.doLogin(userId, password);
        if (Objects.nonNull(user)) {
            log.info("Execute");
            HttpSession session = req.getSession(true); //세션이 없으면 새로 생성한다.
            session.setMaxInactiveInterval(60 * 60);
            session.setAttribute("user", user);
            session.setAttribute("userId", userId);
            return "redirect:/index.do";
        }
        req.setAttribute("loginFail", true);
        return "shop/login/login_form";
    }
}