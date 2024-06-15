package com.nhnacademy.shoppingmall.controller.point;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.Impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.Impl.PointHistoryServiceImpl;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/userInfo/pointHistory.do")
@RequiredArgsConstructor
public class PointHistoryController implements BaseController {

    private final PointHistoryService pointHistoryService = new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<PointHistory> pointHistoryList = pointHistoryService.getAllPointHistory();
        log.info("Point history list size: {}", pointHistoryList.size());
        request.setAttribute("pointHistoryList", pointHistoryList);
        return "/shop/mypage/userInfo/point_history";
    }
}
