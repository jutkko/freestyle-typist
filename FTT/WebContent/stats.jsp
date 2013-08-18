<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "Login.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
</head>
<body>

<% UserBean user = (UserBean)session.getAttribute("currentSessionUser");

%>

Name: 
<br>

Phone Type: 
<br>

Input Method: 
<br>
Races Completed:
<br>
Races Won:
<br>
Average Speed (wpm):
<br>
Best Speed (wpm):  
<br>
Total Words Typed: 	

</body>
</html>