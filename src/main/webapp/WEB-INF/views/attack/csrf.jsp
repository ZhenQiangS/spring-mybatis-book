<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/7/22
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>CSRF 攻击页面</h1>
<form action="/attack/csrfSubmitByPost" method="post">
    <h2>带token</h2>
    <input type="hidden" name="identifier_token" value="${identifier_token}">
    <input value="hahaha">
    <input type="submit" value="提交">
</form>

<form action="/attack/csrfSubmitByPost2" method="post">
    <h2>不带token</h2>
    <input value="hahaha">
    <input type="submit" value="提交">
</form>
</body>
</html>
