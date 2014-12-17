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
<title>选举详细</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>
			<h1 class="page-title">选举详细</h1>
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
			<c:if test="${sessionScope.promotionlist.size()==0 }">
			         <div align="center" font_size="40px">暂时没有选举信息.....</div>
			</c:if>
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<c:forEach items="${sessionScope.promotionlist}" var="promotion">
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab"><strong>${promotion.clubName}</strong>选举资料</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<DIV>
					        <div style="float:left;margin: 20px">

									<table class="tablestyle">
										<tr>
											<td width="1200" height="23" colspan="5" class="title"
												align="center">
												<strong><font color="red">${promotion.clubName}</font></strong>发起了选举</td>
										</tr>
										<tr>
											<td width="120">原因：</td>
											<td colspan="4">${promotion.promotionReason}</td>
										</tr>	
										<tr>									
											<td width="120">推荐的人：</td>
											<td colspan="4">${promotion.recommenduserName}</td>
										</tr>
										<tr>									
											<td width="120">开始时间：</td>
											<td colspan="4">${promotion.startTime}</td>
										</tr>
										<tr>									
											<td width="120">结束时间：</td>
											<td colspan="4">${promotion.expireTime}</td>
										</tr>
										<tr>
											<td align="center"><button type=button width="200" value="3" /><a style="text-decoration: none" href="<%=request.getContextPath() %>/onpromotion?promotionId=${promotion.promotionId}&clubName=${promotion.clubName}">进入投票页面</a></button></td>
										</tr>

									</table>

									
								</div>
							</DIV>							
						</div>
					</div>
				
				</div>
				</c:forEach>
			</div>
		</div>

	</div>

</body>
</html>