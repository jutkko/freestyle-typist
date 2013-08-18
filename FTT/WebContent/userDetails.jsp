<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Login.UserBean" import = "java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<% response.setIntHeader("Refresh", 5); %>
<%
UserBean user = (UserBean) session.getAttribute("currentSessionUser");
%>

<body>

Your friends are : 

<% 
for (String friend : user.friends) {
	out.println(friend);
	HashMap<String, UserBean> onlineUsers = 
			(HashMap<String, UserBean>) application.getAttribute("online users");
	if (onlineUsers.containsKey(friend)) {
		out.println("(online)");
	}
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
<br>
Friend requests:
<br>
<% 
for (String friend : user.friendRequests) {
%>
<br> <a href="/servletName"> <%= friend %> </a>

<%
}
%>






</body>
</html>