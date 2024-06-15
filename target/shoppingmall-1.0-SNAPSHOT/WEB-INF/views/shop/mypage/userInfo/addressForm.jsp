<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Address Form</title>
    <script>
        // 저장이 완료되었을 때 팝업 창을 띄우는 함수
        function showSaveSuccess() {
            alert('저장이 완료되었습니다!');
        }

        // 삭제가 완료되었을 때 팝업 창을 띄우는 함수
        function showDeleteSuccess() {
            alert('삭제가 완료되었습니다!');
        }
    </script>
    <style>
        .container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .form-container {
            width: 45%;
        }
        .address-container {
            width: 45%;
        }
        .delete-container {
            margin-top: 20px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2>주소 등록</h2>
        <form action="/userInfo/addressSave.do" method="post" onsubmit="showSaveSuccess()">
            <!-- 세션에 저장된 사용자 ID를 hidden input 필드에 설정 -->
            <input type="hidden" name="user_id" value="${sessionScope.userId}">
            <label for="zip_code">우편번호:</label>
            <input type="text" id="zip_code" name="zip_code" value="${address.zipCode}">
            <br>
            <label for="address">주소:</label>
            <input type="text" id="address" name="address" value="${address.address}">
            <br>
            <label for="address_detail">상세주소:</label>
            <input type="text" id="address_detail" name="address_detail" value="${address.addressDetail}">
            <br>
            <input type="submit" value="저장">
        </form>
    </div>
    <div class="address-container">
        <h2>주소 목록</h2>
        <table>
            <thead>
            <tr>
                <th>우편번호</th>
                <th>주소</th>
                <th>상세주소</th>
            </tr>
            </thead>
            <tbody>
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
</div>

<div class="delete-container">
    <h2>주소 삭제</h2>
    <form action="/userInfo/addressDelete.do" method="post" onsubmit="showDeleteSuccess()">
        <!-- 삭제하려는 사용자의 userId를 입력받는 hidden input 필드 -->
        <input type="text" name="user_id" value="${sessionScope.userId}">
        <!-- 삭제 버튼 -->
        <input type="submit" value="삭제할 유저아이디 입력">
    </form>
</div>

</body>
</html>
