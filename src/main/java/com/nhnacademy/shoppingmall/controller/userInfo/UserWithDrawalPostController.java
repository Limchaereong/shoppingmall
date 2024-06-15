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

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/userInfo/withdrawalAction.do")
public class UserWithDrawalPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                // 회원정보 삭제
                userService.deleteUser(user.getUserId());
                // 세션에서 사용자 정보 제거
                session.removeAttribute("user");
                // 세션 무효화
                session.invalidate();
                log.info("{}님의 회원탈퇴가 완료되었습니다.", user.getUserName());
            } else {
                log.error("세션에 사용자 정보가 없습니다.");
            }
        } else {
            log.error("세션이 존재하지 않습니다.");
        }
        // 회원탈퇴 후 리다이렉트할 경로 설정 (여기서는 홈 화면으로 리다이렉트)
        return "redirect:/index.do";
    }
}
