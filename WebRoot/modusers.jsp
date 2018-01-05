<%@ page language="java"
	import="bean.Customer,DAO.*,db.*,daoFactory.*,java.util.Date,java.text.SimpleDateFormat,java.util.*"
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
<link rel="stylesheet" href="css/stylet.css" />
<!--
	<link rel="stylesheet" type="text/css" href="css/styles.css">
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

<script type="text/javascript">
	function myFunction() {
		// 声明变量
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");

		// 循环表格每一行，查找匹配项
		for (i = 0; i < tr.length; i++) {
			for ( var j = 0; j < 7; j++) {
				td = tr[i].getElementsByTagName("td")[j];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
						break;
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	}
</script>

</head>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	UserDAO dao = UserDAOFactory.getUserDAOInstance(); 
	List<bean.Customer> list = dao.getAlluser();//获取所有用户信息
%>


<body bgcolor="#eef2fb">
	<form>
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="搜索">
	</form>
	<br>
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th>用户名</th>
				<th>密码</th>
				<th>余额</th>
				<th colspan="2">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=(list.get(i)).getUsername()%></td>
				<td><%=(list.get(i)).getUserpasswd()%></td>
				<td><%=(list.get(i)).getUsermoney()%></td>
				<td><a
					href="./updateu.action?username=<%=(list.get(i)).getUsername()%>">修改信息</a>
				</td>
				<td><a
					href="./deleteu.action?username=<%=(list.get(i)).getUsername()%>">删除</a>
				</td>
				<%
					}
				%>
			
		</tbody>
</body>
</html>