package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;



@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/productDetail.do")
public class ProductDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청으로부터 상품 ID를 가져옴
        int productId = Integer.parseInt(request.getParameter("productId"));

        // ProductService를 사용하여 상품 정보를 가져옴
        Product product = productService.getProduct(productId);

        // 상품 정보가 null이 아니라면 로깅 수행
        if (product != null) {
            log.debug("썸네일 이미지 값: {}", product.getThumbnailImage());
            log.debug("상세 이미지 값: {}", product.getDetailImage());
        } else {
            log.debug("상품 정보가 null입니다.");
        }

        // request attribute에 상품 정보 설정
        request.setAttribute("product", product);

        // 상품 정보를 쿠키에 저장
        addRecentProduct(request, response, String.valueOf(productId));

        // 상품 상세 페이지로 포워딩
        return "shop/product/productDetail";
    }

    private void addRecentProduct(HttpServletRequest request, HttpServletResponse response, String productId) {
        // ProductService를 사용하여 상품 정보를 가져옴
        Product product = productService.getProduct(Integer.parseInt(productId));
        if (product != null) {
            // 쿠키 생성
            Cookie recentProductCookie = new Cookie("recentProducts", productId);
            // 쿠키 수명 설정 (1주일)
            recentProductCookie.setMaxAge(7 * 24 * 60 * 60);
            // 쿠키 경로 설정 (루트 경로)
            recentProductCookie.setPath("/");
            // 응답에 쿠키 추가
            response.addCookie(recentProductCookie);
            log.debug("Added product with ID: {} to recent products", productId);

            // 세션에서 최근 본 상품 목록을 가져옴
            List<Product> recentProducts = (List<Product>) request.getSession().getAttribute("recentProducts");
            if (recentProducts == null) {
                recentProducts = new ArrayList<>();
            }

            // 최근 본 상품 목록에 해당 상품이 이미 있는지 확인 후, 중복되지 않도록 추가
            if (!recentProducts.contains(product)) {
                recentProducts.add(0, product); // 최근 본 상품 목록의 가장 앞에 추가
                // 최근 본 상품 목록을 세션에 다시 설정
                request.getSession().setAttribute("recentProducts", recentProducts);
            }
        }
    }

}
