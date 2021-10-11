<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/7/21
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>xss攻击</h1>
<form action="/attack/xssSubmitByGet">

    <h1>get方式</h1>
    <input name="value" value="<script>alert('如有弹框表示攻击成功')</script>">
    <input type="submit" 提交>
</form>
<form action="xssSubmitByPost" method="post">
    <h1>post方式</h1>
    <input name="value" value="<script>alert('如有弹框表示攻击成功')</script>">
    <input type="submit" 提交>
</form>
<form action="/attack/SubmitByPost" method="post">
    <h1>非过滤方法(post)</h1>
    <input name="value" value="<script>alert('如有弹框表示攻击成功')</script>">
    <input type="submit" 提交>
</form>
</body>
</html>
