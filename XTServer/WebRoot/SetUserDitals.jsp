<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <form action="SetUserDitals" method="post">
    	token:<input type="text" name="token"><br>
    	picurl:<input type="text" name="picurl"><br>
    	nickname:<input type="text" name="nickname"><br>
    	name:<input type="text" name="name"><br>
    	age:<input type="text" name="age"><br>
    	sex:<input type="text" name="sex"><br>
    	school:<input type="text" name="school"><br>
    	college:<input type="text" name="college"><br>
    	email:<input type="text" name="email"><br>
    	phone:<input type="text" name="phone"><br>
    	submit:<input type="submit" value="submit"><br>
    </form>
  </body>
</html>
