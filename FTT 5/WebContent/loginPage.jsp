<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Login.UserBean" import = "java.util.HashMap" %>
<!DOCTYPE html>

<html>
	<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css">

		<title>Login Page</title>
		
	</head>

	<body>
	
	<div data-role="page" id="page2">

  <div data-role="content">

    <div class="wrapper wrapper-style1 wrapper-first" >

		<form action="login" method = "post" data-ajax="false">

			Please enter your username 		
			<input type="text" name="un"/><br>		
		
			Please enter your password
			<input type="password" name="pw">
			<br>
            <input type="submit" value="submit" class="btn btn-primary
        btn-big btn-block">
					
		</form>
		      <button id="back" class="btn btn-big btn-block link" href="#test.jsp"> Back </button>
		
		</div>
		
    </div>

  </div>
 

	<%
	Object onlineUsers = application.getAttribute("online users");
if (onlineUsers == null) {
	response.sendRedirect("Welcome.jsp");
} %>

    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <script>$(document).ready(function(){
$("button.link").on("click", function(e)
{
e.preventDefault();
var linkurl     = $(this).attr("href");
var linkhtmlurl = linkurl.substring(1, linkurl.length);

window.location = linkhtmlurl;
});
});</script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>
