<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/9
  Time: 下午11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json交互的测试</title>
  <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript">
    //请求的是json,输出的是json
    function requestJson() {
      $.ajax({
        type: 'post',
        url: '/requestJson.action',
        contentType: 'application/json;charset=utf-8',
        //数据格式是json串
        data: '{"name":"手机","price":999}',
        success: function (data) {//返回json结果
          var resultObj = $("#result");
          resultObj.html(data.name);
        }
      });
    }
    //请求的是key/value,输出的是json
    function responseJson() {
      $.ajax({
        type: 'post',
        url: '/responseJson.action',
        //不需要再指定contentType
        //数据格式是json串
        data: 'name=手机&price=999',
        success: function (data) {//返回json结果
          alert(data.name)
        }
      });
    }
  </script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求的是json,输出的是json">
<input type="button" onclick="responseJson()" value="请求的是key/value,输出的是json">
<div id="result"></div>
</body>
</html>
