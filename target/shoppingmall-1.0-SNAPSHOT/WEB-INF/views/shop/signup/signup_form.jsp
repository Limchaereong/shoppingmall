<%--
  Created by IntelliJ IDEA.
  User: imchaelyeong
  Date: 5/3/24
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">아이디 입력</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="회원 이름" required>
                <label for="user_name">이름 입력</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생년월일" required>
                <label for="user_birth">생년월일 입력 (예: 19900101)</label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="user_auth" id="role_user" value="ROLE_USER" checked>
                <label class="form-check-label" for="role_user">일반 사용자</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="user_auth" id="role_admin" value="ROLE_ADMIN">
                <label class="form-check-label" for="role_admin">관리자</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">회원가입</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>