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
            </ul>
        </div>

        <div class="col-md-9">
            <h2>상품 삭제 </h2>
            <form method="post" action="/admin/deleteProductAction.do">
                <label for="product_id">상품ID:</label>
                <input type="text" class="form-control" id="product_id" name="product_id" placeholder="삭제할 상품 ID 입력" required>
                <br>
                <button type="submit" class="btn btn-primary">상품 삭제</button>
            </form>

        </div>

        <br>
        <c:if test="${requestScope.successMessage == true}">
            <div class="alert alert-success">
                <strong>성공!</strong> 상품이 성공적으로 삭제되었습니다.
            </div>
        </c:if>

        <c:if test="${requestScope.errorMessage == true}">
            <div class="alert alert-danger">
                <strong>오류!</strong> 상품을 찾을 수 없습니다.
            </div>
        </c:if>


    </div>

</div>
