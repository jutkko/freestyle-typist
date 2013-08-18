package Login;

import java.sql.*; 


public class ConnectionManager { 
	static Connection con = null; 
	static String url = "";
	
	public static Connection getConnection() { 
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
			con = DriverManager.getConnection(uri, "g1227118_u", "JwveU76nxZ");
			if (con != null ) {
			System.out.println("Successfully connected to db.doc using " +
			"unauthenticated SSL.");
			}
		} catch (Exception ex) { 
					ex.printStackTrace();
		} 
	
		
		return con; 
		}
		
	}
