<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/validator-0.7.3/jquery.validator.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validator-0.7.3/local/zh_CN.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/datepicker/css/datepicker.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/other.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/datepicker/js/bootstrap-datepicker.js"></script>
<title>更改密码</title>
<script type="text/javascript">
/**
 *@author joeyy 
 *2014/11/17
 *function check if password1==password2
 */
function checkpassword(){
	var password1=document.getElementById("password1").value;
	var password2=document.getElementById("password2").value;
$.ajax({
	type:"POST",
	dataType:"json",
	data:{password1:password1,password2:password2},
	url:"<%=request.getContextPath()%>/checkpassword",
	success:function(result){
	if (result.status==0) {
		var checkdiv=document.getElementById("checkdiv");
		var msg="<span class=\"msg-box n-right\" style=\"\" for=\"password2\">"+"<span class=\"msg-wrap n-error\" role=\"alert\">"+"<span class=\"n-icon\">"+"</span>"+"<span class=\"n-msg\">"+result.msg+"</span></span></span>";
		checkdiv.innerHTML=msg;
	}else{
		var checkdiv=document.getElementById("checkdiv");
		var btnsubmit1 = document.getElementById("btnsubmit1");
		var btn="<button type=\"button\" class=\"btn btn-primary\" onclick=\"updatepassword();\">"+"<strong>"+"提交"+"</strong>"+"</button>";
		var msg="<span class=\"msg-box n-right\" style=\"\" for=\"password2\">"+"<span class=\"msg-wrap n-ok\" role=\"alert\">"+"<span class=\"n-icon\">"+"</span>"+"<span class=\"n-msg\">"+"</span></span></span>";
		checkdiv.innerHTML=msg;	
		btnsubmit1.innerHTML=btn;
		
		}
	}
});
}
/**
 *@author joeyy 
 *2014/11/25
 *function changepassword
 */
function updatepassword(){
		var password1=document.getElementById("password1").value;
		var password=document.getElementById("password").value;
		$.ajax({
			type:"POST",
			datType:"json",
			data:{password1:password1,password:password},
			url:"<%=request.getContextPath()%>/updatepassword",
			success:function(result){
				if (result.status==0) {
					var changepsw = document.getElementById("changepsw");
					changepsw.innerHTML = result.msg;	
				}else{
					window.parent.location.href="<%=request.getContextPath()%>/login.jsp";
				}	
			}
		});
	}
</script>
</head>
<body class="simple_body">
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">更改密码</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="exchangePsw.jsp">更改密码 </a> <span class="divider">/</span></li>
			<li class="active">更改密码</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">更改密码</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
						<span id="changepsw" style="color:red"></span>

							<form autocomplete="off">
								<label>输入旧密码</label>
								<input type="password" name="password" value="" class="input-xlarge" id="password" data-rule="required;" />
								<label>输入新密码</label>
								<input type="password" class="input-xlarge" id="password1" name="passwrod1" data-rule="required;password;"/>
								<label>确认新密码</label>
								<input type="password" class="input-xlarge" id="password2" onblur="checkpassword();"/>
								<span id="checkdiv"></span>
								<div class="btn-toolbar" id="btnsubmit1">	
									<div class="btn-group"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>