<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.HashMap" import = "Login.UserBean"    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
</head>
<body>
<% 


Object onlineUsers = application.getAttribute("online users");
if (onlineUsers == null) {
	application.setAttribute("online users", new HashMap<String, UserBean>());
} 

if (session.getAttribute("currentSessionUser") != null) {
	response.sendRedirect("userLogged.jsp");
}


/*
if (session.getAttribute("login fail") != null) {
	out.println("Login failed!");
} else {
	out.println("Welcome!");
}
*/

%>
Welcome! 
<br>
<a href="loginPage.jsp"> Login </a>
<br> New User? 
<a href="register.jsp"> Register </a>

</body>
</html>