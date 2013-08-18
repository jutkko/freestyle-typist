<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  
    import = "java.sql.*" import="Login.ConnectionManager" import="java.util.Random"
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>

<h1>Training Mode</h1>


<%

Connection currentCon = null; 
ResultSet rs = null; 
Statement stmt = null;
String passage = "";
String source = "";
int wordCount = 0;

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
	Random r = new Random();

 	int i = 0;
 	while(rs.next()){
 		i++;
 	}
	int index = r.nextInt(i);
 	
 	rs = stmt.executeQuery("SELECT * FROM passage");
 	for(int j = 0 ; j <= index ; j++) {
 		rs.next();
 	}
	passage = rs.getString("text");
	source = rs.getString("source");
	wordCount = rs.getInt("wordcount");
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
   
%>


<article class="container" id="playMode">
    <div class="row">
      <div class="8u">
        <header>
          <h1>  Welcome to our
            <br><p><strong> WebApp!! </strong></p></h1></br>
        </header>

        <button id="practiceMode" class="btn btn-primary
          btn-big btn-block"> Practice mode
        </button>

        <button class="btn btn-primary
          btn-big btn-block"> Challenge mode
        </button>

        <button class="btn btn-primary
          btn-big btn-block"> Quick match
        </button>

      </div>
    </div>
  </article>
  
  <div id="gameBox"> 
    <form class="form-horizontal">
      <p id="theText">
      <%= passage %>
  	  </p>
  	  
  	  <p id="source">
	  <i> Source: </i> <%= source %>
      </p>
      
      <input id="textbox" type="text" placeholder="What you see is what you type..." autocomplete="off" disabled="disabled">

      <button id="submitButton" type="button" class="btn btn-success" value="start" disabled="disabled"> 
      Check!
      </button>
      
    </form>
 </div>


<div id="countDown"></div>

<div id="speed"></div>


<div id="resultSection">
  <p id="resultMessage"></p>
  <table id="resultTable" class="table table-condensed">

    <tbody>
      <tr> 
        <td> Time taken </td>
        <td id="timeTaken"></td>
      </tr>

      <tr>
        <td> Words per minuteSSSS </td>
        <td id="wordsPerSecond"></td>
      </tr>

      <tr>
        <td> AccuraFFFcy </td>
        <td id="accuracy"></td>
      </tr>
    </tbody>

  </table>
</div>

<div id="home">
      <button href="#practiceMode.jsp" id="practiceMode" class="btn link"> Back </button>
</div>

<script>  
var p = "<%= passage %>"; 
var words = "<%= wordCount %>";
</script>
<script type="text/javascript" src="js/home.js"></script>

</html> 