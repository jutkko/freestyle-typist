package Login;

import java.sql.*;

public class LoginService {

	public String authenticate(String userID, String password) {
		
		  try {
		 	    Class.forName("org.postgresql.Driver");
		        } catch (ClassNotFoundException e) {
		           // out.println("<h1>Driver not found:" + e + e.getMessage() + "</h1>" );
		        }
			   
		   try {
			
			    Connection conn = DriverManager.getConnection (
			    	"jdbc:postgresql://db.doc.ic.ac.uk/g1227118_u",
				"g1227118_u", "JwveU76nxZ" );
			
		        Statement stmt = conn.createStatement();
		         ResultSet rs;
		       
		        rs = stmt.executeQuery("SELECT userid FROM Typer WHERE userpassword = 'ilikepink'");		    //out.println( "<table border=1>" );
		        //    while ( rs.next() ) {
		          //      String id = rs.getString("UserID");
		          //      out.println("<tr><td>"+id+"</td></tr>" );
		           // }
			    //out.println( "</table>" );
		            
		       conn.close();
		        
		      // return "dd";
		       rs.next();
		     return rs.getString("userid");
		        } catch (Exception e) {
		          //  out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
		        }
			return password;
		
			
	}

}
