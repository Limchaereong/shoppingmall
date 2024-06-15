package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/AddMainToCartAction.do")
public class AddMainToCart implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 세션을 얻어옵니다.
        HttpSession session = request.getSession();

        // 담길 상품의 정보를 가져옵니다.
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPriceStr = request.getParameter("productPrice");
        String thumbnailImage = request.getParameter("thumbnailImage");

        // 세션에서 기존의 장바구니 목록을 가져옵니다.
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            // 만약 세션에 장바구니가 없다면 새로운 리스트를 생성합니다.
            cart = new ArrayList<>();
        }

        // 상품 가격을 BigDecimal로 변환합니다.
        BigDecimal productPrice = new BigDecimal(productPriceStr);

        // 새로운 상품을 생성하여 장바구니에 추가합니다.
        Product newProduct = new Product();
        newProduct.setProductId(Integer.parseInt(productId));
        newProduct.setProductName(productName);
        newProduct.setProductDescription(productDescription);
        newProduct.setProductPrice(productPrice);
        newProduct.setThumbnailImage(thumbnailImage); // 이미지 URL 설정
        cart.add(newProduct);

        // 변경된 장바구니를 세션에 다시 저장합니다.
        session.setAttribute("cart", cart);

        // 상품을 장바구니에 추가한 후에는 장바구니 페이지로 이동합니다.
        return "redirect:/cart/cart.do";
    }
}
