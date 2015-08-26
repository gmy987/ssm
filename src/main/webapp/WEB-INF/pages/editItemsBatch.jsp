<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 15/8/6
  Time: 下午11:17
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
<form action="${pageContext.request.contextPath }/items/queryItems.action" method="post" name="itemsForm">
  查询条件：
  <table width="100%" border=1>
    <tr>
      <td>商品名称: <input name="itemsCustom.name"/></td>
      <td><input type="button" value="查询" onclick="queryItems()"/></td>
      <td><input type="button" value="批量修改商品提交" onclick="updateItems()"/></td>
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
    <c:forEach items="${itemsList }" var="item" varStatus="status">
      <tr>
        <td><input type="checkbox" name="itemsIds" value="${item.id}"><input type="hidden" name="itemsCustomList[${status.index}].id" value="${item.id}"></td>
        <td><input type="text" name="itemsCustomList[${status.index}].name" value="${item.name}"></td>
        <td><input type="text" name="itemsCustomList[${status.index}].price" value="${item.price}"></td>
        <td><input type="text" name="itemsCustomList[${status.index}].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
        <td><input type="text" name="itemsCustomList[${status.index}].detail" value="${item.detail}"></td>

        <td><a href="${pageContext.request.contextPath }/items/editItems?id=${item.id}">修改</a></td>

      </tr>
    </c:forEach>

  </table>
</form>
<%--Hello World!--%>
</body>
<script type="text/javascript">
  function updateItems() {
    //提交form
    document.itemsForm.action = "/items/editItemsBatchSubmit.action";
    document.itemsForm.submit();
  }
  function queryItems() {
    //提交form
    document.itemsForm.action = "/items/queryItems.action";
    document.itemsForm.submit();
  }
</script>

</html>

