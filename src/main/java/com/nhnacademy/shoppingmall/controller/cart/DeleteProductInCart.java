package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/DeleteProductInCartAction.do")
public class DeleteProductInCart implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 삭제할 상품의 ID를 가져옵니다.
        String productIdStr = request.getParameter("productId");

        if(productIdStr != null && !productIdStr.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdStr);

                // 세션에서 장바구니를 가져옵니다.
                HttpSession session = request.getSession();
                List<Product> cart = (List<Product>) session.getAttribute("cart");

                if (cart != null && !cart.isEmpty()) {
                    // 장바구니에서 해당 상품을 찾아 제거합니다.
                    Iterator<Product> iterator = cart.iterator();
                    while (iterator.hasNext()) {
                        Product product = iterator.next();
                        if (product.getProductId() == productId) {
                            iterator.remove();
                            break;
                        }
                    }

                    // 변경된 장바구니를 세션에 다시 저장합니다.
                    session.setAttribute("cart", cart);
                }
            } catch (NumberFormatException e) {
                log.error("Invalid product ID format", e);
            }
        }

        return "redirect:/cart/cart.do";
    }
}
