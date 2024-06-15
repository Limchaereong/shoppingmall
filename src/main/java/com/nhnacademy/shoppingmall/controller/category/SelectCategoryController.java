package com.nhnacademy.shoppingmall.controller.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/SelectCategoryAction.do")
public class SelectCategoryController implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 카테고리 선택 액션 처리 로직을 여기에 구현합니다.

        // 선택된 카테고리 ID 가져오기
        String categoryId = request.getParameter("categoryId");

        // 이후에 선택된 카테고리 ID를 이용하여 상품 목록을 필터링하거나 다른 작업을 수행할 수 있습니다.
        // 여기서는 세션에 선택된 카테고리 ID를 저장합니다.
        request.getSession().setAttribute("selectedCategoryId", categoryId);

        // 선택된 카테고리에 따라 다시 상품 목록 페이지로 이동합니다.
        return "redirect:/index.do";
    }
}

