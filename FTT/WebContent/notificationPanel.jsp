<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "Login.UserBean" import = "Login.Challenge" import = "java.util.HashSet" %>
    
<% Object userObject = session.getAttribute("currentSessionUser");
if (userObject == null) {
	response.sendRedirect("test.jsp");
	return;
}
UserBean user = (UserBean) userObject ;
%>

<!-- Stuff in notification panel -->
			<div class="ui-panel-inner">
				<div data-role="listview">
					<li data-role="list-divider">Friend requests</li>
					<fieldset data-role="controlgroup">
						<div class="ui-btn-inner">
						
					    <form id="confirmFriend">
						  <% int i = 1; for (String friend : user.friendRequests) 
						  { %>
						    <input type="checkbox" name="friend" id="<%= "checkbox-requests" + i %>" data-iconpos="right" value="<%= friend %>"> 
							    <label for="<%= "checkbox-requests" + i %>"> <%= friend %> </label>
				         <% i++;
				          } %>
				          <% if (!user.friendRequests.isEmpty()) 
				          {%>
                            <button type="submit" class="btn btn-success btn-block"> Accept </button>
                        <%} %>
					      </form>
						</div>
					</fieldset>
					
					<li data-role="list-divider"> Requests sent </li>
					  <% for (String friend : user.waitingFriends) 
					  {%>
					  <li> <%=friend %> </li>
                    <%}%>

					<li data-role="list-divider"> Current challenges </li>

<%  Object beanO = session.getAttribute("currentSessionUser");
UserBean bean = (UserBean) beanO;

    HashSet<Challenge> challenges  = bean.currentChallenges;
    System.out.println("two");
	int j = 1;
%>
<div data-role="controlgroup">
    <% 
    for (Challenge challenge : challenges) {
    	String c = "Challenge " + j;
    	%>
    	  <a class="link" href="<%="#challenge.jsp?challenge=" + challenge.key%>" data-role="button"><%=c %></a>
    	<%-- <br> 
    	<form action="challenge.jsp" method="get">
		 <button name="challenge" type="submit" value= <%= challenge.key %>><%= c %></button>
        </form> --%>
        

   <%  j++;}  %>
   </div>
   

				</div>
			</div>
			
<script src="js/notificationPanel.js"></script>