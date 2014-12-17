<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/datepicker/css/datepicker.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/other.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
</script>
<title>受理申请</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">受理申请</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() %>/index">首页 </a> <span class="divider">/</span></li>
			<li class="active">申请消息</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">消息详情</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
								<c:forEach items="${checkapplydetails}" var="checkapply">
							<DIV>

							<div style="float:left;margin: 20px">
							    <label>用户名</label>
								<input type="text" name="userName" id="userName" value="${checkapply.userName}" disabled="disabled" class="input-xlarge"/>
								<label>所属部门</label> 
								<input type="text" name="userPart" id="userPart" value="${checkapply.userPart}" disabled="disabled" class="input-xlarge"/>
								<label>Email</label>
								<input type="text" name="userEmail" id="email" value="${checkapply.userEmail}" disabled="disabled" class="input-xlarge"/>
								<label>联系电话</label>
								<input type="text" name="userPhone" id="phone" value="${checkapply.userPhone}" disabled="disabled" class="input-xlarge"/>
								<label>申请时间</label>
								<input type="text" name="userPhone" id="phone" value="${checkapply.applyTime}" disabled="disabled" class="input-xlarge"/>
								<label style="color: red">申请状态</label>
								<c:choose >
									<c:when test="${checkapply.applyStatus==0}">submitted</c:when>
									<c:when test="${checkapply.applyStatus==1}">succeed</c:when>
									<c:when test="${checkapply.applyStatus==2}">rejected</c:when>
									<c:when test="${checkapply.applyStatus==3}">canceled</c:when>
								</c:choose>
								<c:choose >
									<c:when test="${apply.applyStatus==1}">
									<label >受理时间</label>
									<input type="text" name="userPhone" id="phone" value="${checkapply.checkTime}" disabled="disabled" class="input-xlarge"/>
									</c:when>
								</c:choose>
					        </div>
					        <div style="float:left;margin: 20px">
							    <label>申请陈述</label>
								<textarea rows="8" cols="30" name="applyDes" id="applyDes" disabled="disabled" class="input-xlarge">${checkapply.applyDes}
								</textarea>
							</div>
							<div style="float:left;margin: 20px">
							<c:choose>
								<c:when test="${checkapply.applyStatus==0}">
								<label style="color: red">rejected reason</label>
								<textarea rows="8" cols="30" name="checkRes" id="checkRes" class="input-xlarge">
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
									<c:when test="${checkapply.applyStatus==0}">
									<button type="button" class="btn btn-primary" onclick="return passapply(${checkapply.applyId},${checkapply.userId},${checkapply.clubId});">pass</button>
									<button type="button" class="btn btn-primary" onclick="return rejectapply(${checkapply.applyId});">reject</button>
									</c:when>															    
									</c:choose>
									</DIV>
									</c:forEach>
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
<script type="text/javascript">
function passapply(applyId,userId,clubId){
if(confirm('请确认是否通过')){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"<%=request.getContextPath()%>/passapply?applyId="+applyId+"&userId="+userId+"&clubId="+clubId,
		success:function(result){
			alert(result.msg);
				location.href="<%=request.getContextPath()%>/checkapply?pageIndex=0";
				return true;
}
});
		}else{
		return false;
		}
}
function rejectapply(applyId){

if (confirm('请确认是否驳回')) {
	var checkRes = document.getElementById("checkRes").value;
		if (checkRes.length<=8) {
			alert('驳回原因过短');
			return false;
		}else{
		$.ajax({
		type:"GET",
		dataType:"json",
		url:"<%=request.getContextPath()%>/rejectapply?applyId="+applyId+"&checkRes="+checkRes,
		success:function(result){
			alert(result.msg);
				location.href="<%=request.getContextPath()%>/checkapply?pageIndex=0";
				return true;
}
});
}
	
}else{
return false;


}
}
</script>
</html>