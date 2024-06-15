<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/7/24
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원탈퇴</title>
    <!-- 여기에 필요한 CSS 파일 링크 또는 스타일 시트를 추가하세요 -->
    <style>
        /* 필요한 스타일 시트를 추가하세요 */
    </style>
</head>
<body>
<div>
    <h1>회원탈퇴 페이지</h1>
    <p>정말로 회원탈퇴를 진행하시겠습니까?</p>
    <!-- 회원탈퇴를 확인할 수 있는 폼 추가 -->
    <form action="/userInfo/withdrawalAction.do" method="post">
        <input type="submit" value="회원탈퇴">
    </form>
    <!-- 회원탈퇴 취소할 수 있는 버튼 추가 -->
    <a href="/index.do">취소</a>
</div>
<!-- 필요한 JavaScript 파일 링크 또는 스크립트를 추가하세요 -->
<script>
    // 필요한 JavaScript 코드를 추가하세요
</script>
</body>
</html>
