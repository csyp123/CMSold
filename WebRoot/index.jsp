<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公司俱乐部管理</title>
</head>
<frameset rows="43px,*" frameborder="no" border="0" framespacing="0">
    <frame id="head" name="head" src="header.jsp" scrolling="no" frameborder="0" noresize="noresize"/>
	<frameset rows="*,50px;" frameborder="no" border="0" framespacing="0">
		<frameset cols="240px,*" frameborder="no" border="0" framespacing="0">
	    	<frame id="left" name="left" src="left.jsp?clublist=${clublist}" frameborder="0" noresize="noresize"/>
	    	<frame id="main" name="main" src="main.jsp" frameborder="0" noresize="noresize"/>
	    </frameset>
	    <frame id="footer" name="footer" src="footer.jsp" frameborder="0"/>
	</frameset>
</frameset>
</html>