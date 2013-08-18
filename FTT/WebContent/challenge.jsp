<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.Set" import = "Login.UserBean" import = "Login.Challenge" import = "java.util.HashMap"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
<div id="challengeInfo">
<h1>Challenge mode</h1>

<% 
UserBean bean = (UserBean) session.getAttribute("currentSessionUser");
int j = new Integer(request.getParameter("challenge"));
session.setAttribute("challenge", j);

//int i = 1;
for (Challenge challenge : bean.currentChallenges) {
	if (challenge.key == j) {
 %>
<% session.setAttribute("passage", challenge.passage); %> 
 <%-- Passage : <%= challenge.text %><br> --%>
 Created on :<%= challenge.createTime %>
 <% 
 Set<String> participants = challenge.participantsAndScores.keySet();
 
 for (String participant : participants ) { %>
 	<br>
 	<%= participant %> : score is <%= challenge.participantsAndScores.get(participant) %>
 <%}
 %>
 
 
 <%break;}} %>
 </div>
  
  <div id="submitForm" style="display: none;">
 </div>
 
 <div id="content1"></div>
 
<script> $("#content1").load("game.jsp"); </script>


</body>