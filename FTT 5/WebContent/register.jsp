<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
      <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
<title>Registration Page</title>
</head>
<body>
 
    <% 
	if (session.getAttribute("reg fail") != null) 
	{ out.println("Username already taken! Try again!"); }
	
	%> 
	
	<div class="span3">
      <h2>Sign Up</h2>
<form action="register" method = "post" data-ajax="false" >
        <label> Name </label>
          <input type="text" name="un" class="span3">
        <label> Password </label>
          <input type="password" name="pw" class="span3">
        <label> Display Name (optional) </label>
          <input type="text" name="displayname" class="span3">


<label for="phonetype" class="select"> Phone type </label>
<select name="phonetype" id="select-choice-min" data-mini="true">
    <option value="Android"> Android </option>
    <option value="iOS"> iOS </option>
    <option value="Windows Phone"> Windows Phone </option>  
    <option value="Symbian"> Symbian </option>
    <option value="Others"> Others </option>
</select> 

<!-- <label class="span3"> Phone type </label>
  <select class="span3" name="phonetype">
    <option value="Android"> Android </option>
    <option value="iOS"> iOS </option>
    <option value="Windows Phone"> Windows Phone </option>  
    <option value="Symbian"> Symbian </option>
    <option value="Others"> Others </option>
  </select> -->
  
<label for="inputmethod" class="select"> Input methods </label>
<select name="inputmethod" id="select-choice-min" data-mini="true">
	<option  value="TouchPal"> TouchPal </option>
	<option value="SwiftKey"> SwiftKey </option>
	<option value="Google"> Google </option>
	<option value="iOS built-in"> iOS built-in </option>
	<option value="Android built-in"> Android built-in </option>
    <option value="Windows Phone built-in"> Windows Phone built-in </option>
    <option value="Samsung built-in"> Samsung built-in </option>
    <option value="Others"> Others </option>
</select> 
          <br>
          <input type="submit" value="submit" class="btn btn-primary">
        <div class="clearfix"></div>
      </form>
      <button id="back" class="btn link" href="#test.jsp"> Back </button>
	</div>
  
	<script src="http://code.jquery.com/jquery.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>$(document).ready(function(){
$("button.link").on("click", function(e)
{
e.preventDefault();
var linkurl     = $(this).attr("href");
var linkhtmlurl = linkurl.substring(1, linkurl.length);

window.location = linkhtmlurl;
});
});</script>
</body>
</html>