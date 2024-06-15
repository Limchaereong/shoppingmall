package com.nhnacademy.shoppingmall.check.entity.product.repository;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class ProductRepositoryImplTest {
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // 테스트 전에 필요한 초기화 작업을 수행합니다.
        // 예를 들어, 테스트용 데이터베이스 연결 설정 등을 여기서 할 수 있습니다.
        DbConnectionThreadLocal mockDbConnectionThreadLocal = mock(DbConnectionThreadLocal.class);
        productRepository = new ProductRepositoryImpl();
    }

    @Test
    void findById_existingProductId_shouldReturnProduct() {
        // 존재하는 상품 ID로 findById 메서드를 호출하여 상품을 가져오는지 확인합니다.
        int existingProductId = 1; // 존재하는 상품 ID로 변경하세요.

        Optional<Product> productOptional = productRepository.findById(existingProductId);

        Assertions.assertTrue(productOptional.isPresent());
        Product product = productOptional.get();
        Assertions.assertEquals(existingProductId, product.getProductId());
        // 추가적으로 가져온 상품의 속성에 대한 검증을 진행할 수 있습니다.
    }

    @Test
    void findById_nonExistingProductId_shouldReturnEmptyOptional() {
        // 존재하지 않는 상품 ID로 findById 메서드를 호출하여 빈 Optional이 반환되는지 확인합니다.
        int nonExistingProductId = -1; // 존재하지 않는 상품 ID로 변경하세요.

        Optional<Product> productOptional = productRepository.findById(nonExistingProductId);

        Assertions.assertFalse(productOptional.isPresent());
    }

    @Test
    void findAllList_shouldReturnListOfProducts() {
        // 모든 상품을 가져오는 findAllList 메서드를 호출하여 상품 목록을 가져오는지 확인합니다.

        List<Product> productList = productRepository.findAllList();

        Assertions.assertNotNull(productList);
        // 가져온 상품 목록에 대한 검증을 진행할 수 있습니다.
    }

    @Test
    void findProductList_shouldReturnPageOfProducts() {
        int pageSize = 10; // 페이지 크기
        int currentPage = 1; // 현재 페이지

        Optional<Page<Product>> pageOptional = productRepository.findProductList(pageSize, currentPage);

        Assertions.assertTrue(pageOptional.isPresent());
        Page<Product> page = pageOptional.get();
        List<Product> productList = page.getContent();
        Assertions.assertNotNull(productList);
        // 페이지의 내용에 대한 검증을 진행할 수 있습니다.
    }

    // save 메서드에 대한 테스트 케이스
    @Test
    void save_shouldInsertProductIntoDatabase() {
        Product product = new Product();
        // 상품 정보를 적절히 초기화하세요.
        product.setCategoryId(1);
        product.setProductName("Test Product");
        product.setProductPrice(BigDecimal.valueOf(1000));
        product.setThumbnailImage("thumbnail.jpg");
        product.setDetailImage("detail.jpg");
        product.setProductDescription("Test description");
        product.setCreatedAt(LocalDateTime.now()); // 현재 시간으로 설정
        product.setUpdatedAt(LocalDateTime.now()); // 현재 시간으로 설정

        int result = productRepository.save(product);

        Assertions.assertNotEquals(0, result);
        // 삽입된 행의 수가 0이 아닌지 검증합니다.
        // 추가적으로 삽입된 상품의 ID 등에 대한 검증을 진행할 수 있습니다.
    }


    // deleteByProductId 메서드에 대한 테스트 케이스
    @Test
    void deleteByProductId_shouldDeleteProductFromDatabase() {
        String productId = "1"; // 삭제할 상품 ID

        int result = productRepository.deleteByProductId(productId);

        Assertions.assertNotEquals(0, result);
        // 삭제된 행의 수가 0이 아닌지 검증합니다.
        // 삭제된 상품이 실제로 데이터베이스에서 삭제되었는지 확인할 수 있습니다.
    }

    // update 메서드에 대한 테스트 케이스
    @Test
    void update_shouldUpdateProductInDatabase() {
        Product product = new Product();

        // 업데이트할 상품 정보를 적절히 초기화하세요.
        product.setProductId(1);
        product.setCategoryId(1);
        product.setProductName("의류 상품 A");
        product.setProductPrice(BigDecimal.valueOf(10000.00));
        product.setThumbnailImage("https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230411_198%2F1681197878767x8Rhp_JPEG%2F51587393_0_600.jpg&type=sc960_832");
        product.setDetailImage("https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230411_198%2F1681197878767x8Rhp_JPEG%2F51587393_0_600.jpg&type=sc960_832");
        product.setProductDescription("의류 상품 A 설명");
        product.setCreatedAt(LocalDateTime.now()); // 현재 시간으로 설정
        product.setUpdatedAt(LocalDateTime.now()); // 현재 시간으로 설정

        int result = productRepository.update(product);

        Assertions.assertNotEquals(0, result);
        // 업데이트된 행의 수가 0이 아닌지 검증합니다.
        // 업데이트된 상품의 정보가 실제로 데이터베이스에 반영되었는지 확인할 수 있습니다.
    }
}
