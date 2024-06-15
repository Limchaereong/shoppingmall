package com.nhnacademy.shoppingmall.entity.product.service;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.common.page.Page;
import java.util.List;

public interface ProductService {

    Product getProduct(int productId);

    Page<Product> getProductPageList(int pageSize, int currentPage);

    List<Product> getAllProdcutList();
    void saveProduct(Product product);


    void updateProduct(Product product);

    void deleteProduct(int productId);
}