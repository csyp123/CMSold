<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/font-awesome/css/font-awesome.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/other.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap.js"></script>
<title>我的申请</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<a href="#page-stats" class="block-heading" data-toggle="collapse">申请列表</a>
										<a href="#sidebar_menu_6" class="nav-header collapsed" data-toggle="collapse"><i class="icon-leaf"></i>按状态查询<i class="icon-chevron-up"></i></a>
							<ul id="sidebar_menu_6" class="nav nav-list collapse">
								<li><a href="<%=request.getContextPath()%>/checkapply?pageIndex=0&applyStatus=0" >submitted</a></li>
								<li><a href="<%=request.getContextPath()%>/checkapply?pageIndex=0&applyStatus=1" >succeed</a></li>
								<li><a href="<%=request.getContextPath()%>/checkapply?pageIndex=0&applyStatus=2" >rejected</a></li>
							</ul>
					<div id="page-stats" class="block-body collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
									<th style="width: 20px">#</th>
									<th style="width: 80px">申请人</th>
									<th style="width: 80px">申请部门</th>
									<th style="width: 80px">申请日期</th>
									<th style="width: 80px">状态</th>
									<th style="width: 80px">联系方式</th>
									<th style="width: 80px">Email</th>
									<th style="width: 80px">受理日期</th>						
									<th style="width: 80px">操作</th>
									<th style="width: 80px" hidden="hidden">apply_id</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="i" value="0"/>
							<c:forEach items="${checkapplylist}" var="checkapply">
								<c:set var="i" value="${i+1 }"/>
								<tr>
									<td>${1}</td>
									<td>${checkapply.userName}</td> 
									<td>${checkapply.clubName}</td>
									<td>${checkapply.applyTime}</td>
									<td style="color:red"><c:choose>
									<c:when test="${checkapply.applyStatus==0}">submitted</c:when>
									<c:when test="${checkapply.applyStatus==1}">succeed</c:when>
									<c:when test="${checkapply.applyStatus==2}">rejected</c:when>
									</c:choose></td>
									<td>${checkapply.userPhone}</td>
									<td>${checkapply.userEmail}</td>
									<td>${checkapply.checkTime}</td>
									<td><c:choose>
									<c:when test="${checkapply.applyStatus==0}">
									<a href="checkapplydetails?applyId=${checkapply.applyId}" title="查看"><i class="icon-pencil"></i></a> &nbsp; 
										<a data-toggle="modal" href="javascript:void(0);" title="通过" onclick="return passapply(${checkapply.applyId},${checkapply.userId},${checkapply.clubId});"><i class="icon-play"></i></a></c:when></c:choose></td>
									<td hidden="hidden"></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>

						<div class="pagination">
						<% 
						Integer pageIndex=((Integer)request.getAttribute("pageIndex")/10)+1;
						%>
							<ul>
								<li><a href="javascript:void(0);" onclick="lastpage(<%=pageIndex%>)">&lt;&lt;</a></li>
								<li class="active"><span><%=pageIndex%></span></li>
								<li><a href="javascript:void(0);" onclick="nextpage(<%=pageIndex%>);">&gt;&gt;</a></li>
								<li><span>共${requestScope.checkapplysize}条</span></li>
							</ul>
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
				location.href="<%=request.getContextPath()%>/checkapply?pageIndex=<%=pageIndex%>";
				return true;
}
});
		}else{
		return false;
		}
}
function lastpage(p){
	if (p===1) {
		alert("there is no previous page");
	}else{
		p=p-1;
		location.href="checkapply?pageIndex="+p;
	}
}
function nextpage(p){
		p=p+1;
		location.href="checkapply?pageIndex="+p;
}
</script>
</html>