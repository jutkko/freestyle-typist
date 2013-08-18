
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Login.UserBean" import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/jquery.gritter.js"></script>

<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery.gritter.css" />


<title>Main</title>
</head>

<%
Object userObject = session.getAttribute("currentSessionUser");
if (userObject == null) {
	response.sendRedirect("test.jsp");
	return;
}
UserBean user = (UserBean) userObject ;
%>

<body>
  
	<div data-role="page" id="page1">
	
		<div data-role="content">
            <div id="panelshiding">
			<!-- Panels -->
			<a class="pull-right" href="#defaultpanel" data-role="button"
				data-inline="true" data-icon="bars"> M </a> 
				
 				<a class="pull-right" href="#friendspanel" data-role="button"
				data-inline="true" data-icon="search"> F </a>
							
				<a class="pull-right" href="#notificationpanel" data-role="button"
				data-inline="true" data-icon="alert"> N </a>
<br>
<br>
<br>
</div>
			<!-- Home -->
			<div id="content" class="wrapper wrapper-style1 wrapper-first">

			</div>
		</div>


		<!-- defaultpanel  -->
		
		<div data-role="panel" id="defaultpanel" data-position="right"
			data-display="overlay" data-theme="c">

			<!-- Stuff in default panel -->
			<div class="ui-panel-inner">
				<div data-role="listview">
					<li><a href="#userLogged.jsp" class="navlink-home"> Home </a></li>
					<li><a href="#practiceMode.jsp" class="navlink"> Practice </a></li>
					<li><a href="#rankings.jsp" class="navlink"> Rankings </a></li>
					<li><a id="logout" href="logout" class="navlink"> Logout </a></li>
				</div>
			</div>
			<!-- /content wrapper for padding -->

		</div>
		<!-- /defaultpanel -->
		<div data-role="panel" id="friendspanel" data-position="right"
			data-display="overlay" data-theme="c">
			
		</div>

		<!-- notificationpanel  -->
		<div data-role="panel" id="notificationpanel" data-position="right"
			data-display="overlay" data-theme="c">

		</div>
		<!-- /notificationpanel -->

	</div>

<script>
$.extend($.gritter.options, { 
  position: 'bottom-left', // defaults to 'top-right' but can be 'bottom-left', 'bottom-right', 'top-left', 'top-right' (added in 1.7.1)
fade_in_speed: 'medium', // how fast notifications fade in (string or int)
fade_out_speed: 4000, // how fast the notices fade out
time: 4000 // hang on the screen for...
});
</script>
<script>
</script>
<script>

</script>



	<script type="text/javascript">
	    		var On = {};

	        On.socket = null;

	        On.connect = (function(host) {
	            if ('WebSocket' in window) {
                On.socket = new WebSocket(host);
	            } else if ('MozWebSocket' in window) {
	                On.socket = new MozWebSocket(host);
	            } else {
	                               return;
            }
	
            On.socket.onopen = function () {
	                
	                        var msg = "<%= user.getUsername() %>";           
	                        On.sendMessage(msg);
	                        
	                    
                 };
	
	            On.socket.onclose = function () {
	           //     document.getElementById('test').onclick = null;
                 };
	
                 On.socket.onmessage = function (message) {
    	            	if (message.data.substring(0, 3) == "off") {
    	            		 var name = message.data.substring(3, message.data.length);
     	            	  $.gritter.add({
                 		  	title:  name + " has exited the game! :(",
                 		  		  	sticky: false,
                 		  	time: ''
                 		  });
    	            	} else if (message.data.substring(0,3) == "add") {
    	            	  var name = message.data.substring(3, message.data.length);
    	            	  $.gritter.add({
                		  	title: name,
                		  		  	sticky: false,
                		  	time: ''
                		  });
    	            	  
    	            	 // alert (name);
    	            	} else if (message.data.substring(0,3) == "acc") {
    	            	  var name = message.data.substring(3, message.data.length);
    	            	  $.gritter.add({
                		  	title: name,
                		  		  	sticky: false,
                		  	time: ''
                		  });
    	            	  
    	            	   }  else if (message.data.substring(0,3) == "cha") {
       	            	  var name = message.data.substring(3, message.data.length);
      	            	  $.gritter.add({
                  		  	title: name,
                  		  		  	sticky: false,
                  		  	time: ''
                  		  });
      	            	  
      	            	   } else if (message.data.substring(0,3) == "sco") {
         	            	  var name = message.data.substring(3, message.data.length);
        	            	  $.gritter.add({
                    		  	title: name,
                    		  		  	sticky: false,
                    		  	time: ''
                    		  });
        	            	  
        	            	   }else if (message.data.substring(0,3) == "upd") {
           	            	  var name = message.data.substring(3, message.data.length);
          	            	  // updateOpponentProgresss();
          	            	   //alert(m);
          	            	   if (name == "100") {
          	            	     $("#progressBarOpponent").width(name + "%");
          	            	     //$("#textbox").attr("disabled", true);
          	            	   //  spaces++;
//           	            	     $bar.width(100*12);
          	            	   //   var time = new Date().getTime() - timer;
          	            	   //   $("#progressBar").text(Math.round(spaces / (time/1000/60)) + " wpm");
          	            	         document.getElementById("theText").innerHTML = p;
//           	            	       // submitButton.attr("disabled", true);
          	            	        $("#textbox").attr("disabled", true);
									/* clearInterval(speedCounter); */
//           	            	      //  document.getElementById("speed").innerHTML = "  ";
////            	            	        resultMessage.text("Congratulations, you have just finished a game of our WebApp! Here is some data:");
          	            	        $("#resultMessageSecond").text("You lost by such a narrow margin !!! Try harder next time!!!");
          	            	        $("#panelshiding").show();
          	            	        
          	            	        //        var time = new Date().getTime() - timer;
//           	            	        //On.socket.send(100);
          	            	  //      var table = document.getElementById('resultTable');
          	            	    //    table.rows[0].cells[1].innerHTML = Math.round(time / 1000) + " seconds";
          	            	      //  table.rows[1].cells[1].innerHTML = Math.round(words / (time/1000/60));
//           	            	       //table.rows[2].cells[1].innerHTML = correct/(correct+incorrect)*100 + "%";
          	            	       // $("#resultTable").show();
          	            	        //$("#source").show();
          	            	   } else {
          	            	  $("#progressBarOpponent").width(name + "%");}
          	            	  //alert("a");
              	            	  
              	            	   }else if (message.data.substring(0,3) == "rea") {
                 	            	  $("#content").load("challengeModeRealTime.jsp");
                    	            	  
                    	            	   } else
    	            	{
                		   $.gritter.add({
                		  	title: message.data + " has come online!",
                		  		  	sticky: false,
                		  	time: ''
                		  });
    	            	}
    	            };
    	        });
    	
	
        On.initialize = function() {
	            if (window.location.protocol == 'http:') {
	                On.connect('ws://' + '129.31.228.28:8080' + '/FTTworking/online');
	            } else {
	                On.connect('wss://' + '129.31.228.28:8080'	 + '/FTTworking/online');
	            }
	        };
	
	        On.sendMessage = (function(msg) {
	            
	                On.socket.send(msg);
	                	            
	        });
	
	        On.initialize();
	
  </script>

 	<script src="js/bootstrap.min.js"></script>
 	<!-- <script type="text/javascript" src="js/jquery.pnotify.min.js"></script> -->
	<script src="js/userLogged.js"></script>

	
</body>
</html>