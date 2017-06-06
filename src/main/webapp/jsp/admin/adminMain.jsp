<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<%@ page language="java" pageEncoding="UTF-8"%>
	<%@ include file="../common/base.jsp" %>
	<meta charset="utf-8">
	<title>欢迎来到大东亚共荣圈</title>
	<link rel="stylesheet" media="screen" href="../static/css/style.css">
	<style type="text/css">
		.aaa{
			font: 900 8em helvetica neue;
			color:darkred;
			text-shadow: #fff 0 1px 0;
			text-align: center;
			-webkit-animation: 2s headline_appear_animation;
			-moz-animation: 2s headline_appear_animation;
		}
		.bbb{
			position: absolute;
			top: 100px;
		}
		.contentMain {
			position: absolute;
			left: 400px;
			top: 400px;
			width: 373px;
			height: 23px;
			z-index: 2;
			margin: auto;
			padding: 50px 40px 40px 40px;
			width: 570px;
			height:200px;

			color: #fff;
			-webkit-animation: 2s contentappear;
			-moz-animation: 2s contentappear;
			background-image: -moz-linear-gradient(top, rgba(85, 85, 85, 0.7), rgba(0, 0, 0, 1));
			background-image: -o-linear-gradient(top, rgba(85, 85, 85, 0.7), rgba(0, 0, 0, 1));
			background-image: -webkit-linear-gradient(top, rgba(85, 85, 85, 0.7), rgba(0, 0, 0, 1));
			background-image: linear-gradient(top, rgba(85, 85, 85, 0.7), rgba(0, 0, 0, 1));
			border: 1px solid #000;
			box-shadow: inset 0 1px rgba(255, 255, 255, 0.4),
			0 3px 8px #000000;
			border-radius: 6px;

			line-height: 25px;
			font-weight: 300;
			text-shadow: #000 0 1px 0;
		}

		.contentMain h2 {
			text-transform: uppercase;
			text-align: center;
			padding-bottom: 20px;
		}
	</style>
</head>
<body>
<div class="bbb">
<p class="aaa">欢迎来到大东亚共荣圈</p>
</div>

<div class="contentMain">
	<h2 style="color: gold ">请您登陆入圈 O(∩_∩)O~</h2>

	<div class="login-wrapper">
		<div class="form-wrap">
			<p class="cTitle">用户登录</p>
			<form id="loginForm" method="post" th:action="@{/login}">
				<div class="username inp-wrap">
					<label for="userName">用户名</label>
					<div class="txt-wrapper clearfix">
						<span class="login-img"><i></i></span>
						<span class="login-inp login-name"><input id="userName" type="text" name="name_key" placeholder="请输入用户名"/></span>
					</div>
				</div>
				<div class="password inp-wrap">
					<label for="password">密码</label>
					<div class="txt-wrapper clearfix">
						<span class="login-img"><i></i></span>
						<span class="login-inp login-pass"><input id="password" type="password" name="pwd_key" placeholder="请输入密码"/></span>
					</div>
				</div>
				<div class="inp-wrap rem-wrap clearfix">
					<div class="fl clearfix">
						<a href="javascript:;" onclick="chooseCheckbox(this);" class=""></a>
						<span class="rem-txt">记住密码</span>
					</div>
					<span class="fr tips"></span>
				</div>
				<div class="inp-wrap sub-wrapper">
					<input type="button" value="登&nbsp;录" id="login" onclick="loginFun();"/>
				</div>
			</form>
		</div>
	</div>

</div>

</body>
</html>
