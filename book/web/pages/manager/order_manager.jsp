<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含 头部页面--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
      <script type="text/javascript">
		  $(function () {
		  	//发货添加点击事件
		  	$("a.fa").click(function () {
		  		return confirm("你确定要发货用户ID为【"+$(this).parent().parent().find("td:first").text()+"】的商品吗？")
			})
		  })
	  </script>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%--静态包含图书管理页面--%>
			<%@include file="/pages/common/Manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户的ID</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
				<c:forEach items="${sessionScope.orders}" var="orders">
					<tr>
						<td>${orders.userId}</td>
						<td>${orders.createTime}</td>
						<td>${orders.price}</td>
						<td><a href="orderServlet?action=orderItemOrderId&orId=${orders.orderId}">查看详情</a></td>
						<c:if test="${orders.status == 0}">
							<td><a href="orderServlet?action=confirm&orderId=${orders.orderId}&status=1" class="fa">点击发货</a></td>
						</c:if>
						<c:if test="${orders.status == 1}">
							<td>已发货</td>
						</c:if>
						<c:if test="${orders.status == 2}">
							<td style="color: red">客户已签收</td>
						</c:if>
					</tr>
				</c:forEach>
		</table>
	</div>

	<%--静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>