<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  
    import = "java.sql.*" import="Login.ConnectionManager" import="java.util.Random"
    %>
<head>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.css" rel="stylesheet">

</head>

<%-- <%


Connection currentCon = null; 
ResultSet rs = null; 
Statement stmt = null;
String passage = "";
String source = "";
int wordCount = 0;
int charCount = 0;

try {
Class pgClass = Class.forName("org.postgresql.Driver");
} catch ( java.lang.ClassNotFoundException e ) {
System.out.println( "Could not find org.postgresql.Driver class " +
"- please check your classpath." );
System.out.println( e );
}

String uri = "jdbc:postgresql://db.doc.ic.ac.uk/g1227118_u?&ssl=true" +
			"&sslfactory=org.postgresql.ssl.NonValidatingFactory";

try {
	//connect to DB 
	currentCon = DriverManager.getConnection(uri, "g1227118_u", "JwveU76nxZ");
	if (currentCon != null ) {
	System.out.println("Successfully connected to db.doc using " +
	"unauthenticated SSL.");

	stmt=currentCon.createStatement();
	rs = stmt.executeQuery("SELECT text FROM passage");
	
	Integer num = (Integer) session.getAttribute("passage");
	System.out.println(num);
	
	if (num == null)
	{
		Random r = new Random();
	 	int size = 0;
	 	while(rs.next()){
	 	size++;
	 	}
		num = r.nextInt(size) + 1;
	} 
		
	
	rs = stmt.executeQuery("SELECT * FROM passage WHERE number = " + num);

	rs.next();
 	passage = rs.getString("text");
	source = rs.getString("source");
	wordCount = rs.getInt("wordcount");
	charCount = rs.getInt("charcount");
	} 
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
   
%> --%>

<!-- Game --> 

  <div id="gameBox"> 
    <form class="form-horizontal">
      <p id="theText">
      What you see is what you type...
  	  </p>
  	  
  	  <p id="source">
	  <i> Source: </i> J
      </p>
      
      <input id="textbox" type="text" class="input-large search-query" 	placeholder="What you see is what you type..." autocomplete="off" disabled="disabled">
      
    </form>
 </div>

<div id="countDown" align="center"></div>

<div class="container" align="center">
    <div class="progress progress-striped active">
        <div id="progressBar" class="bar" style="width: 0%;"></div>
    </div>
</div>

<div id="resultSection">
  <p id="resultMessage"></p>
  <table id="resultTable" class="table table-condensed">

    <tbody>
      <tr> 
        <td> Time taken </td>
        <td id="timeTaken"></td>
      </tr>

      <tr>
        <td> Words per minute </td>
        <td id="wordsPerSecond"></td>
      </tr>
    </tbody>

  </table>
</div>

<div>
      <button class="btn link" id="home"> Back </button>
</div>

<script>  
var p = "What you see is what you type..."; 
var words = "10";
var chars = "30";
</script>
<script src="js/game.js"></script>
