<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>购物车</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<style>
		#main{
			text-align: center;
			margin-top: 18px;
		}
	</style>
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">购物车</span>

	<%--静态包含，登录 成功之后的菜单 --%>
	<%@ include file="/pages/common/login_success_menu.jsp"%>

	<script type="text/javascript">
		$(function () {
			//给删除绑定点击事件
			$("a.delete").click(function () {
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
			})

			//给清空购物车添加点击事件
			$("a.clearClass").click(function () {
				return confirm("你确定要清空购物车吗？")
			})
			//数量添加修改事件
			$(".countClass").change(function () {
				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				var id = $(this).attr('bookId');

				if (confirm("你确定要将【"+name+"】的商品数量修改为"+count+"吗？")){
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count=" +count+"&id="+id;
				} else {
					this.value = this.defaultValue;
				}
			})
		})
	</script>

</div>

<div id="main" >

	<table>
		<tr>
			<td>商品名称</td>
			<td>数量</td>
			<td>单价</td>
			<td>金额</td>
			<td>操作</td>
		</tr>
		<c:if test="${empty sessionScope.cart.items}">
			<%--//如果购物车为空--%>
			<tr>
				<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a></td>
			</tr>
		</c:if>

		<c:if test="${not empty sessionScope.cart}">
		<%--	购物车不为空的话--%>
			<c:forEach items="${sessionScope.cart.items}" var="cart">
				<tr>
					<td>${cart.value.name}</td>
					<td>
						<input type="text" bookId = "${cart.value.id}" class="countClass" value="${cart.value.count}" style="width: 80px">
					</td>
					<td>${cart.value.price}</td>
					<td>${cart.value.totalPrice}</td>
					<td><a href="cartServlet?action=deleteItem&id=${cart.value.id}" class="delete">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>


	<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear" class="clearClass">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
	</c:if>


</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>