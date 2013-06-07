package Login;

import java.sql.*; 


public class ConnectionManager { 
	static Connection con; 
	static String url; 
	
	public static Connection getConnection() { 
		try { 
			String url = "jdbc:postgresql://db.doc.ic.ac.uk/g1227118_u"; 
			// assuming "DataSource" is your DataSource name 
			Class.forName("org.postgresql.Driver"); 
			try { con = DriverManager.getConnection(url,
					"g1227118_u", "JwveU76nxZ"); 
			// assuming your SQL Server's username is "username" 
			// and password is "password" 
				} catch (SQLException ex) { 
					ex.printStackTrace(); } 
			} catch(ClassNotFoundException e) { 
				System.out.println(e); } 
		
		return con; } 
			
		
	}
