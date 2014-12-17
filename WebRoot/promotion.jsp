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
	function dopromote(promotionId){
	if (confirm('你确认提交么？')) {		
		var voteduser=document.getElementsByName("promotionuser");
		var params="";
		for(var i=0;i<voteduser.length;i++){
			if(voteduser[i].checked){
				params=voteduser[i].value;
				break;
			}
		}
		if(params===""){
			alert("没有选择成员");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			data:{promotionuser:params,promotionId:promotionId},
			url:"<%=request.getContextPath()%>/onpromotion",
			success:function(result){
					alert(result.msg);
					location.reload();
					return true;
			}
		});
	}else{
	return false;
	}
	}
</script>
<title>${requestScope.clubName}负责人选举</title>
</head>
<body class="simple_body">
	<div class="content">
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<a href="#page-stats" class="block-heading" data-toggle="collapse"><strong><font color="red">${requestScope.clubName}</font></strong>负责人选举</a>
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
								<c:forEach items="${promotionuserlist }" var="user" varStatus="status">
								<tr>
									<c:set var="items" value="${status.count }"/>																	
									<td>${status.count }</td>
									<td>${user.userName }</td>
									<td>${user.userPart }</td>
									<td>${user.userPhone }</td>	
									<td>${user.userEmail }</td>	
									<td><input type="radio" name="promotionuser" value="${user.userId}"/></td>
								</tr><input hidden="hidden" id="promotionId" value="${requestScope.promotionId}"/>
								</c:forEach>
							</tbody>
						</table>
						<div>
						<button type="button" onclick="return dopromote(${requestScope.promotionId});">提交</button>
						</div>
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