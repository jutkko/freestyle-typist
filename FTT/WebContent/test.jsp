<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.HashMap" import = "Login.UserBean"    %>


<% 
Object onlineUsers = application.getAttribute("online users");
if (onlineUsers == null) {
	application.setAttribute("online users", new HashMap<String, UserBean>());
} 




/* if (session.getAttribute("currentSessionUser") != null) {
	
//	response.sendRedirect("userLogged.jsp");
} else {
//	session.removeAttribute("loadUserLogged");
}
 */

%>
<!DOCTYPE html>
<html>
  <head>
    <title>Testing</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css">

  </head>

  <body>

<div data-role="page" id="page1">

  <div data-role="content">

    <!-- Home -->
    <div id="content" class="wrapper wrapper-style1 wrapper-first" >
      <header>
        <h1 id="welcomeMessage">  
  <a class="pull-left" href="#">
    <img src="img/welcomeicon2.png" class="img-rounded">
  </a>
        </h1>
      </header>
      <button href="#practiceMode.jsp" id="practiceMode" class="btn btn-primary
        btn-big btn-block link"> Practice mode
      </button>
      <button href="#loginPage.jsp" id="loginPage" class="btn btn-primary
        btn-big btn-block link-redirect"> Login
      </button>
      <button href="#register.jsp" id="signupPage" class="btn btn-primary
        btn-big btn-block link-redirect"> Sign up
      </button>
      <div id="panelshiding"></div>
    </div>
    

  </div>

</div>

    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/test.js"></script>

  </body>
</html>