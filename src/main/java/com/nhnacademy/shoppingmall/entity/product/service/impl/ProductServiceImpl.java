package com.nhnacademy.shoppingmall.entity.product.service.impl;

import com.nhnacademy.shoppingmall.entity.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.common.page.Page;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 상품 ID로 상품을 조회합니다.
     *
     * @param productId 상품 ID
     * @return 상품
     */
    @Override
    public Product getProduct(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("상품 ID " + productId + " 에 해당하는 상품이 없습니다.");
        }

        return optionalProduct.get();
    }

    @Override
    public Page<Product> getProductPageList(int pageSize, int currentPage) {
        Optional<Page<Product>> pageProducts = productRepository.findProductList(pageSize, currentPage);

        if (!pageProducts.isPresent()) {
            throw new RuntimeException("상품들을 불러오는데 실패했습니다.");
        }

        return pageProducts.get();
    }

    @Override
    public List<Product> getAllProdcutList() {
        return productRepository.findAllList();
    }


    /**
     * 새 상품을 저장합니다.
     *
     * @param product 저장할 상품
     */
    @Override
    public void saveProduct(Product product) {
        if (productRepository.findById(product.getProductId()).isPresent()) {
            throw new RuntimeException("상품 ID " + product.getProductId() + " 에 해당하는 상품이 이미 존재합니다.");
        }
        log.info("상품 생성");
        productRepository.save(product);
    }

    /**
     * 기존 상품을 수정합니다.
     *
     * @param product 수정할 상품
     */
    @Override
    public void updateProduct(Product product) {
        if (!productRepository.findById(product.getProductId()).isPresent()) {
            throw new RuntimeException("상품 ID " + product.getProductId() + " 에 해당하는 상품이 없습니다.");
        }
        productRepository.update(product);
    }

    /**
     * 상품 ID로 상품을 삭제합니다.
     *
     * @param productId 삭제할 상품 ID
     */
    @Override
    public void deleteProduct(int productId) {
        if (!productRepository.findById(productId).isPresent()) {
            throw new RuntimeException("상품 ID " + productId + " 에 해당하는 상품이 없습니다.");
        }

        productRepository.deleteByProductId(String.valueOf(productId));
    }
}