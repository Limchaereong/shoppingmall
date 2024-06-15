<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <li class="nav-item">
                    <p style="margin-top: 20px;">카테고리 숫자 설명</p>
                    <p style="font-size: 14px;">카테고리 숫자 입력시 참고<br>1 - 의류<br>2 - 신발<br>3 - 가방<br>4 - 남성의류<br>5 - 여성의류<br>6 - 스니커즈<br>7 - 부츠<br>8 - 클러치<br>9 - 숄더백</p>
                </li>
            </ul>
        </div>

        <div class="col-md-9">
            <form method="post" action="/admin/addProductAction.do">
                <h2>상품 등록 </h2>

                <div class="form-group">
                    <label for="category_id">카테고리:</label>
                    <input type="number" class="form-control" id="category_id" name="category_id" placeholder="카테고리 숫자 입력(1 ~ 5)" required>
                    </select>
                </div>
                <div class="form-group">
                    <label for="product_name">상품이름:</label>
                    <input type="text" class="form-control" id="product_name" name="product_name" placeholder="상품이름 입력" required>
                </div>
                <div class="form-group">
                    <label for="product_price">상품가격:</label>
                    <input type="text" class="form-control" id="product_price" name="product_price" placeholder="상품 가격 입력" required>
                </div>
                <div class="form-group">
                    <label for="thumbnail_image">상품썸네일:</label>
                    <input type="url" class="form-control" id="thumbnail_image" name="thumbnail_image" placeholder="썸네일 이미지 URL 입력" required>
                </div>
                <div class="form-group">
                    <label for="detail_image">상품상세이미지:</label>
                    <input type="url" class="form-control" id="detail_image" name="detail_image" placeholder="상품 상세 이미지 URL 입력" required>
                </div>
                <div class="form-group">
                    <label for="product_description">상품설명:</label>
                    <input type="text" class="form-control" id="product_description" name="product_description" placeholder="상품 설명 입력" required>
                </div>
                <div class="form-group">
                    <label for="created_at">상품 등록 날짜:</label>
                    <input type="text" class="form-control" id="created_at" name="created_at" placeholder="상품 등록 날짜 입력 ex -> (YYYY-MM-DDTHH:mm:ss) " required>
                </div>
                <div class="form-group">
                    <label for="updated_at">상품 수정 날짜:</label>
                    <input type="text" class="form-control" id="updated_at" name="updated_at" placeholder="상품 수정 날짜 입력 (최초 등록시 등록날짜 입력) ex -> (YYYY-MM-DDTHH:mm:ss)" required>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">상품 등록</button>
            </form>

        </div>

        <br>

        <c:if test="${requestScope.successMessage == true}">
            <div class="alert alert-success">
                <strong>성공!</strong> 상품이 성공적으로 등록되었습니다.
            </div>
        </c:if>

        <c:if test="${requestScope.errorMessage == true}">
            <div class="alert alert-danger">
                <strong>오류!</strong> 상품이 등록되지 않았습니다.
            </div>
        </c:if>

    </div>

</div>