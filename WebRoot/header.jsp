<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="./assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="./assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="./assets/lib/font-awesome/css/font-awesome.css" />
<script type="text/javascript" src="./assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="./assets/js/other.js"></script>
<script type="text/javascript" src="./assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="./assets/lib/bootstrap/js/bootstrap.js"></script>
<title>头部页面</title>
<style type="text/css">
	.brand {
		font-family: georgia, serif;
	}
	.brand .first {
		color: #fff;
        font-weight: bold;

	}
</style>
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">
				<li id="fat-menu" class="dropdown">
					<a href="<%=request.getContextPath() %>/updateMyInfo?userId=${sessionScope.user.userId}" class="dropdown-toggle" target="main">				
						&nbsp;&nbsp;&nbsp;<i class="icon-user"></i>${sessionScope.user.userName}
					</a>
				</li>
				<li id="fat-menu" class="dropdown">
					<a href="<%=request.getContextPath()%>/user_add.jsp" class="dropdown-toggle" target="_top">
					注册账号
					</a>
				</li>
				<li id="fat-menu" class="dropdown">
					<a href="<%=request.getContextPath()%>/logOut" class="dropdown-toggle" target="_top">
						退出系统
					</a>
				</li>
			</ul>
			<a class="brand" href="#"><span class="first">公司俱乐部管理</span></a>
		</div>
	</div>
</body>
</html>