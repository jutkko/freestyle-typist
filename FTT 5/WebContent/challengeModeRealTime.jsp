<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  
    import = "java.sql.*" import="Login.ConnectionManager" import="java.util.Random"
    %>
<head>
 <link href="css/bootstrap.css" rel="stylesheet" media="screen">
 <link href="css/bootstrap-responsive.css" rel="stylesheet">
 
 <style>

</style>
</head>

<%

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
	Random r = new Random();

//  	int i = 0;
//  	while(rs.next()){
//  		i++;
//  	}
// 	int index = r.nextInt(i);
 	
 	rs = stmt.executeQuery("SELECT * FROM passage where number = 2");
 	rs.next();
//  	for(int j = 0 ; j <= index ; j++) {
//  		rs.next();
//  	}
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
   
%>


<!-- Home -->

  <article class="container" id="playMode">
    <div class="row">
      <div class="8u">

      </div>
    </div>
  </article>

<!-- Game --> 
  <div id="gameBox"> 
    <form class="form-horizontal">
      <p id="theText">
      <%= passage %>
  	  </p>
  	  
  	  <p id="source">
	  <i> Source: </i> <%= source %>
      </p>
      
      <input id="textbox" class="input-large search-query" type="text" placeholder="What you see is what you type..." autocomplete="off" disabled="disabled">
      
    </form>
 </div>

<div id="countDown" align="center"></div>


<!-- <div id="speed"> -->
<!-- </div> -->

<div class="container" align="center">
    <div  class="progress progress-striped active">
        <div id="progressBar" class="bar" style="width: 0%;"></div>
    </div>
</div>

<div class="container" align="center">
    <div class="progress progress-warning progress-striped">
        <div id="progressBarOpponent" class="bar" style="width: 0%;"></div>
    </div>
</div>



<!-- <div style="width: 100%;"> -->

<!-- <div id="speed" style="width: 50% ; float: left"> -->
<!-- </div> -->

<!-- <div id="progressBar" class="container" style="width: 50% ; float: right"> -->
<!--     <div class="progress progress-striped active"> -->
<!--         <div class="bar" style="width: 0%;"></div> -->
<!--     </div> -->
<!-- </div> -->

<!-- </div> -->


<div id="resultSection">
  <p id="resultMessage"></p>
  <p id="resultMessageSecond"></p>
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

<!--       <tr> -->
<!--         <td> Accuracy </td> -->
<!--         <td id="accuracy"></td> -->
<!--       </tr> -->
    </tbody>

  </table>
</div>

<script>  
var p = "<%= passage %>"; 
var words = "<%= wordCount %>";
var chars = "<%= charCount %>";
</script>
<script type="text/javascript" src="js/home.js"></script>

