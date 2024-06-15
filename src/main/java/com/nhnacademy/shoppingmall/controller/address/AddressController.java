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
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/userInfo/address.do")
public class AddressController implements BaseController {

    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 주소 목록을 가져오는 작업
        try {
            List<Address> addressList = addressService.getAllAddresses(); // 주소 서비스를 사용하여 주소 목록을 가져옴
            request.setAttribute("addressList", addressList); // 주소 목록을 request에 저장하여 JSP로 전달
        } catch (Exception e) {
            log.error("주소 목록을 불러오는데 실패했습니다.", e);
            // 실패 시 필요한 처리를 여기에 추가
            return "redirect:/error"; // 에러 페이지로 리다이렉트
        }

        return "/shop/mypage/userInfo/addressForm"; // 주소 정보를 입력하고 수정하는 페이지로 이동
    }
}
