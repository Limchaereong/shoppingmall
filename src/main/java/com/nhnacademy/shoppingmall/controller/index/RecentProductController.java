package com.nhnacademy.shoppingmall.controller.index;

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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/recentlyView.do")
public class RecentProductController implements BaseController {

    private static final String RECENT_PRODUCT_COOKIE = "recentProducts";

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 최근 본 상품 목록을 가져와서 request attribute에 설정
        List<Product> recentProducts = getRecentProducts(request);
        request.setAttribute("recentProducts", recentProducts);
        return "/shop/recentProducts/recentProducts"; // 최근 본 상품 페이지 JSP 경로
    }

    public List<Product> getRecentProducts(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Product> recentProducts = new ArrayList<>();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(RECENT_PRODUCT_COOKIE)) {
                    String recentProductsString = cookie.getValue();
                    try {
                        // 쿠키의 값을 디코딩하여 최근 본 상품 ID들을 얻음
                        String[] productIds = URLDecoder.decode(recentProductsString, "UTF-8").split(",");
                        for (String productId : productIds) {
                            log.debug("Fetching product with ID: {}", productId);
                            // 각 상품 ID에 대해 ProductService를 사용하여 상품을 가져옴
                            Product product = productService.getProduct(Integer.parseInt(productId));
                            if (product != null) {
                                recentProducts.add(product);
                                log.debug("Added product to recent products: {}", product);
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        log.error("Unsupported encoding exception occurred: {}", e.getMessage());
                    }
                    break;
                }
            }
        }

        return recentProducts;
    }
}
