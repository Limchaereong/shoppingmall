package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/deleteProduct.do")
public class ProductDeleteFormController implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "layout/deleteproductForm";
    }
}
