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

<title>���Ŀ���Servlet����</title>

<link rel="stylesheet" type="text/css" href="./css/default.css" />
<script language="javascript" type="text/javascript">
//��֤���ĺ���
function check()
{
	var code=document.f1.username.value;
   if(code!="" && code!=null){
   }
   else{
		alert("�û�������Ϊ��");
   		document.f1.username.focus();
		return false;
   }
   
   var codec=document.f1.userpasswd.value;
   if(codec!="" && codec!=null){
   }
   else{
		alert("���벻��Ϊ��");
   		document.f1.userpasswd.focus();
		return false;
   }
   
   return true;
}
//����û����Ƿ���õķ���

function checkuser(){
 var code=document.f1.userid.value;
 //alert("�û����Ϊ��"+code);
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
						<td align="center" colspan="2">&nbsp;&nbsp;ע&nbsp;&nbsp;��&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td class="item">�û���:</td>
						<td><input type="text" name="username" size="20" value="">
						</td>
					</tr>
					<tr>
						<td width="30%" class="item" >����:</td>
						<td width="70%"><input type="password" name="userpasswd"
							size="20" value="" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" name="sm" value="ע��">&nbsp;&nbsp;<input
							type="reset" name="rs" value="����">
							&nbsp;&nbsp;<a href="./login.jsp" >����</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
