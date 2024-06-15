<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container my-5 border p-3 rounded shadow">
    <h2 class="text-center mb-4">유저 정보</h2>

    <div class="row text-center">
        <div class="col-md-6 mx-auto">
            <div class="mb-3">
                <strong>아이디:</strong> ${sessionScope.user.userId}
            </div>
            <div class="mb-3">
                <strong>이름:</strong> ${sessionScope.user.userName}
            </div>
            <div class="mb-3">
                <strong>생년월일:</strong> ${sessionScope.user.userBirth}
            </div>
            <div class="mb-3">
                <strong>권한:</strong> ${sessionScope.user.userAuth}
            </div>
            <div class="mb-3">
                <strong>포인트:</strong> ${sessionScope.user.userPoint}
            </div>
            <div class="mb-3">
                <strong>가입일:</strong> ${sessionScope.user.createdAt}
            </div>
            <div class="mb-3">
                <strong>최근 접속일:</strong> ${sessionScope.user.latestLoginAt}
            </div>
        </div>
        <a class="btn btn-warning" href="/userInfo/update.do">정보 수정</a>
    </div>
    <div>

    </div>
</div>