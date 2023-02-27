<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>에러</title>
</head>
<body>
<h1>[에러페이지]</h1>
<hr/>
<p>잘못된 요청입니다. 다시 요청해주세요</p>

<br>
<form action="/user/loginForm.do" method="post">
    <button type="submit">로그인 페이지</button>
</form>
<form action="/user/joinForm.do" method="post">
    <button type="submit">회원가입 페이지</button>
</form>
</body>
</html>
