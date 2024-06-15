package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/mypage.do")
public class MyPageController implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "shop/mypage/mypage";
    }
}
