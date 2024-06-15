<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<html>
<head>
    <title>장바구니</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .empty-cart {
            text-align: center;
            margin-top: 20px;
            color: #888;
        }
        .product-image {
            max-width: 100px;
            max-height: 100px;
        }
        .delete-btn {
            color: red;
            cursor: pointer;
        }
        .order-btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .order-btn:hover {
            background-color: #45a049;
        }
        .total-price {
            margin-top: 20px;
            text-align: right;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>장바구니</h1>

    <c:if test="${not empty sessionScope.cart}">
        <table>
            <thead>
            <tr>
                <th>상품 이미지</th>
                <th>상품 ID</th>
                <th>상품 이름</th>
                <th>상품 설명</th>
                <th>상품 가격</th>
                <th>상품 삭제</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="totalPrice" value="0" />
            <c:forEach items="${sessionScope.cart}" var="product">
                <tr>
                    <td><img src="${product.thumbnailImage}" alt="상품 이미지" class="product-image"></td>
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.productDescription}</td>
                    <td>${product.productPrice}</td>
                    <td>
                        <form id="deleteForm_${product.productId}" action="/DeleteProductInCartAction.do" method="post">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <span class="delete-btn" onclick="deleteProduct(${product.productId})">삭제</span>
                        </form>
                    </td>
                </tr>
                <c:set var="totalPrice" value="${totalPrice + product.productPrice}" />
            </c:forEach>
            </tbody>
        </table>

        <!-- Order button -->
        <form action="/OrderForm.do" method="post">
            <button type="submit" class="order-btn">주문</button>
        </form>

        <!-- Total price -->
        <div class="total-price">
            총 주문 금액: ${totalPrice}원
        </div>

    </c:if>
    <c:if test="${empty sessionScope.cart}">
        <p class="empty-cart">장바구니가 비어 있습니다.</p>
    </c:if>
</div>

<script>
    function deleteProduct(productId) {
        if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
            // 삭제할 상품의 폼을 찾아서 제출합니다.
            document.getElementById("deleteForm_" + productId).submit();
        }
    }
</script>

</body>
</html>
