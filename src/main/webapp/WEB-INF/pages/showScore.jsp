<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/16
  Time: 下午12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title></title>
  <script src="/js/jquery-2.1.4.min.js" type="text/javascript"/>
    <script>
        $.ajax({

        });
    </script>

</head>
<body>
    <c:forEach var="semester" items="${semesterList}">
        <a href="/tju/showScore/${semester}">${semester}</a>
    </c:forEach>
  <table width="100%" border=1>
      <tr>
          <td>课程名称</td>
          <td>课程类型</td>
          <td>学分</td>
          <td>成绩</td>
      </tr>
      <c:forEach var="score" items="${scoreList}">
          <tr>
              <td>${score.courseName}</td>
              <td>${score.courseType}</td>
              <td>${score.credit}</td>
              <td>${score.score}</td>
          </tr>
      </c:forEach>
  </table>
  <div>加权平均分: <fmt:formatNumber maxFractionDigits="4" value="${avgScore}"/> </div>

</body>
</html>
