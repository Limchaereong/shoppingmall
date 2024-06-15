package com.nhnacademy.shoppingmall.entity.product.repository;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.common.page.Page;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(int productId);

    Optional<Page<Product>> findProductList(int pageSize, int currentPage);

    List<Product> findAllList();

    int save(Product product);
    int deleteByProductId(String productId);
    int update(Product product);

}