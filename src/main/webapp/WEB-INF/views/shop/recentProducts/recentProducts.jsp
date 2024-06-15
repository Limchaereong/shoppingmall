<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<%
    // 최근 본 상품 목록을 세션에서 가져옴
    List<Product> recentProducts = (List<Product>) session.getAttribute("recentProducts");
    if (recentProducts == null) {
        recentProducts = new ArrayList<>();
    }

    // 상품 상세 페이지로부터 전달받은 상품 정보를 최근 본 상품 목록에 추가
    Product viewedProduct = (Product) request.getAttribute("product");
    if (viewedProduct != null) {
        // 최근 본 상품 목록에 해당 상품이 이미 있는지 확인 후, 중복되지 않도록 추가
        if (!recentProducts.contains(viewedProduct)) {
            recentProducts.add(0, viewedProduct); // 최근 본 상품 목록의 가장 앞에 추가
            // 최근 본 상품 목록을 세션에 다시 설정
            session.setAttribute("recentProducts", recentProducts);
        }
    }
%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>최근 본 상품</h2>
            <div class="row row-cols-1 row-cols-md-3 g-3">
                <c:forEach items="${recentProducts}" var="product">
                    <div class="col">
                        <div class="card shadow-sm">
                            <img src="${product.thumbnailImage}" class="bd-placeholder-img card-img-top" width="100%" height="225" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false" alt="상품 썸네일"/>
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">${product.productDescription}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">${product.productPrice}원</small>
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
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${empty recentProducts}">
                <p>최근에 본 상품이 없습니다.</p>
            </c:if>
        </div>
    </div>
</div>
