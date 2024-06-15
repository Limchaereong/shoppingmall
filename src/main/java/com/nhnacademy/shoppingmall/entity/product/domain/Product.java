package com.nhnacademy.shoppingmall.entity.product.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private BigDecimal productPrice;
    private String thumbnailImage;
    private String detailImage;
    private String productDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {
        //
    }

    public Product(int productId, int categoryId, String productName, BigDecimal productPrice, String thumbnailImage, String detailImage, String productDescription, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.thumbnailImage = thumbnailImage;
        this.detailImage = detailImage;
        this.productDescription = "";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", productName=" + productName +
                ", productPrice='" + productPrice + '\'' +
                ", thumbnailImage='" + thumbnailImage + '\'' +
                ", detailImage='" + detailImage + '\'' +
                ", productDescription=" + productDescription +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
