<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<div class="container">

    <div class="row">

        <div class="col-md-3">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="/admin/productList.do">상품 목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/addProduct.do">상품 추가</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/deleteProduct.do">상품 삭제</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">주문 관리</a>
                </li>
            </ul>
        </div>

        <div class="col-md-9">

            <h1>상품 목록</h1>

            <c:if test="${not empty list}">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>상품 ID</th>
                        <th>카테고리 ID</th>
                        <th>상품이름</th>
                        <th>상품가격</th>
                        <th>상품썸네일</th>
                        <th>상품상세이미지</th>
                        <th>상품설명</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.list}" var="product">
                        <tr>
                            <td>${product.productId}</td>
                            <td>${product.categoryId}</td>
                            <td>${product.productName}</td>
                            <td>${product.productPrice}</td>
                            <td><img src="${product.thumbnailImage}" alt="상품 썸네일" style="width: 100px; height: auto;"></td>
                            <td><img src="${product.detailImage}" alt="상품 상세 이미지" style="width: 100px; height: auto;"></td>
                            <td>${product.productDescription}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- 페이징 버튼 추가 -->
                <div class="text-center">
                    <c:if test="${currentPage > 1}">
                        <a href="/admin/productList.do?page=${currentPage - 1}" class="btn btn-primary">이전</a>
                    </c:if>

                    <strong class="mx-2">${currentPage}</strong>

                    <c:if test="${currentPage < totalPages}">
                        <a href="/admin/productList.do?page=${currentPage + 1}" class="btn btn-primary">다음</a>
                    </c:if>
                </div>

            </c:if>

            <c:if test="${empty list}">
                <p class="text-center">상품이 없습니다.</p>
            </c:if>

        </div>

    </div>

</div>
