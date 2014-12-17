<?xml version="1.0" encoding="UTF-8" ?>
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
<SCRIPT type="text/javascript">
	function appendMonth(){
		$('#month').empty();
		if(document.getElementById("year").value=="-1")return;
		for(var i=1;i<=12;i++){
			$('#month').append('<option label="'+i+'" value="'+i+'"></option>');
		}
		appendDay();
	}
	function appendDay(){
		$('#day').empty();
		year=document.getElementById("year").value;
		month=document.getElementById("month").value;
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
			for(var i=1;i<=31;i++){
				$('#day').append('<option label="'+i+'" value="'+i+'"></option>');
			}
		}else if(month!=2){
			for(var i=1;i<=30;i++){
				$('#day').append('<option label="'+i+'" value="'+i+'"></option>');
			}
		}
		else if(year%4==0&&year%100!=0||year%400==0){
			for(var i=1;i<=29;i++){
				$('#day').append('<option label="'+i+'" value="'+i+'"></option>');
			}
		}
		else {
			for(var i=1;i<=28;i++){
				$('#day').append('<option label="'+i+'" value="'+i+'"></option>');
			}
		}
	}
</SCRIPT>
<title>开除查询</title>
</head>
<body class="simple_body">
	<div class="content">
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="bb-alert alert alert-info" style="display: none;">
					<span>操作成功</span>
				</div>
				<!--- START 以上内容不需更改，保证该TPL页内的标签匹配即可 --->
				<div class="btn-toolbar" style="margin-bottom: 2px;">
					 <a data-toggle="collapse" data-target="#search" href="#" title="检索"><button
							class="btn btn-primary" style="margin-left: 5px">
							<i class="icon-search"></i>
						</button></a>
				</div>
				
				<div id="search" class="collapse out">
					<form  class="form_search" action="ExcludeQuery" method="post" style="margin-bottom: 0px">
						<div style="float: left; margin-right: 5px">
							投票结果
							<select name="excludeResult" id="excludeResult">
								<option label="*" value="-1">*</option>
								<option label="正在进行" value="1"></option>
								<option label="成功结束" value="2"></option>
								<option label="失败结束" value="3"></option>
								<option label="过期" value="4"></option>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;开始时间
							<select name="year" id="year" onchange="appendMonth();" style="width:70px">
								<option  label="*" value="-1"></option>
								<option label="2000" value="2000"></option>
								<option label="2001" value="2001"></option>
								<option label="2002" value="2002"></option>
								<option label="2003" value="2003"></option>
								<option label="2004" value="2004"></option>
								<option label="2005" value="2005"></option>
								<option label="2006" value="2006"></option>
								<option label="2007" value="2007"></option>
								<option label="2008" value="2008"></option>
								<option label="2009" value="2009"></option>
								<option label="2010" value="2010"></option>
								<option label="2011" value="2011"></option>
								<option label="2012" value="2012"></option>
								<option label="2013" value="2013"></option>
								<option label="2014" value="2014"></option>
								<option label="2015" value="2015"></option>
								<option label="2016" value="2016"></option>
								<option label="2017" value="2017"></option>
								<option label="2018" value="2018"></option>
								<option label="2019" value="2019"></option>
								<option label="2020" value="2020"></option>
							</select>年&nbsp;
							<select name="month" id="month" onchange="appendDay();" style="width:70px">
							</select>月&nbsp;
							<select name="day" id="day" style="width:70px">
							</select>日&nbsp;
						</div>
						<div class="btn-toolbar">
							<button type="submit" class="btn btn-primary">查询</button>
						</div>
						<div style="clear: both;"></div>
					</form>
				</div>
				<div class="block">
					<a href="#page-stats" class="block-heading" data-toggle="collapse">账号列表</a>
					<div id="page-stats" class="block-body collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
								    <th style="width: 20px" hidden="hidden">#</th>
									<th style="width: 20px">#</th>
									<th style="width: 80px">俱乐部</th>
									<th style="width: 100px">被投票人</th>
									<th style="width: 70px">投票结果</th>
									<th style="width: 140px">开始时间</th>
									<th style="width: 140px">结束（截止）时间</th>
									<th style="width: 50px">同意票</th>
									<th style="width: 50px">反对票</th>
									<th style="width: 50px">弃权票</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${excludeQueryList}" var="excludeQuery" varStatus="status">
								<tr>
									<td>${status.index }</td>
									<td>${excludeQuery.clubName }</td>
									<td>${excludeQuery.userName }</td>
									<td>${excludeQuery.state}</td>
									<td>${excludeQuery.startTime }</td>
									<td>${excludeQuery.expireTime }</td>
									<td>${excludeQuery.forVotes}</td>
									<td>${excludeQuery.againstVotes }</td>
									<td>${excludeQuery.neutralVotes }</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>