package com.nhnacademy.shoppingmall.controller.address;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.Impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.address.service.AddressService;
import com.nhnacademy.shoppingmall.entity.address.service.Impl.AddressServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/userInfo/addressUpdate.do")
public class AddressUpdateController implements BaseController {

    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 수정할 주소의 정보 가져오기
        String userId = request.getParameter("user_id");
        String zipCode = request.getParameter("zip_code");
        String address = request.getParameter("address");
        String addressDetail = request.getParameter("address_detail");

        // 주소 객체 생성
        Address updatedAddress = new Address(userId, zipCode, address, addressDetail);

        // 주소 수정
        addressService.updateAddress(updatedAddress);

        // 페이지 리다이렉트
        return "redirect:/shop/mypage/userInfo/addressForm";
    }
}
