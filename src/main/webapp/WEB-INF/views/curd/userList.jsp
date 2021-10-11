<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/7/25
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/user2/addUser">新增</a>
<table id="table" style="text-align: center;padding: 20px;margin: 20px;">

</table>
<script>
    window.onload = function () {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open("get", "/userInterface/allUser", true);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                let data = JSON.parse(xmlHttp.responseText);
                console.log(data)
                let str = "";

                for (let i = 0; i < data.length; i++) {
                    str += "<tr>";
                    let d = data[i];
                    str += "<td>" + d.id + "</td>";
                    str += "<td>" + d.name + "</td>";
                    str += "<td>" + d.age + "</td>";
                    str += "<td>" + d.address + "</td>";
                    str += "<td>" + "<a href=/userInterface/deleteUser/" + d.id + ">删除" + "</a>"
                        + "<a href=updateUser/" + d.id + ">修改" + "</a>" + "</td>";
                    str += "</tr>"
                }
                document.getElementById("table").innerHTML = str;
            }
        }
        xmlHttp.send();
    }
</script>
</body>
</html>
