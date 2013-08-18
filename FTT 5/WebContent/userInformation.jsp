<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "Login.UserBean" import = "Login.UserDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
</head>
<body>

<% 
UserBean user;
String username = request.getParameter("username");
if (username != null ) {
	 user = UserDAO.getUserBean(username);
	
} else {
	 user = (UserBean)session.getAttribute("currentSessionUser");
}
 %>

<% if (user.getDisplayName() != null ) {
%>

				<header>
				<h1 id="welcomeMessage">
  <font size="40"><%= user.getDisplayName() %></font>
				</h1>
				</header>

<% } else {%>
	<header>
	<h1 id="welcomeMessage">
	<font size="40"><%= user.getUsername() %></font>
					</h1>
				</header>

<% }%>
<font size="6"><b><%= user.getUsername() %></b></font> is my username
<br>
<font size="6"><b><%= user.getPhoneType() %></b></font> is the phone I use
<br>
<font size="6"><b><%= user.getInputMethod() %></b></font> is my input method
<br>
<font size="6"><b><%= user.getRacesCompleted() %></b></font> races I've finished
<br>
<font size="6"><b><%= user.getRacesWon() %></b></font> races I've won
<br>
<font size="6"><b><%= user.getAvgSpeed() %></b></font> is my average speed (WPM)
<br>
<font size="6"><b><%= user.getBestSpeed() %></b></font> is my best speed (WPM)  
<br>
<font size="6"><b><%= user.getTotalWordsTyped() %></b></font> words I've typed

</body>
</html>