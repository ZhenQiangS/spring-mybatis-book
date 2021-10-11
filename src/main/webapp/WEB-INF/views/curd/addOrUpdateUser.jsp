<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/7/25
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${url}" method="${method}" enctype="multipart/form-data">
    <input name="id" id="id" type="hidden" value="${user.id}">
    <label>姓名：</label> <input id="name" name="name" value="${user.name}"><br>
    <label>年龄</label> <input id="age" name="age" value="${user.age}"><br>
    <input type="submit" value="${buttonStr}">
    <input type="button" value="提交" onclick="ss()">
</form>
<script type="text/javascript">
    function ss() {
        let data = {
            name: document.getElementById("name").value,
            age: document.getElementById("age").value,
        };
        let xhr = new XMLHttpRequest();
        xhr.open("post", "/userInterface/addUser")
        xhr.setRequestHeader("content-type", 'application/json')
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                debugger
                //根据服务器的响应内容格式处理响应结果
                if (xhr.getResponseHeader('content-type') === 'application/json') {
                    let result = JSON.parse(xhr.responseText);
                    //根据返回结果判断验证码是否正确
                    if (result.code === -1) {
                        alert('验证码错误');
                    }
                } else {
                    console.log(xhr.responseText);
                }
            }
        }
        xhr.send(JSON.stringify(data))
    }
</script>
</body>
</html>
