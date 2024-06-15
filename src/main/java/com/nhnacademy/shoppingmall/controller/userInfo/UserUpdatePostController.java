package com.nhnacademy.shoppingmall.controller.userInfo;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/userInfo/updateAction.do")
public class UserUpdatePostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 세션이 없을 경우 처리할 내용을 추가하세요.
            return "redirect:/login.do"; // 예: 로그인 페이지로 이동
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 사용자 정보가 세션에 없을 경우 처리할 내용을 추가하세요.
            return "redirect:/login.do"; // 예: 로그인 페이지로 이동
        }

        log.debug("{}을 업데이트 합니다.", user.toString());

        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.valueOf(req.getParameter("user_auth"));

        // 기존 정보 유지
        int userPoint = user.getUserPoint();
        String userId = user.getUserId();
        LocalDateTime userCreatedAt = user.getCreatedAt();
        LocalDateTime userLastLoginAt = user.getLatestLoginAt();

        // user_birth가 null이면 빈 문자열로 처리
        if (userBirth == null) {
            userBirth = "";
        }

        user = new User(userId, userName, userPassword, userBirth, userAuth, userPoint, userCreatedAt, userLastLoginAt);
        userService.updateUser(user);
        session.setAttribute("user", user);

        log.info("{}을 update 완료 ", user.getUserName());
        return "shop/mypage/userInfo/userInfo";
    }
}
