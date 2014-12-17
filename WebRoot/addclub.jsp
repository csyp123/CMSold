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
<title>新增俱乐部</title>
<script type="text/javascript">
	function doApply(){
			var clubName=document.getElementById("clubName").value;
			var managerName=document.getElementById("managerName").value;
			var clubLocation=document.getElementById("clubLocation").value;
			var clubDes=document.getElementById("clubDes").value;
			if(clubName==""){
				alert("请输入俱乐部名称！");
				return false;
			}
			if(managerName==""){
				alert("请输入俱乐部负责人！");
				return false;
			}
			if(clubLocation==""){
				alert("请输入俱乐部所在地！");
				return false;
			}
			if(clubDes.length<=8){
				alert("请输入关于俱乐部的相关描述！");
				return false;
			}
			$.ajax({
				type:"post",
				data:"clubName="+clubName+"&managerName="+managerName+"&clubLocation="+clubLocation+"&clubDes="+clubDes,
				dataType:"JSON",
				url:"addClub",
				success:function(result){
						if(result.status==0){
								alert(result.msg);
								return false;
						}else{
								 alert("创建成功！");
								location.href=result.url+"?location="+clubLocation;
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
			<h1 class="page-title">添加俱乐部</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li class="active">添加</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">请填写俱乐部信息</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
							<DIV>
							<div style="float:left;margin: 20px">
							     <label>俱乐部名称</label>
								<input type="text" name="clubName" id="clubName" value=""   class="input-xlarge"/>
								<label>负责人姓名</label>
								<input type="text" name="managerName" id="managerName" value="" class="input-xlarge"/>
								<label>俱乐部所在地</label>
								<select name="clubLocation" id="clubLocation" class="input-xlarge">
								<option label="请选择俱乐部所属地" value="">请选择俱乐部所属地</option>
								<option label="北京" value="beijing">北京</option>
								<option label="成都" value="chengdu">成都</option>
							    </select>
					        </div>
					        <div style="float:left;margin: 20px">
							    <label>俱乐部描述</label>
								<textarea rows="8" cols="30" name="clubDes" id="clubDes" value="" class="input-xlarge">
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