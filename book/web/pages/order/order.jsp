<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含 头部页面--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	#main{
		text-align: center;
		margin-top: 18px;
	}
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

    <script type="text/javascript">
		$(function () {
			//给确认收货添加点击事件
			$("a.sign").click(function () {
				return confirm("你确定签收吗？")
			})
		})
	</script>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%--静态包含，登入成功之后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
			<table>
				<tr>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${sessionScope.ordersByUserId}" var="ordersByUserId">
					<tr>
						<td>${ordersByUserId.createTime}</td>
						<td>${ordersByUserId.price}</td>
						<c:if test="${ordersByUserId.status == 0}">
							<td style="color: gray">未发货</td>
						</c:if>
						<c:if test="${ordersByUserId.status == 1}">
							<td style="color: black">已发货</td>

						</c:if>
						<c:if test="${ordersByUserId.status == 2}">
							<td style="color: red">已签收</td>
						</c:if>
						<td><a href="orderServlet?action=orderItemOrderId&orId=${ordersByUserId.orderId}">查看详情</a></td>

						<c:if test="${ordersByUserId.status == 0}">
							<td>请等待发货</td>
						</c:if>

						<c:if test="${ordersByUserId.status == 1}">
							<td>
								<a href="orderServlet?action=confirm&orderId=${ordersByUserId.orderId}&status=2" class="sign">点击签收</a>
							</td>
						</c:if>

						<c:if test="${ordersByUserId.status == 2}">
							<td style="color: red">感谢您的支持</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
	</div>

	<%--静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>