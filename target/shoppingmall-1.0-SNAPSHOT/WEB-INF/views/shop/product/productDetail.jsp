<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .product-detail {
        display: flex;
        align-items: center; /* 수직 중앙 정렬 */
    }

    .product-info {
        flex-grow: 1; /* 나머지 공간을 최대한 확장 */
        padding-left: 40px; /* 텍스트와 이미지 사이 간격을 위한 패딩 추가 */
        text-align-all: start;
    }

    /* 담기 버튼 스타일 */
    .btn-add-to-cart {
        flex-shrink: 0; /* 크기가 줄어들지 않도록 설정 */
        margin-top: 10px; /* 위쪽 여백 추가 */
    }
</style>

<h1>상품 상세</h1>

<div class="product-detail">
    <div class="product-image">
        <img src="${product.detailImage}" alt="상품 이미지">
    </div>
    <div class="product-info">
        <h1 class="product-name">${product.productName}</h1>
        <p class="product-description">${product.productDescription}</p>
        <br>
        <h3 class="product-price">${product.productPrice.toPlainString()}원</h3>
        <p class="product-description">상품 번호: ${product.productId}</p>
        <form action="/AddMainToCartAction.do" method="post" class="d-flex justify-content-between align-items-center">
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="productName" value="${product.productName}">
            <input type="hidden" name="productDescription" value="${product.productDescription}">
            <input type="hidden" name="productPrice" value="${product.productPrice}">
            <input type="hidden" name="thumbnailImage" value="${product.thumbnailImage}">
            <button type="submit" class="btn btn-sm btn-primary btn-add-to-cart">담기</button>
        </form>
    </div>
</div>
