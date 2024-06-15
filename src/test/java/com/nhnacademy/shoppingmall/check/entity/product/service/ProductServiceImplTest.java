package com.nhnacademy.shoppingmall.check.entity.product.service;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setProductName("Test Product");
        // 다른 필요한 속성들을 여기에 설정할 수 있습니다.
    }

    @Test
    void getProduct_whenProductExists() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product found = productService.getProduct(1);

        assertNotNull(found);
        assertEquals("Test Product", found.getProductName());
    }

    @Test
    void getProduct_whenProductDoesNotExist() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> productService.getProduct(999));

        assertEquals("상품 ID 999 에 해당하는 상품이 없습니다.", exception.getMessage());
    }

    @Test
    void saveProduct_whenNewProduct() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> productService.saveProduct(product));
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void saveProduct_whenProductAlreadyExists() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        Exception exception = assertThrows(RuntimeException.class, () -> productService.saveProduct(product));

        assertEquals("상품 ID 1 에 해당하는 상품이 이미 존재합니다.", exception.getMessage());
    }

    @Test
    void deleteProduct_whenProductExists() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        assertDoesNotThrow(() -> productService.deleteProduct(1));
        verify(productRepository, times(1)).deleteByProductId("1");
    }

    @Test
    void deleteProduct_whenProductDoesNotExist() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> productService.deleteProduct(999));

        assertEquals("상품 ID 999 에 해당하는 상품이 없습니다.", exception.getMessage());
    }
}
