<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/10
  Time: 下午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登录</title>
</head>
<body>
<form action="/user/login.action">
  用户名:<input type="text" name="username"><br>
  密码: <input type="password" name="password"><br>
  <input type="submit" value="登录">
</form>
</body>
</html>
