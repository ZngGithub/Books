<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含 头部页面--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//页面加载完执行
		$(function () {

			//给用户名添加失去焦点事件
			$("#username").blur(function () {
				var username = this.value;
				$.getJSON("http://localhost:8080/book/userServlet","action=ajaxUsername&username="+username,function (data) {
					if (data.exists){
						$("span.errorMsg").text("用户名已存在")
					} else {
						$("span.errorMsg").text("用户名可用")
					}
				})
			})

			//给验证码图片添加点击事件
			$("#code_img").click(function () {
				//点击更换地址
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

			//给注册添加点击事件
			$("#sub_btn").click(function () {
				//获取用户名的内容
				var username = $("#username").val();
				//用户名长度5-12。 /^\w{5,12}$/;
				var regExp = /^\w{5,12}$/;
				if (!regExp.test(username)){
					$(".errorMsg").text("输入的用户名不合法");
					return false;
				}
				$(".errorMsg").text("");

				//获取密码的内容
				var password = $("#password").val();
				//正则表达式,密码至少包含数字和字母
				var regExp01 = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,15}$/
				if (!regExp01.test(password)){ //判断
					$(".errorMsg").text("输入的密码不合法");
					return false;
				}
				//获取确认密码内容和密码内容
				var repwd = $("#repwd").val();
				if (repwd != password){
					$(".errorMsg").text("输入的密码不一致");
					return false
				}
				//获取电子邮箱的内容
				var email = $("#email").val();
				var emailExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if (!emailExp.test(email)){
					$(".errorMsg").text("输入的邮箱不合法");
					return false
				}
				//获取验证码的内容
				var code = $("#code").val();
				code = $.trim(code);
				if (code == "" || code.length == null ){
					$(".errorMsg").text("验证码错误");
					return false;
				}

			})
		/*	$("#code").keydown(function (y) {
				if (y.keyCode == 13){
					$(".errorMsg").text("登入,正在进行身份认证....");
				}
			})*/


		})
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.username}
									${requestScope.code}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value= "${requestScope.name}"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   value="abc2333" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   value="abc2333" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 100px;"  id="code" value=""/>
									<img id="code_img" alt="" src="http://localhost:8080/book/kaptcha.jpg" style="float: right; width: 120px; height: 38px; margin-right: 40px" >
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>