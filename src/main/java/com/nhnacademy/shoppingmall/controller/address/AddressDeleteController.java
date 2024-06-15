package com.nhnacademy.shoppingmall.controller.address;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.address.repository.Impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.address.service.AddressService;
import com.nhnacademy.shoppingmall.entity.address.service.Impl.AddressServiceImpl;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/userInfo/addressDelete.do")
public class AddressDeleteController implements BaseController {

    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 삭제할 주소의 ID 가져오기
        String userId = request.getParameter("user_id");

        // 주소 삭제
        addressService.deleteAddressByUserId(userId);

        // 페이지 리다이렉트
        return "/shop/mypage/userInfo/addressForm";
    }
}
