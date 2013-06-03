<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Login.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
</head>
<%
UserBean user = (UserBean) session.getAttribute("currentSessionUser");
%>
<body>

Welcome <%=  user.getUsername()%> 
to the Game!

<br> 
Your friends are:
<br>
<% 
for (String friend : user.friends) {
	out.println(friend);
}

%>
<br> 
Friends awaiting confirmation:
<br>
<% 
for (String friend : user.waitingFriends) {
	out.println(friend);
}

%>

	<form action="addFriend" method = "post">

			Add Friend
			<input type="text" name="friend"/><br>		
			<input type="submit" value="submit">			

		</form>


<a href="logout"> Logout </a>


</body>
</html>