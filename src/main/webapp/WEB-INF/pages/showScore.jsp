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
    <script src="/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(getScore(14152));
        function getScore(semester) {
            var totalScore = 0;
            var totalCredit = 0;
            var table = $("#table");
            table.empty();
            var title = $("<tr><td>课程名称</td><td>课程类型</td><td>学分</td><td>成绩</td></tr>");
            $.ajax({
                url: '/tju/showScore/'+semester,
                success: function(data) {
                    title.appendTo("#table");
                    for( var i = 0 ; i < data.length ; i++ ){
                        var tr = $("<tr></tr>");
                        var td1 = $("<td></td>");
                        td1.html(data[i].courseName);
                        td1.appendTo(tr);
                        var td2 = $("<td></td>");
                        td2.html(data[i].courseType);
                        td2.appendTo(tr);
                        var td3 = $("<td></td>");
                        td3.html(data[i].credit);
                        td3.appendTo(tr);
                        var td4 = $("<td></td>");
                        td4.html(data[i].score);
                        td4.appendTo(tr);
                        tr.appendTo("#table");
                        if(data[i].courseType=="外语水平") continue;
                        totalScore+=data[i].score*data[i].credit;
                        totalCredit += data[i].credit;
                    }
                    $("#avgScore").html((totalScore / totalCredit).toFixed(3));
                }
            });
        }
    </script>
</head>
<body>
    <c:forEach var="semester" items="${semesterList}">
        <input onclick="getScore(${semester})" type="button" value="${semester}"/>
    </c:forEach>
  <table width="100%" border=1 id="table"></table>
  <div>加权平均分: <span id="avgScore"></span> </div>

</body>
</html>
