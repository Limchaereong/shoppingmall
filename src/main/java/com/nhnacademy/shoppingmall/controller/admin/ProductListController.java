package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productList.do")
public class ProductListController implements BaseController {

    private static final int PRODUCTS_PER_PAGE = 5;

    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청된 페이지 번호 파라미터 확인
        int page = getPageParameter(request);

        // 전체 상품 목록 조회
        List<Product> allProducts = productService.getAllProdcutList();

        // 페이지에 맞는 상품 목록 추출
        List<Product> productsOnPage = paginate(allProducts, page);

        // 세션에 상품 목록 저장
        HttpSession session = request.getSession(false);
        session.setAttribute("list", productsOnPage);

        // 현재 페이지 및 전체 페이지 수 계산하여 JSP로 전달
        int totalPages = calculateTotalPages(allProducts.size());
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        return "layout/printProductList";
    }

    // 요청된 페이지 번호를 가져오는 메서드
    private int getPageParameter(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        int page = 1; // 기본 페이지 번호는 1로 설정

        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // 페이지 번호 파라미터가 잘못된 경우 기본값인 1 사용
            }
        }
        return page;
    }

    // 전체 상품 목록을 페이징하여 현재 페이지에 맞는 상품 목록을 반환하는 메서드
    private List<Product> paginate(List<Product> allProducts, int page) {
        int startIndex = (page - 1) * PRODUCTS_PER_PAGE;
        int endIndex = Math.min(startIndex + PRODUCTS_PER_PAGE, allProducts.size());
        return allProducts.subList(startIndex, endIndex);
    }

    // 전체 상품 개수를 바탕으로 전체 페이지 수를 계산하는 메서드
    private int calculateTotalPages(int totalProducts) {
        return (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);
    }
}
