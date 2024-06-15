<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.nhnacademy.shoppingmall.entity.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.entity.user.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>주문 페이지</title>
    <script>
        // 주문 완료 시 팝업 표시 함수
        function showOrderConfirmation() {
            alert("주문이 완료되었습니다.");
        }
    </script>
</head>
<body>
<h1>주문 페이지</h1>

<div style="float:right; width:45%; margin-left:5%;">
    <h2>등록된 주소 목록</h2>
    <table border="1">
        <thead>
        <tr>
            <th>우편번호</th>
            <th>주소</th>
            <th>상세주소</th>
        </tr>
        </thead>
        <tbody>
        <%-- 주소 목록 출력 --%>
        <c:forEach var="address" items="${addressList}">
            <tr>
                <td>${address.zipCode}</td>
                <td>${address.address}</td>
                <td>${address.addressDetail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%
    // 총 주문 금액 계산
    BigDecimal totalPrice = new BigDecimal("0");
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (cart != null) {
        for (Product product : cart) {
            BigDecimal productPrice = new BigDecimal(String.valueOf(product.getProductPrice()));
            totalPrice = totalPrice.add(productPrice);
        }
    }

    // 사용자 포인트 가져오기
    User user = (User) session.getAttribute("user");
    int userPoint = user != null ? user.getUserPoint() : 0;
%>

<%-- 총 주문 금액이 있는 경우 표시 --%>
<% if (totalPrice.compareTo(BigDecimal.ZERO) > 0) { %>
<p>총 주문 금액: <%= totalPrice %>원</p>
<% } else { %>
<p>장바구니가 비어 있습니다.</p>
<% } %>

<%-- 사용자 포인트가 있는 경우 표시 --%>
<p>사용자 포인트: <%= userPoint %>점</p>

<form action="/PlaceOrderAction.do" method="post" onsubmit="showOrderConfirmation()">
    <!-- 고객 정보를 입력하는 입력 필드 -->
    <label for="customerName">받으실분 이름:</label>
    <input type="text" id="customerName" name="customerName" required><br><br>

    <label for="customerZipCode">받으실분 집 우편번호(5자리):</label>
    <input type="text" id="customerZipCode" name="customerZipCode" required><br><br>

    <label for="customerAddress">받으실분 집 상세주소:</label>
    <input type="text" id="customerAddress" name="customerAddress" required><br><br>

    <label for="customerPhone">받으실분 전화번호:</label>
    <input type="tel" id="customerPhone" name="customerPhone" required><br><br>

    <!-- 총 주문 금액을 전달하기 위한 숨은 필드 -->
    <input type="hidden" name="totalPrice" value="<%= totalPrice %>">

    <!-- 주문 완료 버튼 -->
    <button type="submit">주문 완료</button>
</form>
</body>
</html>
