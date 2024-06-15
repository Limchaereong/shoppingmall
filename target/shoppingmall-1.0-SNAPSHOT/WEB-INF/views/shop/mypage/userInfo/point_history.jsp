<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Point History</title>
</head>
<body>
<h2>Point History</h2>
<table border="1">
    <tr>
        <th>유저 아이디</th>
        <th>&nbsp;&nbsp;&nbsp;&nbsp;포인트 적립 및 사용&nbsp;&nbsp;&nbsp;&nbsp;</th>
        <th>포인트 양</th>
        <th>포인트 내역 일시</th>
    </tr>
    <c:forEach var="pointHistory" items="${pointHistoryList}">
        <tr>
            <td>${pointHistory.userId}</td>
            <td>${pointHistory.pointAction}</td>
            <td>${pointHistory.pointAmount}</td>
            <td>${pointHistory.pointHistoryDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
