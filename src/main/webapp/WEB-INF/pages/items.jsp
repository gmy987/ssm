<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/5
  Time: 上午12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${username!=null}">
  当前用户:${username}
  <a href="/user/logout">退出登录</a><br>
</c:if>
<a href="/items/editItemsBatch.action">批量修改商品</a>
<form action="${pageContext.request.contextPath }/items/queryItems.action" method="post" name="itemsForm">
  查询条件：
  <table width="100%" border=1>
    <tr>
      <td>商品名称: <input name="itemsCustom.name"/>
          商品类型:
        <select name="itemsType">
          <c:forEach items="${itemsType}" var="itemType">
            <option value="${itemType.key}">${itemType.value}</option>
          </c:forEach>
        </select>
      </td>
      <td><input type="button" value="查询" onclick="queryItems()"/></td>
      <td><input type="button" value="批量删除" onclick="deleteItems()"/></td>
    </tr>
  </table>
  商品列表：
  <table width="100%" border=1>
    <tr>
      <td>选择</td>
      <td>商品名称</td>
      <td>商品价格</td>
      <td>生产日期</td>
      <td>商品描述</td>
      <td>操作</td>
    </tr>
    <c:forEach items="${itemsList }" var="item">
      <tr>
        <td><input type="checkbox" name="itemsIds" value="${item.id}"></td>
        <td>${item.name }</td>
        <td>${item.price }</td>
        <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${item.detail }</td>

        <td><a href="${pageContext.request.contextPath }/items/editItems/${item.id}">修改</a></td>

      </tr>
    </c:forEach>

  </table>
</form>
<%--Hello World!--%>
</body>
<script type="text/javascript">
  function deleteItems() {
    //提交form
    document.itemsForm.action = "/items/deleteItems.action";
    document.itemsForm.submit();
  }
  function queryItems() {
    //提交form
    document.itemsForm.action = "/items/queryItems.action";
    document.itemsForm.submit();
  }
</script>

</html>
