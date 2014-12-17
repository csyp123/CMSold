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
<script type="text/javascript">
	function voteExclude(excludeId,buttonId){
		var radio=document.getElementsByName(excludeId);
		var choice="";
		for(var i=0;i<radio.length;i++){
			if(radio[i].checked){
				choice=radio[i].value;
			}
		}
		if(choice==""){
			alert("还没有做出选择");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			data:{excludeId:excludeId,choice:choice},
			url:"<%=request.getContextPath()%>/ExcludeVoteServlet",
			success:function(result){
					document.getElementById(buttonId).value=result.msg;
					document.getElementById(buttonId).disabled=true;
			}
		});
	}
</script>
<title>选举详细</title>
</head>
<body class="simple_body">
	<div class="content">
		<c:if test="${excludeList.size()==0 }">
			<div align="center" font_size="40px">暂时没有投票信息.....</div>
		</c:if>
		<div class="container-fluid">
			<div class="row-fluid">
				<!-- START 以上内容不需更改，保证该TPL页内的标签匹配即可 -->
				<c:forEach items="${excludeList}" var="exclude" varStatus="status">
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">投票资料</a></li>
					</ul>

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<DIV>
					        <div style="float:left;margin: 20px">

									<table class="tablestyle">
										<tr>
											<td width="1200" height="23" colspan="5" class="title"
												align="center">
												<strong><font color="red">${exclude.clubName}发起了把${exclude.userName}踢出俱乐部的投票</font></strong></td>
										</tr>
										<tr>
											<td width="120">原因：</td>
											<td colspan="4">${exclude.excludeReason}</td>
										</tr>	
										<tr>									
											<td width="120">你的选择：</td>
											<td>不同意<input type="radio" name="${exclude.excludeId}" value="1" /></td>
  											<td>同意<input type="radio" name="${exclude.excludeId}" value="2" /></td>
  											<td>弃权<input type="radio" name="${exclude.excludeId}" value="3" /></td>
										</tr>
										<tr>									
											<td width="120">开始时间：</td>
											<td colspan="4">${exclude.startTime }</td>
										</tr>
										<tr>									
											<td width="120">结束时间：</td>
											<td colspan="4">${exclude.expireTime }</td>
										</tr>
										<tr>
											<td align="center"><input name="button" type="button" id="${status.index+1}" 
												onclick='voteExclude("${exclude.excludeId}","${status.index+1}");' value="提交投票"/></td>
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