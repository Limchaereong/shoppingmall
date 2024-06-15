<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 9:36 AM
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

        </div>

    </div>

</div>