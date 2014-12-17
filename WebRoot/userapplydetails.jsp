<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
<script type="text/javascript">
function cancelapply(applyId){
if(confirm('请确认是否撤销')){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"<%=request.getContextPath()%>/cancelapply?applyId="+applyId,
		success:function(result){
			alert(result.msg);
				location.href="<%=request.getContextPath()%>/myapply?pageIndex=0";
				return true;

}
});
		}else{
		return false;
		}
}
</script>
<title>申请加入</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">申请详细</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() %>/index">首页 </a> <span class="divider">/</span></li>
			<c:forEach items="${applydetails}" var="apply">
			<li class="active">${apply.clubName}</li>
			</c:forEach>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">申请资料</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
							<c:forEach items="${applydetails}" var="apply">
							<DIV>

							<div style="float:left;margin: 20px">
							    <label>用户名</label>
								<input type="text" name="userName" id="userName" value="${apply.userName}" disabled="disabled" class="input-xlarge"/>
								<label>所属部门</label> 
								<input type="text" name="userPart" id="userPart" value="${apply.userPart}" disabled="disabled" class="input-xlarge"/>
								<label>Email</label>
								<input type="text" name="userEmail" id="email" value="${apply.userEmail}" disabled="disabled" class="input-xlarge"/>
								<label>联系电话</label>
								<input type="text" name="userPhone" id="phone" value="${apply.userPhone}" disabled="disabled" class="input-xlarge"/>
								<label>申请时间</label>
								<input type="text" name="userPhone" id="phone" value="${apply.applyTime}" disabled="disabled" class="input-xlarge"/>
								<label style="color: red">申请状态</label>
								<c:choose >
									<c:when test="${apply.applyStatus==0}">submitted</c:when>
									<c:when test="${apply.applyStatus==1}">succeed</c:when>
									<c:when test="${apply.applyStatus==2}">rejected</c:when>
									<c:when test="${apply.applyStatus==3}">canceled</c:when>
								</c:choose>
								<c:choose >
									<c:when test="${apply.applyStatus==1||apply.applyStatus==2}">
									<label >受理时间</label>
									<input type="text" name="userPhone" id="phone" value="${apply.checkTime}" disabled="disabled" class="input-xlarge"/>
									</c:when>
								</c:choose>
					        </div>
					        <div style="float:left;margin: 20px">
							    <label>申请陈述</label>
								<textarea rows="8" cols="30" name="applyDes" id="applyDes" disabled="disabled" class="input-xlarge">${apply.applyDes}
								</textarea>
							</div>
							<div style="float:left;margin: 20px">
							<c:choose>
								<c:when test="${apply.applyStatus==2}">
								<label style="color: red">rejected reason</label>
								<textarea rows="8" cols="30" name="applyDes" id="applyDes" disabled="disabled" class="input-xlarge">${apply.checkRes}
								</textarea>
								</c:when>	
							</c:choose>
							<div>
							</div>						
							</div >
							<div style="clear: left;margin-left: 200px">
							    <div class="btn-toolbar">
									<DIV style="float: left;margin-left: 100px">
									<c:choose>
									<c:when test="${apply.applyStatus==0}">
									<button type="button" class="btn btn-primary" onclick="return cancelapply(${apply.applyId});">取消申请</button>
									</c:when>															    
									</c:choose>
									</DIV>
									</c:forEach>
									
									<div class="btn-group"></div>
								</div>
							</DIV>
								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>