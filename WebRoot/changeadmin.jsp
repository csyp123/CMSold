<?xml version="1.0" encoding="UTF-8" ?>
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
	function dopromotion(){
			var promotionReson=document.getElementById("promotionReson").value;
			var recommenduserId=document.getElementById("recommenduserId").value;
			if(promotionReson.length>=8){
					if(confirm('你确认提交么？')){
							$.ajax({
									type:"POST",
									data:"promotionReson="+promotionReson+"&recommenduserId="+recommenduserId,
									dataType:"JSON",
									url:"<%=request.getContextPath()%>/changeadmin",
									success:function(result){
											if(result.status==0){
													alert(result.msg);
													return false;
												}else{
														alert("succeed to initiate");
														location.reload();
														return true;
													}
										}
								});
						}
				}else{	
				alert("你输入的原因过短");
				return false;				
		}
		}
</script>
<title>更换管理员</title>
</head>
<body class="simple_body">
<script type="text/javascript">
	if(${not empty errorMsg}){
		alert("${errorMsg}");
		location.href="<%=request.getContextPath()%>/init?location=chengdu";
		}
</script>
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">更换管理员</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li class="active">${sessionScope.user.userName}</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">请填写更换资料</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
							<DIV>
							<div style="float:left;margin: 20px">
							    <label>推荐的人</label>
								<select name="userType" id="recommenduserId" class="input-xlarge" data-rule="required;">
									<option label="请选择用户" value="">请选择用户</option>
									<c:forEach items="${userList }" var="user" varStatus="status">
									<option label="${user.userName }" value="${user.userId }">${user.userName }</option>
									</c:forEach>
								</select>
					        </div>
					        <div style="float:left;margin: 20px">
							    <label>更换原因</label>
								<textarea rows="8" cols="30" name="promotionReson" id="promotionReson"  class="input-xlarge"></textarea>
							</div>
							</DIV >
							<DIV style="clear: left;margin-left: 200px">
							    <div class="btn-toolbar">
							        <DIV style="float: left">
							        <button type="button" class="btn btn-primary" onclick="return dopromotion();"><strong>提交</strong></button>
							        </DIV>
									<DIV style="float: left;margin-left: 100px">
									    <a href="<%=request.getContextPath()%>/init?location=chengdu" class="btn btn-primary">取消</a>
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