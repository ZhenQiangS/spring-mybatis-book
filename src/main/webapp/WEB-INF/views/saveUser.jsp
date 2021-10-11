<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/6/2
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
</head>
<body>
<form method="post" action="/user/insert">
    <table>
        <tr>
            <td>姓名：</td>
            <td><input id="name" name="name" type="text"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input id="password" name="password" type="text"></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><input id="age" name="age" type="text"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>

    </table>
</form>
</body>
</html>
