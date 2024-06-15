<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="/SelectCategoryAction.do" method="get">
                <select name="categoryId" onchange="this.form.submit()">
                    <option value="">카테고리 선택</option>
                    <option value="1">카테고리 1 - 의류</option>
                    <option value="2">카테고리 2 - 신발</option>
                    <option value="3">카테고리 3 - 가방</option>
                    <option value="4">카테고리 4 - 남성의류</option>
                    <option value="5">카테고리 5 - 여성의류</option>
                    <option value="6">카테고리 6 - 스니커즈</option>
                    <option value="7">카테고리 7 - 부츠</option>
                    <option value="8">카테고리 8 - 클러치</option>
                    <option value="9">카테고리 9 - 숄더백</option>
                    <!-- 다른 카테고리 옵션 추가 -->
                </select>
            </form>
            <div class="row row-cols-1 row-cols-md-3 g-3">
                <c:if test="${not empty sessionScope.list}">
                    <c:forEach items="${sessionScope.list}" var="product">
                        <div class="col">
                            <div class="card shadow-sm">
                                <img src="${product.thumbnailImage}" class="bd-placeholder-img card-img-top" width="100%" height="225" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false" alt="상품 썸네일"/>
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.productDescription}</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <small class="text-muted">${product.productPrice}원</small>
                                        <form action="/AddMainToCartAction.do" method="post">
                                            <input type="hidden" name="productId" value="${product.productId}">
                                            <input type="hidden" name="productName" value="${product.productName}">
                                            <input type="hidden" name="productDescription" value="${product.productDescription}">
                                            <input type="hidden" name="productPrice" value="${product.productPrice}">
                                            <input type="hidden" name="thumbnailImage" value="${product.thumbnailImage}">
                                            <button type="submit" class="btn btn-sm btn-primary">담기</button>
                                        </form>
                                    </div>
                                </div>
                                <!-- productId를 숨겨서 JavaScript에서 사용할 수 있도록 함 -->
                                <input type="hidden" name="productId" value="${product.productId}">
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty sessionScope.list}">
                    <p>상품이 없습니다.</p>
                </c:if>
            </div>
            <c:if test="${sessionScope.totalPage > 1}">
                <div class="row mt-3">
                    <div class="col text-center">
                        <c:if test="${sessionScope.currentPage > 1}">
                            <a href="index.do?page=${sessionScope.currentPage - 1}" class="btn btn-primary">이전</a>
                        </c:if>
                        <span class="mx-2">${sessionScope.currentPage}</span>
                        <c:if test="${sessionScope.currentPage < sessionScope.totalPage}">
                            <a href="index.do?page=${sessionScope.currentPage + 1}" class="btn btn-primary">다음</a>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- JavaScript 코드 추가 -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // 각 상품 카드에 클릭 이벤트 추가
        var productCards = document.querySelectorAll('.card');
        productCards.forEach(function(card) {
            card.addEventListener('click', function() {
                // 클릭된 상품의 productId 가져오기
                var productId = card.querySelector('input[name="productId"]').value;
                // 자세히 보기 페이지 URL 생성
                var detailPageUrl = '/productDetail.do?productId=' + productId;
                // 자세히 보기 페이지로 이동
                window.location.href = detailPageUrl;
            });
        });
    });
</script>
