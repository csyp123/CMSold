<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.CMS.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/stylesheets_default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/lib/font-awesome/css/font-awesome.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/other.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function exclude(){
		var excludeUsers=document.getElementsByName("excludeUsers");
		var params="";
		for(i=0;i<excludeUsers.length;i++){
			if(excludeUsers[i].checked){
				params=excludeUsers[i].value;
				break;
			}
		}
		var reason=document.getElementById("reason").value;
		if(params==""){
			alert("没有选择成员");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			data:{excludeUser:params,reason:reason},
			url:"<%=request.getContextPath()%>/BuildExcludeServlet",
			success:function(result){
					var errmsg=document.getElementById("serverMsg");
					alert(result.msg);
					document.getElementById("reason").value="";
			}
		});
	}
</script>
<title>开除成员</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<a href="#page-stats" class="block-heading" data-toggle="collapse">删除成员</a>
					<div id="page-stats" class="block-body collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
									<th style="width: 20px">#</th>
									<th style="width: 80px">用户名</th>
									<th style="width: 80px">部门</th>
									<th style="width: 80px">电话</th>
									<th style="width: 80px">邮箱</th>
									<th style="width: 80px">选择</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList }" var="user" varStatus="status">
								<tr>
									<c:set var="items" value="${status.count }"/>
									<td>${status.count }</td>
									<td>${user.userName }</td>
									<td>${user.userPart }</td>
									<td>${user.userPhone }</td>	
									<td>${user.userEmail }</td>	
									<td><input type="radio" name="excludeUsers" value="${user.userName }"/></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
				
						<div>
						<LABEL>请说明删除原因:</LABEL>
						<textarea rows="8" cols="100" id="reason"  class="input-xlarge"></textarea></div>
						<button class="btn btn-primary" style="float:left;width : 120px; height : 28px;" onclick="exclude();">
										<font color="white">发起投票</font></button><SPAN id="serverMsg" style="color:#ff0000"></SPAN><br/>
						<div class="pagination">
							<ul>
								<li><a href="javascript:void(0);" onclick="">&lt;&lt;</a></li>
								<li class="active"><span></span></li>
								<li><a href="javascript:void(0);" onclick="">&gt;&gt;</a></li>
								<li><span>共${items}条</span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>