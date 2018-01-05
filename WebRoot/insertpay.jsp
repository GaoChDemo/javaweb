<%@ page language="java"
	import="bean.Customer,DAO.*,db.*,daoFactory.*,java.util.Date,java.text.SimpleDateFormat"
	pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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

<title>My JSP 'moduser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
<script language="javascript" type="text/javascript">
	//验证表单的函数
	function check() {

		function IsNum(num) {
			var reNum = /^\d*$/;
			return (reNum.test(num));
		}
		var code = document.moduser.paytime.value;
		if (code != "" && code != null) {
		} else {
			alert("日期不能为空");
			document.moduser.paytime.focus();
			return false;
		}

		var codec = document.moduser.income_money.value;
		if (codec == "" || codec == null) {
			alert("金额不能为空");
			document.moduser.income_money.focus();
			return false;
		} else if (!IsNum(codec)) {
			alert("金额格式不正确!");
			return false;
		}
		
		return true;
		
	}
</script>

</head>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	String username = (String) session.getAttribute("username"); //获取值
	UserDAO dao = UserDAOFactory.getUserDAOInstance();
	bean.Customer user = dao.getUserByuserUsername(username);//通过用户名获取用户信息
	Date date = new Date(); 					//设置及获取日期
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String str = sdf.format(date);
	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	String strd = sdfd.format(date);
	user.setPayid(str);
	user.setPaytime(strd);
%>


<body bgcolor="#eef2fb">
	<div id="login">
		<div id="form">
			<form action="insert.action" method="post" name="moduser">
				<table border="1">
					<tr class="title">
						<td align="center" colspan="2">储&nbsp;蓄&nbsp;功&nbsp;能</td>
					</tr>
					<tr>
						<td class="item">存单编号:</td>
						<td><input type="text" name="payid" size="20" readonly="true"
							value="<%=user.getPayid()%>">
						</td>
					</tr>
					<tr>
						<td class="item">用户名:</td>
						<td><input type="text" name="username" readonly="true"
							size="20" value="<%=user.getUsername()%>">
						</td>
					</tr>
					<tr>
						<td class="item">操作时间:</td>
						<td><input type="date" name="paytime" size="20"
							value="<%=user.getPaytime()%>">
						</td>
					</tr>
					<tr>
						<td class="item">操作类型:</td>
						<td><select class="selectStyle" name="paytype" id="paytype"
							type="text">
								<option value="0">存款</option>
								<option value="1">取款</option>
								<option value="2">缴纳水电费</option>
								<option value="3">缴纳电话费</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="item">金额:</td>
						<td><input type="text" name="income_money" size="20"
							maxlength="8">
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;&nbsp;<input type="submit" name="sm" onclick="return check()" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" name="rs" value="清空">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>