package com.nhnacademy.shoppingmall.controller.index;

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
import java.util.stream.Collectors;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = {"/index.do"})
public class IndexController implements BaseController {

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    private static final int PAGE_SIZE = 9; // 한 페이지당 보여줄 상품 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 현재 페이지 가져오기
        int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        // 전체 상품 목록 가져오기
        List<Product> productList = productService.getAllProdcutList();

        // 카테고리에 따라 상품 목록 필터링
        String selectedCategoryId = (String) request.getSession().getAttribute("selectedCategoryId");
        if (selectedCategoryId != null && !selectedCategoryId.isEmpty()) {
            productList = filterProductsByCategoryId(productList, selectedCategoryId);
        }

        // 전체 페이지 수 계산
        int totalPage = (int) Math.ceil((double) productList.size() / PAGE_SIZE);

        // 현재 페이지에 해당하는 상품 목록 가져오기
        List<Product> pageProducts = getPageProducts(productList, currentPage);

        HttpSession session = request.getSession();
        // 상품 목록과 페이지 정보를 세션에 저장
        session.setAttribute("list", pageProducts);
        session.setAttribute("totalPage", totalPage);
        session.setAttribute("currentPage", currentPage);

        return "shop/main/index";
    }

    // 카테고리에 따라 상품 목록 필터링
    private List<Product> filterProductsByCategoryId(List<Product> productList, String categoryId) {
        return productList.stream()
                .filter(product -> String.valueOf(product.getCategoryId()).equals(categoryId))
                .collect(Collectors.toList());
    }

    // 현재 페이지에 해당하는 상품 목록 가져오기
    private List<Product> getPageProducts(List<Product> productList, int currentPage) {
        int startIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, productList.size());
        return productList.subList(startIndex, endIndex);
    }
}