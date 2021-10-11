<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZQS
  Date: 2020/6/20
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<html>
<head>
    <title>说说列表页面</title>
    <meta http-equiv="Content-type" content="text/html;charest=UTF-8"/>
</head>
<body>
<div id="moods">
    <b>说说列表：</b>
    <c:forEach items="${moods}" var="mood">
        <br>
        <b>用户：</b><span id="account">${mood.userName}</span><br>
        <b>说说内容：</b><span id="content">${mood.content}</span><br>
        <b>发表时间：</b><span id="publish_time">${mood.publishTime}</span><br>
        <b>点赞数sdd：</b><span id="praise_num">${mood.praiseNum}</span><br>
        <div style="margin-left:350px">
            <a id="praise" href="/mood/${mood.id}/praise?userId=${mood.userId}">常规点赞</a>
            <a id="praise2" href="/mood/${mood.id}/praise4Redis?userId=${mood.userId}">redis存储 点赞</a>
            <a id="praise3" href="/mood/${mood.id}/praise4RedisAsync?userId=${mood.userId}">消息队列-redis存储 点赞</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
