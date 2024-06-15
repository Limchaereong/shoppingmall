package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.Impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.Impl.PointHistoryServiceImpl;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.PointHistoryService;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/PlaceOrderAction.do")
public class OrderCompleteActionController implements BaseController {

    private final UserService userService;
    private final PointHistoryService pointHistoryService;

    public OrderCompleteActionController() {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
        this.pointHistoryService = new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 주문 완료 후 포인트 차감 및 추가 로직 추가
        User user = (User) request.getSession().getAttribute("user");
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        BigDecimal totalPrice = BigDecimal.ZERO;

        // 총 주문 금액 계산
        if (cart != null) {
            for (Product product : cart) {
                BigDecimal productPrice = new BigDecimal(String.valueOf(product.getProductPrice()));
                totalPrice = totalPrice.add(productPrice);
            }
        }

        // 결제한 금액의 10%를 포인트로 추가
        BigDecimal pointToAdd = totalPrice.multiply(new BigDecimal("0.1")); // 결제한 금액의 10% 계산
        int pointToAddInt = pointToAdd.setScale(0, BigDecimal.ROUND_DOWN).intValue(); // 정수로 변환
        if (user != null) {
            int currentPoint = user.getUserPoint();
            int updatedPoint = currentPoint + pointToAddInt;
            user.setUserPoint(updatedPoint);
            // 포인트 추가 후 사용자 정보 업데이트 (예: 데이터베이스에 저장)
            userService.updateUser(user);

            // 포인트 내역 추가
            PointHistory pointHistoryToAdd = new PointHistory(user.getUserId(), "적립", pointToAdd, LocalDateTime.now());
            pointHistoryService.addPointHistory(pointHistoryToAdd);
        } else {
            log.error("사용자 정보를 가져올 수 없습니다.");
            // 사용자 정보가 없는 경우 처리 로직 추가
        }

        // 결제한 금액만큼 포인트 차감
        BigDecimal pointToDeduct = totalPrice.setScale(0, BigDecimal.ROUND_DOWN); // 결제한 금액을 정수로 변경
        if (user != null) {
            int userPoint = user.getUserPoint();
            if (userPoint >= pointToDeduct.intValue()) {
                int remainingPoint = userPoint - pointToDeduct.intValue();
                user.setUserPoint(remainingPoint);
                // 포인트 차감 후 사용자 정보 업데이트 (예: 데이터베이스에 저장)
                userService.updateUser(user);

                // 포인트 내역 추가
                PointHistory pointHistoryToDeduct = new PointHistory(user.getUserId(), "사용", pointToDeduct.negate(), LocalDateTime.now());
                pointHistoryService.addPointHistory(pointHistoryToDeduct);
            } else {
                log.error("사용자 포인트가 주문 금액보다 부족합니다.");
                // 포인트가 부족할 경우 처리 로직 추가
            }
        } else {
            log.error("사용자 정보를 가져올 수 없습니다.");
            // 사용자 정보가 없는 경우 처리 로직 추가
        }

        // 장바구니 비우는 로직 추가
        if (cart != null) {
            cart.clear(); // 장바구니 비우기
            request.getSession().setAttribute("cart", cart); // 세션에 변경된 장바구니 업데이트
        }

        // 주문 완료 후에 어떤 페이지로 이동할지 반환
        return "shop/main/index"; // 예시로 메인 페이지로 이동하도록 설정
    }
}
