<%@ page language="java" import="bean.Customer,DAO.*,db.*,daoFactory.*"
	pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>欢迎登录!</title>

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<script src="js/function.js" type="text/javascript"></script>

</head>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	String username = (String)session.getAttribute("username");
	UserDAO dao= UserDAOFactory.getUserDAOInstance();
	bean.Customer user=dao.getUserByuserUsername(username);
%>

<body bgcolor="#eef2fb">
<h1><strong><%= user.getUsername() %> 欢迎登录!</strong></h1>
</body>