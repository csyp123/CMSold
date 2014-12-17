<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
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
					<a href="#page-stats" class="block-heading" data-toggle="collapse">我的申请</a>
					<a href="#sidebar_menu_6" class="nav-header collapsed" data-toggle="collapse"><i class="icon-leaf"></i>按状态查询<i class="icon-chevron-up"></i></a>
							<ul id="sidebar_menu_6" class="nav nav-list collapse">
								<li><a href="<%=request.getContextPath()%>/myapply?pageIndex=0&applyStatus=0" >submitted</a></li>
								<li><a href="<%=request.getContextPath()%>/myapply?pageIndex=0&applyStatus=1" >succeed</a></li>
								<li><a href="<%=request.getContextPath()%>/myapply?pageIndex=0&applyStatus=2" >rejected</a></li>
								<li><a href="<%=request.getContextPath()%>/myapply?pageIndex=0&applyStatus=3" >canceled</a></li>
							</ul>
					<div id="page-stats" class="block-body collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
									<th style="width: 20px">#</th>
									<th style="width: 80px">申请部门</th>
									<th style="width: 80px">申请日期</th>
									<th style="width: 80px">状态</th>
									<th style="width: 80px">负责人联系方式</th>
									<th style="width: 80px">受理时间</th>
									<th style="width: 80px">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="i" value="0"/>
							<c:forEach items="${applylist}" var="apply">
								<c:set var="i" value="${i+1 }"/>
								<tr>
									<td>${i}</td>
									<td>${apply.clubName}</td>
									<td>${apply.applyTime}</td>
									<td style="color:red"><c:choose>
									<c:when test="${apply.applyStatus==0}">submitted</c:when>
									<c:when test="${apply.applyStatus==1}">succeed</c:when>
									<c:when test="${apply.applyStatus==2}">rejected</c:when>
									<c:when test="${apply.applyStatus==3}">canceled</c:when>
									</c:choose></td>
									<td>${apply.managerPhone}</td>
									<td><c:choose>
									<c:when test="${apply.applyStatus==1||apply.applyStatus==2}">${apply.checkTime}</c:when>
									</c:choose></td>
									<td><a href="applydetails?applyId=${apply.applyId}" title="查看"><i class="icon-pencil"></i></a> &nbsp; 
										<c:choose><c:when test="${apply.applyStatus==0}"><a data-toggle="modal" href="#" title="撤销" onclick="return cancelapply(${apply.applyId});"><i class="icon-remove"></i></a></c:when></c:choose></td>	
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
								<li><span>共${requestScope.applysize}条</span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

function cancelapply(applyId){
if(confirm('请确认是否撤销')){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"<%=request.getContextPath()%>/cancelapply?applyId="+applyId,
		success:function(result){
			alert(result.msg);
				location.href="<%=request.getContextPath()%>/myapply?pageIndex=<%=pageIndex%>";
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
		location.href="myapply?pageIndex="+p;
	}
}
function nextpage(p){
		p=p+1;
		location.href="myapply?pageIndex="+p;
}
</script>
</html>