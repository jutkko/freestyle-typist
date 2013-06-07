<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
</head>
<body>
<form action="register" method = "post">
    <!-- 
    <% 
	if (session.getAttribute("reg fail") != null) 
	{ out.println("Username already taken! Try again =)"); }
	
	%> 
	 -->
	
	Sign up!
	<br>
	Username 		
	<input type="text" name="un"/><br>		
		
	Password
	<input type="password" name="pw"/>
		
	<input type="submit" value="submit">			
		
		
</form>
</body>
</html>