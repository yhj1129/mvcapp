<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h1>[로그인]</h1>
<hr/>
<form action="/user/login.do" method="post">
    <input type="text" name="username" placeholder="Enter username" /><br/>
    <input type="text" name="password" placeholder="Enter password" /><br/>
    <button type="submit">로그인 완료</button>
</form>
</body>
</html>