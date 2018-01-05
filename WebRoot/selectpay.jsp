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
          for (var j = 0; j < 7; j++) {
            td = tr[i].getElementsByTagName("td")[j];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break;
                } 
                else {
                  tr[i].style.display = "none";
                }
            }
          }
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
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	String username = (String) session.getAttribute("username");
	UserDAO dao= UserDAOFactory.getUserDAOInstance();
	List<bean.Customer> list = dao.getAllu(username); //获取该用户的账单信息
%>


<body bgcolor="#eef2fb">
	<form>
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="搜索">
	</form><br>
	<table class="table table-striped" id="myTable">
		<thead>
			<tr>
				<th>存单编号</th>
				<th>操作时间</th>
				<th>操作类型</th>
				<th>金额</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=(list.get(i)).getPayid()%></td>
				<td><%=(list.get(i)).getPaytime()%></td>
				<td><%=(list.get(i)).getPaytype()%></td>
				<td><%=(list.get(i)).getIncome_money()%></td>
				<%
					}
				%>
			
		</tbody>
</body>
</html>