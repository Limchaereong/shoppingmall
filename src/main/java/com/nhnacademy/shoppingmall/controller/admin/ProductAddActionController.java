package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addProductAction.do")
public class ProductAddActionController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String categoryIdStr = req.getParameter("category_id");
            String productName = req.getParameter("product_name");
            String productPrice = req.getParameter("product_price");
            String thumbnailImage = req.getParameter("thumbnail_image");
            String detailImage = req.getParameter("detail_image");
            String productDescription = req.getParameter("product_description");
            String createdAt = req.getParameter("created_at");
            String updatedAt = req.getParameter("updated_at");

            // 빈 값 검증
            if (isNullOrEmpty(categoryIdStr) || isNullOrEmpty(productName) || isNullOrEmpty(productPrice) || isNullOrEmpty(thumbnailImage) || isNullOrEmpty(detailImage) || isNullOrEmpty(productDescription) || isNullOrEmpty(createdAt) || isNullOrEmpty(updatedAt)) {
                req.setAttribute("errorMessage", true);
                log.warn("입력이 안된 칸이 있음");
                return "layout/addproductForm";
            }

            // 카테고리 ID 유효성 검증
            Integer categoryId = Integer.parseInt(categoryIdStr.trim());
            if (!isValidCategoryId(categoryId)) {
                req.setAttribute("errorMessage", true);
                log.warn("유효하지 않은 카테고리 ID입니다.");
                return "layout/addproductForm";
            }

            // Product 객체 생성 및 값 설정
            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setProductName(productName.trim());
            product.setProductPrice(new BigDecimal(productPrice.trim()));
            product.setThumbnailImage(thumbnailImage.trim());
            product.setDetailImage(detailImage.trim());
            product.setProductDescription(productDescription.trim());
            product.setCreatedAt(LocalDateTime.parse(createdAt.trim(), DateTimeFormatter.ISO_DATE_TIME));
            product.setUpdatedAt(LocalDateTime.parse(updatedAt.trim(), DateTimeFormatter.ISO_DATE_TIME));

            productService.saveProduct(product);

            // 성공 메시지 설정
            req.setAttribute("successMessage", true);
            // 클라이언트를 새로운 URL로 리다이렉트하여 이동시킴
            return "redirect:/admin/productList.do";
        } catch (NumberFormatException | DateTimeParseException e) {
            log.error("입력 형식 오류", e);
            req.setAttribute("errorMessage", true);
            return "layout/addproductForm";  // 입력 오류 시 같은 폼으로 리턴
        } catch (Exception e) {
            log.error("상품 추가 중 오류 발생", e);
            req.setAttribute("errorMessage", true);
            return "layout/addproductForm";  // 기타 오류 시 같은 폼으로 리턴
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isValidCategoryId(Integer categoryId) {
        // 여기에 카테고리 ID의 유효성을 검증하는 로직을 구현
        // 예를 들어, 해당 ID가 데이터베이스에 존재하는지 확인하는 코드를 작성할 수 있음
        // 이 예제에서는 단순히 true를 반환하도록 작성
        return true;
    }
}
