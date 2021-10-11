<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登入页面</title>
</head>
<body>
<div>
    <form action="/user/submit" method="post">
        <table>
            <tr>
                <th>姓名</th>
                <th><input name="name"></th>
            </tr>
            <tr>
                <th>姓名</th>
                <th><input name="id"></th>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>