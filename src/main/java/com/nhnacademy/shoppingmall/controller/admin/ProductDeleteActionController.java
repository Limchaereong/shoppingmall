package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/deleteProductAction.do")
public class ProductDeleteActionController implements BaseController {

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productIdString = request.getParameter("product_id");
        request.setAttribute("successMessage", false);
        request.setAttribute("errorMessage", false);

        if(productIdString == null || productIdString.isEmpty()) {
            request.setAttribute("errorMessage", true);
            log.error("상품 Id가 제공되지 않았습니다.");
            return "layout/deleteproductForm";
        }

        try {
            int productId = Integer.parseInt(productIdString);
            productService.deleteProduct(productId);
            request.setAttribute("successMessage", true);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", true);
            log.error("상품 Id가 올바르지않습니다.", e);
        }
        return "layout/deleteproductForm";
    }

}
