<%@ page language="java" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>核心控制Servlet测试</title>

<link rel="stylesheet" type="text/css" href="./css/default.css" />
<script language="javascript" type="text/javascript">
//验证表单的函数
function check()
{
	var code=document.f1.username.value;
   if(code!="" && code!=null){
   }
   else{
		alert("用户名不能为空");
   		document.f1.username.focus();
		return false;
   }
   
   var codec=document.f1.userpasswd.value;
   if(codec!="" && codec!=null){
   }
   else{
		alert("密码不能为空");
   		document.f1.userpasswd.focus();
		return false;
   }
   
   return true;
}
//检查用户名是否可用的方法

function checkuser(){
 var code=document.f1.userid.value;
 //alert("用户编号为："+code);
 if(code!="" && code!=null){
 window.open("./check.action?userid="+code,"mywindow","height:300px;width:400px");
 }
 
}
</script>
<style>
#login {
	width: 400px;
	height: 280px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	margin-top: -140px;
	border: 1px;
	align: center;
}

#form {
	width: 300px;
	height: 160px;
	position: relative;
	left: 50%;
	top: 50%;
	margin-left: -150px;
	margin-top: -80px;
}
</style>
</head>
<body bgcolor="#eef2fb">
	<div id="login">
		<div id="form">
			<form action="register.action" method="post" name="f1"
				onsubmit="return check()">
				<table border="1">
					<tr class="title">
						<td align="center" colspan="2">&nbsp;&nbsp;注&nbsp;&nbsp;册&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td class="item">用户名:</td>
						<td><input type="text" name="username" size="20" value="">
						</td>
					</tr>
					<tr>
						<td width="30%" class="item" >密码:</td>
						<td width="70%"><input type="password" name="userpasswd"
							size="20" value="" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" name="sm" value="注册">&nbsp;&nbsp;<input
							type="reset" name="rs" value="重置">
							&nbsp;&nbsp;<a href="./login.jsp" >返回</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
