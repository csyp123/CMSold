<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.CMS.servlet.InitServlet" %>
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
<title>菜单页面</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="header">
			<div class="stats">
				<p class="stat">
					<!--span class="number"></span-->
				</p>
			</div>

			<h1 class="page-title">用户信息查询</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() %>/init?location=chengdu">首页 </a> <span class="divider">/</span></li>
			<li><a href="<%=request.getContextPath() %>/search.jsp">返回 </a> <span class="divider">/</span></li>
			<li class="active">用户信息查询</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
						<div style="clear: both;"></div>
				</div>
				<%
				HttpSession session1=request.getSession();
				Integer type= (Integer)session1.getAttribute("rowNumber");
				int rowNumber;
				if(type==null){
					HttpSession session2=request.getSession();
					List resultList=new ArrayList();
					resultList=(ArrayList)session2.getAttribute("resultList");
					rowNumber=resultList.size();
				}else{
					rowNumber=type;
				}
				
				int pageCount;
				int pageNow=0;
				int startRow;
				int endRow;
				if(rowNumber%5==0){
					pageCount=rowNumber/5;
				}else{
					pageCount=rowNumber/5+1;
				}
				String getPageNow=request.getParameter("pageNow");
				if(getPageNow==null){
					pageNow=1;
				}else{
					pageNow=Integer.parseInt(getPageNow);
					if(pageNow<1){
						pageNow=1;
					}else if(pageNow>pageCount){
						pageNow=pageCount;
						}
				}
				startRow=(pageNow-1)*5;
				endRow=startRow+4;
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", pageNow);
				request.setAttribute("startRow", startRow);
				request.setAttribute("endRow", endRow);
				HttpSession httpSession=request.getSession();
				httpSession.setAttribute("rowNumber", rowNumber);
				%>
				<div class="block">
					<a href="#page-stats" class="block-heading" data-toggle="collapse">信息列表</a>
					<div id="page-stats" class="block-body collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
								    <th style="width: 20px" hidden="hidden">#</th>
									<th style="width: 80px">用户名</th>
									<th style="width: 80px">所属部门</th>
									<th style="width: 120px">邮箱</th>
									<th style="width: 100px">联系电话</th>
									<th style="width: 100px">所在俱乐部</th>
									<th style="width: 80px">俱乐部所在地</th>
									<th style="width: 80px">所担任职位</th>
									<th style="width: 80px">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="i" value="0"/>
							<c:forEach items="${sessionScope.resultList}" var="searchDto" begin="${startRow}" end="${endRow}">
								<c:set var="i" value="${i+1 }"/>
								<tr>
								   <td name=culbId id="${i}" hidden="hidden">${searchDto["userId"]}</td>
									<td>${searchDto.userName }</td>
									<td>${searchDto.userPart }</td>
									<td>${searchDto.userEmail}</td>
									<c:if test="${not empty searchDto.userPhone}">
										<td>${searchDto.userPhone}</td>
									</c:if>
									<c:if test="${empty searchDto.userPhone}">
										<td>无</td>
									</c:if>
									<c:if test="${not empty searchDto.clubName}">
										<td>${searchDto.clubName}</td>
									</c:if>
									<c:if test="${empty searchDto.clubName}">
										<td>无</td>
									</c:if>
									<c:if test="${not empty searchDto.clubName}">
										<td>${searchDto.clubLocation}</td>
									</c:if>
									<c:if test="${empty searchDto.clubName}">
										<td>无</td>
									</c:if>
									<c:if test="${not empty searchDto.clubName}">
										<c:if test="${searchDto.userType==0}">
										<td>负责人</td>
										</c:if>
										<c:if test="${searchDto.userType==1}">
										<td>普通会员</td>
										</c:if>
										<c:if test="${searchDto.userType==10}">
										<td>系统管理员</td>
										</c:if>
									</c:if>
									<c:if test="${empty searchDto.clubName}">
										<td>无</td>
									</c:if>
									<td><button class="btn btn-primary" style="float:left;width : 120px; height : 28px;" onclick="searchUser(${searchDto.userName},${searchDto.userType});">
										<font color="white">发送消息</font></button> &nbsp;&nbsp; </td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<!--- START 分页模板 --->

						<div class="pagination">
							<ul>
								<li><a href="<%=request.getContextPath() %>/searchlist.jsp?pageNow=${pageNow-1}">上一页</a></li>
								<li class="active"><span>${pageNow}</span></li>
								<li><a href="<%=request.getContextPath() %>/searchlist.jsp?pageNow=${pageNow+1}" >下一页</a></li>
								<li><span id="countclub">共${pageCount}页</span></li>
								<li><span id="countclub">共${sessionScope.resultList.size()}条</span></li>
							</ul>
						</div>
						<!--- END --->
					</div>
				</div>
			</div>
		</div>
</body>
<SCRIPT type="text/javascript">
	function searchUser(userName,userType){
		location.href="searchUser?userName="+clubId+"&userType="+userType;
	}
</SCRIPT>
</html>