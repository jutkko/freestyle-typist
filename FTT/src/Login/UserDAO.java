package Login;

import java.sql.*;

import javax.swing.JOptionPane;

public class UserDAO {
	
	static Connection currentCon = null; 
	static ResultSet rs = null; 
	
	public static UserBean login(UserBean bean) { 
		
		//preparing some objects for connection 
		Statement stmt = null;
		String username = bean.getUsername(); 
		String password = bean.getPassword(); 
		String searchQuery = "select * from users where username='"
							+ username + "' AND password='" + password + "'"; 
		
		// "System.out.println" prints in the console; Normally used to trace the process 
		
		System.out.println("Your user name is " + username); 
		System.out.println("Your password is " + password); 
		System.out.println("Query: "+searchQuery);
		
		try { 
			//connect to DB 
			currentCon = ConnectionManager.getConnection(); 
			stmt=currentCon.createStatement(); 
			rs = stmt.executeQuery(searchQuery); 
			boolean more = rs.next(); 
			// if user does not exist set the isValid variable to false 
			if (!more) { System.out.println("Sorry, you are not a registered " +
					"user! Please sign up first"); 
			bean.setValid(false); } 
			//if user exists set the isValid variable to true 
			else if (more) { 
					
			bean.setValid(true);
			System.out.println("Shouldnt happen");
			insertUserDetails(bean, stmt);
			} } 
			catch (Exception ex) { 
				System.out.println("Log In failed: An Exception has occurred! " + ex); 
				} //some exception handling 
			finally { 
				if (rs != null) 
				{ try { rs.close(); } 
				catch (Exception e) {} rs = null; }
				if (stmt != null) { try { stmt.close(); } 
				catch (Exception e) {} stmt = null; } 
				if (currentCon != null) { 
					try { currentCon.close(); } 
					catch (Exception e) { } currentCon = null; } } 
		   return bean; }

	
		private static void insertUserDetails(UserBean bean, Statement stmt) throws SQLException {
			rs = stmt.executeQuery("SELECT friend FROM userdata WHERE userid='" +
					bean.getUsername() + "'");
			while (rs.next()) {
				bean.addFriends(rs.getString(1));
			}
		}

		public static UserBean register(UserBean bean) {
			Statement stmt = null;
			String username = bean.getUsername(); 
			String searchQuery = "select * from users where username='"
								+ username + "'"; 
			
			// "System.out.println" prints in the console; Normally used to trace the process 
			
			System.out.println("Your sign-up user name is " + username); 
			System.out.println("Query: "+searchQuery);
			
			try { 
				//connect to DB 
				currentCon = ConnectionManager.getConnection(); 
				stmt=currentCon.createStatement(); 
				rs = stmt.executeQuery(searchQuery); 
				boolean more = rs.next(); 

				if (!more) { System.out.println("username not taken"); 
				bean.setTaken(false); 
				stmt.executeUpdate("INSERT INTO users VALUES ('" 
						+ username + "', '" + bean.getPassword() + "')");
				insertUserDetails(bean, stmt);
				} 
				
				else if (more) { 
					bean.setTaken(true); } } 
			
				catch (Exception ex) { 
					System.out.println("Registration failed: An Exception has occurred! " + ex); 
					} //some exception handling 
			
				finally { 
					if (rs != null) 
					{ try { rs.close(); } 
					catch (Exception e) {} rs = null; }
					if (stmt != null) { try { stmt.close(); } 
					catch (Exception e) {} stmt = null; } 
					if (currentCon != null) { 
						try { currentCon.close(); } 
						catch (Exception e) { } currentCon = null; } } 
			   return bean; }


		public static void addFriend(UserBean bean, String friend) {
			Statement stmt = null;
			String username = bean.getUsername();
			try { 
				//connect to DB 
				currentCon = ConnectionManager.getConnection(); 
				stmt=currentCon.createStatement(); 
				stmt.executeUpdate("INSERT INTO friendsWaiting VALUES ('" 
						+ username + "', '" + friend + "')");
				bean.addWaitingFriends(friend);
				
				} 
				 
			
				catch (Exception ex) { 
					System.out.println("Cannot add friend! " + ex); 
					JOptionPane.showMessageDialog(null,"Your 'friend' is not registered!"
							 ,"Friend failure",JOptionPane.WARNING_MESSAGE);
					} //some exception handling 
			
				finally { 
					if (stmt != null) { try { stmt.close(); } 
					catch (Exception e) {} stmt = null; } 
					if (currentCon != null) { 
						try { currentCon.close(); } 
						catch (Exception e) { } currentCon = null; } } 
			   }
			
		}
	
		

