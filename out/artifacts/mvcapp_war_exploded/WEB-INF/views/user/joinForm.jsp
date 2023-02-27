<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h1>[회원가입]</h1>
<hr/>
<form action="/user/join.do" method="post">
    <input type="text" name="username" placeholder="Enter username" /><br/>
    <input type="text" name="password" placeholder="Enter password" /><br/>
    <input type="text" name="email" placeholder="Enter e-mail" /><br/>
    <button type="submit">회원가입 완료</button>
</form>
</body>
</html>