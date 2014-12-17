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
<title>删除俱乐部</title>
<script type="text/javascript">
	function doDelete(){
			var clubName=document.getElementById("clubName").value;
			var clubLocation="${clubLocation}";
			if(clubName==0){
				alert("请输入俱乐部名称！");
				return false;
			}
			$.ajax({
				type:"post",
				data:"clubName="+clubName+"&clubLocation="+clubLocation,
				dataType:"JSON",
				url:"deleteClub",
				success:function(result){
						if(result.status==0){
								alert(result.msg);
								return false;
						}else{
								 alert("俱乐部删除成功！");
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
			<h1 class="page-title">删除俱乐部</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li class="active">删除</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">请输入俱乐部相关信息</a></li>
						<li class="active"><a href="#home" data-toggle="tab" style="color:red">只有俱乐部中仅剩下管理员时，才可以将该俱乐部删除</a></li>
					</ul>
					<ul class="nav nav-tabs">
					<c:choose>
						<c:when test="${clubLocation eq 'chengdu'}">
							<li class="active"><a href="<%=request.getContextPath()%>/initDeleteClub?clubLocation=chengdu" data-toggle="tab">成都</a></li>
						</c:when>
						<c:otherwise>
							<li class="selected"><a href="<%=request.getContextPath()%>/initDeleteClub?clubLocation=chengdu" >成都</a></li>
						</c:otherwise>
					</c:choose>
						<c:choose>
						<c:when test="${clubLocation eq 'beijing'}">
							<li class="active"><a href="<%=request.getContextPath()%>/initDeleteClub?clubLocation=beijing" data-toggle="tab">北京</a></li>
						</c:when>
						<c:otherwise>
							<li class="selected"><a href="<%=request.getContextPath()%>/initDeleteClub?clubLocation=beijing">北京</a></li>
						</c:otherwise>
					</c:choose>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">

							<form id="tab" method="post" action="#" autocomplete="off">
							<DIV>
							<div style="float:left;margin: 20px">
								<select name="clubName" id="clubName" class="input-xlarge">
								<option label="请选择俱乐部" value="0">请选择俱乐部</option>
								<c:forEach items="${clubList}" var="clubInfo">
										<option label="${clubInfo.clubName}" value="${clubInfo.clubName}">${clubInfo.clubName}</option>
								</c:forEach>
							    </select>
					        </div>
							</DIV >
							<DIV style="clear: left;margin-left: 200px">
							    <div class="btn-toolbar">
							        <DIV style="float: left">
							        <button type="button" class="btn btn-primary" onclick="return doDelete();"><strong>提交</strong></button>
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