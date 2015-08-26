<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/12
  Time: 下午9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/tju/getScore">
  用户名: <input name="uid" type="text"><br>
  密码: <input name="password" type="password"><br>
  验证码: <input type="text" name="validation"><img src="/upload/Kaptcha.jpg"><br>
  <input type="submit" value="登录">
</form>
</body>
</html>
