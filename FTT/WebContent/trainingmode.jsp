<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.sql.*" import="Login.ConnectionManager" 
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="http://code.jquery.com/jquery.js"></script>

<h1>My First JavaScript</h1>


<%

Connection currentCon = null; 
ResultSet rs = null; 
Statement stmt = null;
String passage = "";

try { 
	//connect to DB 
	currentCon = ConnectionManager.getConnection(); 
	stmt=currentCon.createStatement(); 
	rs = stmt.executeQuery("SELECT passage FROM passages");
	rs.next();
	passage = rs.getString(1);
	} 
	 

	catch (Exception ex) { 
		
	} //some exception handling 

	finally { 
		if (stmt != null) { try { stmt.close(); } 
		catch (Exception e) {} stmt = null; } 
		if (currentCon != null) { 
			try { currentCon.close(); } 
			catch (Exception e) { } currentCon = null; }
	} 
   




%>

<p id="text">  
<%= passage %>
 </p> 

<input id="textbox" type="text" disabled="disabled">

<script>
var timer;
var p = "<%= passage %>";

function change(button)
{
if(button.value == "Start!")
	{
	button.value = "Submit!"
	timer = new Date().getTime();
	document.getElementById("textbox").disabled = false;
	}
else
	{
	check();
	}
}

function check()
{
 document.getElementById("text").value = document.getElementById("text").innerHTML;
var text =  document.getElementById("text").value;
var answer =  document.getElementById("textbox").value;

if(answer == p)
	{
	document.write("correct");
        var time = new Date().getTime() - timer;
	document.write(time/1000 + "seconds");
	}
}
</script>

<input type='button' id="button" onclick="change(this)" value="Start!"> 

</body>
</html> 