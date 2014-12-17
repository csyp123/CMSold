<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	function doApply(){
			var statement=document.getElementById("statement").value;
			if(statement.length<=8){
					if(confirm('您确认不输入申请陈述？')){
						   
							$.ajax({
									type:"post",
									data:"statement="+statement+"&clubId="+${club.clubId}+"&requesterId="+${sessionScope.user.userId},
									dataType:"JSON",
									url:"apply",
									success:function(result){
											if(result.status==0){
													alert(result.msg);
													return false;

												}else{
														alert("申请已发出");
														location.href=result.url;
													}

										}
									


								});
						}
				}else{
					$.ajax({
						type:"post",
						data:"statement="+statement+"&clubId="+${club.clubId}+"&requesterId="+${sessionScope.user.userId},
						dataType:"JSON",
						url:"apply",
						success:function(result){
								if(result.status==0){
										alert(result.msg);
										return false;

									}else{
										    alert("申请已发出");
											location.href=result.url;
										}

							}
						


					});
					}
		}
</script>
<title>申请加入</title>
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
			<h1 class="page-title">申请加入</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li class="active">${club.clubName}俱乐部</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">请填写申请资料</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
							<DIV>
							<div style="float:left;margin: 20px">
							     <label>用户名</label>
								<input type="text" name="userName" id="userName" value="${sessionScope.user.userName}" disabled="disabled"  class="input-xlarge"/>
								<label>所属部门</label>
								<input type="text" name="userPart" id="userPart" value="${sessionScope.user.userPart}" disabled="disabled"  class="input-xlarge"/>
								<label>Email</label>
								<input type="text" name="userEmail" id="email" value="${sessionScope.user.userEmail}" disabled="disabled"  class="input-xlarge"/>
								<label>联系电话</label>
								<input type="text" name="userPhone" id="phone" value="${sessionScope.user.userPhone}" disabled="disabled"  class="input-xlarge"/>
					        </div>
					        <div style="float:left;margin: 20px">
							    <label>申请陈述</label>
								<textarea rows="8" cols="30" name="statement" id="statement" value=""  class="input-xlarge">
								</textarea>
							
							</div>
							
							</DIV >
							<DIV style="clear: left;margin-left: 200px">
							    <div class="btn-toolbar">
							        <DIV style="float: left">
							        <button type="button" class="btn btn-primary" onclick="return doApply();"><strong>提交</strong></button>
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