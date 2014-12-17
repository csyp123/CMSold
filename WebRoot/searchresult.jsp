<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/datepicker/css/datepicker.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/validator-0.7.3/jquery.validator.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validator-0.7.3/local/zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/other.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/datepicker/js/bootstrap-datepicker.js"></script>

<title>查询用户</title>
</head>
<body class="simple_body">

<script type="text/javascript">
	if(${not empty errorUserType}){
		alert("${errorUserType}");

		}
</script>
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">查询用户</h1>
		</div>
		<c:forEach items="${resultList }" var="searchDto">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() %>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li class="active">用户信息查询</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">${searchDto.userName}的详细信息</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
						
							<form id="tab" method="post" action="#" autocomplete="off">
							<DIV>
							<div style="float:left;margin: 20px">
							     <label>用户名</label>
								<input type="text" name="userName" id="userName" value="${searchDto.userName}" disabled="disabled"  class="input-xlarge"/>
								<label>所属部门</label>
								<input type="text" name="userPart" id="userPart" value="${searchDto.userPart}" disabled="disabled"  class="input-xlarge"/>
								<label>Email</label>
								<input type="text" name="email" id="email" value="${searchDto.userEmail}" disabled="disabled"  class="input-xlarge"/>
								<label>联系电话</label>
								<input type="text" name="phone" id="phone" value="${searchDto.userPhone}" disabled="disabled"  class="input-xlarge"/>
					        </div>
					        <div style="float:left;margin: 20px">
					        	<c:if test="${empty searchDto.clubName}">
									<label>所属俱乐部</label>
								     <input type="text" name="clubName" id="clubName" value="无" disabled="disabled"  class="input-xlarge"/>
								</c:if>
								<c:if test="${not empty searchDto.clubName}">
								    <label>所属俱乐部</label>
									<input type="text" name="clubName" id="clubName" value="${searchDto.clubName}" disabled="disabled"  class="input-xlarge"/>
								 </c:if>
       								    <c:if test="${not empty searchDto.clubName}">
       								    	 <c:if test="${searchDto.userType==0}">
								     		<label>所担任职位</label>
								     		<input type="text" name="userType" id="userType" value="负责人" disabled="disabled"  class="input-xlarge"/>
											</c:if>
											<c:if test="${searchDto.userType==1}">
								     		<label>所担任职位</label>
								     		<input type="text" name="userType" id="userType" value="普通会员" disabled="disabled"  class="input-xlarge"/>
											</c:if>
											<c:if test="${searchDto.userType==10}">
								     		<label>所担任职位</label>
								     		<input type="text" name="userType" id="userType" value="系统管理员" disabled="disabled"  class="input-xlarge"/>
											</c:if>
								          
										</c:if>
       								  <c:if test="${empty searchDto.clubName}">
										 		<label>所担任职位</label>
								     			<input type="text" name="userType" id="userType" value="无" disabled="disabled"  class="input-xlarge"/>
										 </c:if>
										 <c:if test="${empty searchDto.clubLocation}">
											<label>俱乐部所在地</label>
								     		<input type="text" name="clubName" id="clubName" value="无" disabled="disabled"  class="input-xlarge"/>
										</c:if>
										<c:if test="${not empty searchDto.clubLocation}">
								    		<label>俱乐部所在地</label>
											<input type="text" name="clubName" id="clubName" value="${searchDto.clubLocation}" disabled="disabled"  class="input-xlarge"/>
								 		</c:if>
   							 </c:forEach>
							
							</DIV >
							<DIV style="clear: left;margin-left: 200px">
							    <div class="btn-toolbar">
							        <DIV style="float: left">
							        <button type="button" class="btn btn-primary" onclick="return doApply();"><strong>发送消息</strong></button>
							        </DIV>
									<DIV style="float: left;margin-left: 100px">
									    <a href="<%=request.getContextPath() %>/search.jsp" class="btn btn-primary">返回</a>
									</DIV>
									
									<div class="btn-group"></div>
								</div>
							</DIV>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>