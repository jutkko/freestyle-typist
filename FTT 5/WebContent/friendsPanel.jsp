<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Login.UserBean" import="java.util.HashMap"%>
<%
Object userObject = session.getAttribute("currentSessionUser");
if (userObject == null) {
	response.sendRedirect("test.jsp");
	return;
}
UserBean user = (UserBean) userObject ;
%>

		<!-- friendstpanel  -->
				<a class="pull-right" href="#friendspanel" data-role="button"
				data-inline="true" data-icon="search" data-iconpos="notext"></a> 

			<!-- Stuff in friends panel -->
			<div class="ui-panel-inner">

				<div data-role="listview">
					<li data-role="list-divider"> Friends </li>
					<fieldset data-role="controlgroup">
						<div class="ui-btn-inner">
							<!-- <form method="post" action="" id="mform" class="form-horizontal">
 <div class="btn-group btn-group-vertical" data-toggle="buttons-checkbox">
   <button type="button" name="left"   value="1" class="btn btn-primary">Left</button>
   <button type="button" name="middle" value="1" class="btn btn-primary">Middle</button>
   <button type="button" name="right"  value="1" class="btn btn-primary">Right</button>
 </div>
 <button type="submit" class="btn">Submit</button>
</form>	 -->		  
                <form id="mform">
                
              <div class="btn-group btn-group-vertical">
                               
              
 			<% @SuppressWarnings("unchecked")
		    HashMap<String, UserBean> onlineUsers = 
			 (HashMap<String, UserBean>) application.getAttribute("online users"); int i = 1; for (String friend : user.friends) 
			   { %>
			   <%System.out.println(friend); %>
                <% if (onlineUsers.containsKey(friend)) 
                {%>
                <div class="btn-group">
                  <button class="btn link" href="<%="#userInformation.jsp?username=" + friend %>" style="width: 150px;"><font color="green" > <%= friend %> </font></button>
                <button class="btn btn-warning" type="button" name="opponent" value="<%=friend %>"  data-toggle="buttons-checkbox"> Pick </button>
                </div>
<%-- 				  <label for="<%= "checkbox-" + i %>"> <font color="green"> <%= friend %> </font></label> 
 --%>			    <%} else
			    {%>
 			  <div class="btn-group">
                 <button class="btn link" href="<%="#userInformation.jsp?username=" + friend %>" style="width: 150px;"> <%= friend %> </button>
                <button class="btn btn-warning" type="button" name="opponent" value="<%=friend %>" data-toggle="buttons-checkbox"> Pick </button>
                </div>
<%-- 			      <label for="<%= "checkbox-" + i %>"> <%= friend %> </label> 
 --%>			    <%}
                
                  i++;%>
                  <br>
		    <% } %>		    
				<input type="Submit" value="Challenge" class="btn btn-danger btn-block">
		    </div>
		    </form>
		    
<!-- 		                    <form id="mform" action = "userInformation.jsp">
		                      <button class="btn span2" name="username" value ="Kobe" ><font color="green"> Kobe </font></button>
		    		    </form> -->
		    

<!-- <div class="btn-group">
                <button class="btn span2" type="button"> J </button>
                <button class="btn dropdown-toggle span1" data-toggle="buttons-checkbox"> Tick </button>
              </div>
				<a href="#demo-links" data-rel="close" data-role="button"
				data-theme="c" data-icon="grid" data-iconpos="right"> Challenge </a> -->
						</div>
					</fieldset>
                <li data-role="list-divider"> Add friends </li>
                
                
                
               
				<form id="addFriend">
				    <input type="text" id="friendRequest" name="friendRequest" /><br> 
					<input type="submit" value="Add friend" class="btn btn-success">
				</form>           
				
				
				         
					<div id="addFriendAlert">
                    </div>
				</div>
			</div>
			
			<script src="js/friendsPanel.js"></script>