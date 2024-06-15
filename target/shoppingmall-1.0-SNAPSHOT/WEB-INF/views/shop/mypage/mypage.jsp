<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 > 마이 페이지 </h1>
<div class="row">
    <div class="col-md-4">
        <div class="list-group">
            <a href="/userInfo/userinfo.do" class="list-group-item">회원정보 확인 및 회원정보 수정</a>
        </div>
    </div>
    <div class="col-md-4">
        <div class="list-group">
            <a href="/userInfo/address.do" class="list-group-item">회원 주소 등록</a>
        </div>
    </div>
    <div class="col-md-4">
        <div class="list-group">
            <a href="/userInfo/withDrawal.do" class="list-group-item">회원탈퇴</a>
        </div>
    </div>
    <div class="col-md-4">
        <div class="list-group">
            <a href="/userInfo/pointHistory.do" class="list-group-item">포인트사용및적립 내역</a>
        </div>
    </div>
    <c:if test="${sessionScope.user != null && sessionScope.user.userAuth == 'ROLE_ADMIN'}">
        <div class="col-md-4">
            <div class="list-group">
                <a href="/admin/adminPage.do" class="list-group-item">관리자 페이지 접속</a>
            </div>
        </div>
    </c:if>
</div>