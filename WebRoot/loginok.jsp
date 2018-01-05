<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>注册成功</title>
    <script type="text/javascript">
    function countDown(secs, surl) {
        //alert(surl); 
        var jumpTo = document.getElementById('jumpTo');
        jumpTo.innerHTML = secs;
        if (--secs > 0) {
            setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
        } else {
            location.href = surl;
        }
    }
    </script>
</head>

<body><h1>登录失败，无此用户或密码错误！！！ <span id="jumpTo">3</span>秒后自动跳转到登录页面</h1>
    <script type="text/javascript">
    countDown(3, './login.jsp');
    </script>
</body>

</html>