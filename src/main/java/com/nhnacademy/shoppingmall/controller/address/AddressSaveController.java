package com.nhnacademy.shoppingmall.controller.address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.address.domain.Address;
import com.nhnacademy.shoppingmall.entity.address.repository.Impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.address.service.AddressService;
import com.nhnacademy.shoppingmall.entity.address.service.Impl.AddressServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/userInfo/addressSave.do")
public class AddressSaveController implements BaseController {

    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 주소 정보 가져오기
        String userId = request.getParameter("user_id");
        String zipCode = request.getParameter("zip_code");
        String address = request.getParameter("address");
        String addressDetail = request.getParameter("address_detail");

        // 받은 매개변수 로깅하기
        log.info("받은 매개변수: userId={}, zipCode={}, address={}, addressDetail={}", userId, zipCode, address, addressDetail);

        // userId가 null 또는 빈 문자열인지 확인
        if (userId == null || userId.isEmpty()) {
            log.error("사용자 ID가 null이거나 빈 문자열입니다.");
            // 필요한 처리 수행
            // 예: 오류 메시지 반환
            return "redirect:/error";
        }

        // 주소 객체 생성
        Address newAddress = new Address(zipCode, address, addressDetail);
        newAddress.setUserId(userId);
        newAddress.setZipCode(zipCode);
        newAddress.setAddress(address);
        newAddress.setAddressDetail(addressDetail);

        try {
            // 주소 저장
            addressService.saveAddress(newAddress);
        } catch (Exception e) {
            log.error("주소 저장에 실패했습니다.", e);
            // 예외 처리 후, 필요한 작업 수행
            // 예: 사용자에게 오류 메시지 표시
            return "redirect:/error"; // 에러 페이지로 리다이렉트
        }

        // 주소 저장 후 페이지 이동
        return "/shop/mypage/userInfo/addressForm";
    }
}
