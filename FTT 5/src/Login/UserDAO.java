package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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

  public static String challengeFriends(String[] challengeFriends, int challengeCount, String user, int passage) {
    Statement stmt = null;
        
    challengeCount++;
    
     Date currentDatetime = new Date(System.currentTimeMillis());  
          //java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());  
          java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
          String text = null;
    try { 
      //connect to DB 
      currentCon = ConnectionManager.getConnection(); 
//      PreparedStatement stmts=currentCon.prepareStatement("INSERT INTO challenges VALUES ('" 
//          + challengeCount + "', '" + 2 + "', '" + "?" +"')");
      PreparedStatement stmts=currentCon.prepareStatement("INSERT INTO challenges VALUES (?, ?, ?)");
      stmts.setInt(1, challengeCount);
      stmts.setInt(2, passage);
      stmts.setTimestamp(3, timestamp);
//      rs = stmt.executeQuery("select createDateTime from challenges where no = 2");
//        rs.next();
//        Timestamp k = rs.getTimestamp(1);
//        Date now = new Date();
//        System.out.println(now);
//        System.out.println(k);
//        System.out.println(now.getTime() - k.getTime());
//        
//        k.getTime();
      stmts.execute();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery("Select text from passage where number = " + passage);
      rs.next();
      text = rs.getString(1);
      stmt.executeUpdate("INSERT INTO challengers VALUES ('" 
          + user + "', " + challengeCount + ", " + "0)" );
      for (String challengeFriend : challengeFriends) {
        stmt.executeUpdate("INSERT INTO challengers VALUES ('" 
            + challengeFriend + "', " + challengeCount + ", " + "0)" );
      }
      
      
    } 
       
    
      catch (Exception ex) { 
        System.out.println(ex); 
                  System.out.println("here");
        } //some exception handling 
    
      finally { 
        if (stmt != null) { try { stmt.close(); } 
        catch (Exception e) {} stmt = null; } 
        if (currentCon != null) { 
          try { currentCon.close(); } 
          catch (Exception e) { } currentCon = null; } }
    return text;
      
       }

		private static void insertUserDetails(UserBean bean, Statement stmt) throws SQLException {
			rs = stmt.executeQuery("SELECT friend FROM userdata WHERE userid='" +
					bean.getUsername() + "'");
			while (rs.next()) {
				bean.addFriends(rs.getString(1));
			}
			
			rs = stmt.executeQuery("SELECT friendwaiting FROM friendsWaiting WHERE userid='" +
					bean.getUsername() + "'");
			while (rs.next()) {
				bean.addWaitingFriends(rs.getString(1));
			}
			
			rs = stmt.executeQuery("SELECT friendrequest FROM friendRequests WHERE userid='" +
					bean.getUsername() + "'");
			while (rs.next()) {
				bean.addFriendRequest(rs.getString(1));
			}
			
			rs = stmt.executeQuery("SELECT * FROM userstats WHERE userid='" +
					bean.getUsername() + "'");
			rs.next();
			bean.setPhoneType((rs.getString(1)));
			bean.setInputMethod(rs.getString(2));
			bean.setDisplayName(rs.getString(3));
			bean.setAvgSpeed(rs.getInt(4));
			bean.setRacesCompleted(rs.getInt(5));
			bean.setRacesWon(rs.getInt(6));
			bean.setBestSpeed(rs.getInt(7));
			bean.setTotalWordsTyped(rs.getInt(8));
			
			rs = stmt.executeQuery("SELECT challengeno FROM challengers WHERE userid='" +
          bean.getUsername() + "'");
      while (rs.next()) {
        ResultSet rs2;
        int challengeKey = rs.getInt(1);
        rs2 = stmt.executeQuery("SELECT * FROM challenges WHERE no = " +
            challengeKey);
        rs2.next();
        int passage = rs2.getInt(2);
        Timestamp createTime = rs2.getTimestamp(3);
        ResultSet rs4;
        rs4 = stmt.executeQuery("SELECT text FROM passage WHERE number = " +
            passage);
        rs4.next();
        String text = rs4.getString(1);
        ResultSet rs3;
        rs3 = stmt.executeQuery("SELECT * FROM challengers WHERE challengeno = " +
            challengeKey);
        HashMap<String, Float> map = new HashMap<String, Float>();
        while (rs3.next()) {
          String participant = rs3.getString(1);
          float wpm = rs3.getFloat(3);
          map.put(participant, wpm);
        }
        Challenge challenge = new Challenge(passage, map, challengeKey, text, createTime);
        bean.currentChallenges.add(challenge);
      }
			
			
		}
		
		public static UserBean getUserBean(String username) {
			Statement stmt = null;
			UserBean bean = new UserBean();
			bean.setUserName(username);
			String searchQuery = "select * from users where username='"
								+ username + "'"; 
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
			return bean;
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
				
				if (bean.getDisplayName() == null) {
				stmt.executeUpdate("INSERT INTO userstats VALUES('" 
						+ bean.getPhoneType() + "', '" + bean.getInputMethod() + 
						"', null, 0, 0, 0, 0, 0, '" + username + "')");
				} else {
					stmt.executeUpdate("INSERT INTO userstats VALUES('" 
							+ bean.getPhoneType() + "', '" + bean.getInputMethod() + 
							 "', '" + bean.getDisplayName() + "', 0, 0, 0, 0, 0, '" + username + "')");
				}
							
				
				//insertUserDetails(bean, stmt);
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


		public static boolean addFriend(UserBean bean, String friend, Map<String, Object> mapForReq) {
			if (bean.friends.contains(friend)) {
			  System.out.println(friend);
			  mapForReq.put("message", "You are already friends!");
				return false;
			}
			if (bean.getUsername().equals(friend)) {
			  System.out.println("Adding myself");
			  mapForReq.put("message", "You cannot add yourself!");
        return false;
			}
			Statement stmt = null;
			String username = bean.getUsername();
			boolean added;
			try { 
				//connect to DB 
				currentCon = ConnectionManager.getConnection(); 
				stmt=currentCon.createStatement(); 
				stmt.executeUpdate("INSERT INTO friendsWaiting VALUES ('" 
						+ username + "', '" + friend + "')");
								
				stmt.executeUpdate("INSERT INTO friendRequests VALUES ('" 
						+ friend + "', '" + username + "')");
				added = true;
				} 
				 
			
				catch (Exception ex) {
	        mapForReq.put("message", "Adding failed!");
					added = false;
					} //some exception handling 
			
				finally { 
					if (stmt != null) { try { stmt.close(); } 
					catch (Exception e) {} stmt = null; } 
					if (currentCon != null) { 
						try { currentCon.close(); } 
						catch (Exception e) { } currentCon = null; } }
				return added;
			   }


		public static void confirmFriend(UserBean bean, String friend) {
			Statement stmt = null;
			String username = bean.getUsername();
			
			try { 
				//connect to DB 
				currentCon = ConnectionManager.getConnection(); 
				stmt=currentCon.createStatement(); 
				stmt.executeUpdate("DELETE FROM friendsWaiting WHERE userid='" +
						friend + "' AND friendwaiting='" + username + "'");
								
				stmt.executeUpdate("DELETE FROM friendRequests WHERE userid='" +
					username + "' AND friendrequest='" + friend + "'");
				
				stmt.executeUpdate("INSERT INTO userdata VALUES ('" 
						+ username + "', '" + friend + "')");
				
				stmt.executeUpdate("INSERT INTO userdata VALUES ('" 
						+ friend + "', '" + username + "')");
				
				} 
				 
			
				catch (Exception ex) { 
					System.out.println(ex); 
										
					} //some exception handling 
			
				finally { 
					if (stmt != null) { try { stmt.close(); } 
					catch (Exception e) {} stmt = null; } 
					if (currentCon != null) { 
						try { currentCon.close(); } 
						catch (Exception e) { } currentCon = null; } }
				
			   }
			
    public static void updateScore(UserBean bean, float score, int key) {
      Statement stmt = null;
      
      try { 
        currentCon = ConnectionManager.getConnection(); 
        stmt=currentCon.createStatement();
        
        stmt.executeUpdate("UPDATE challengers SET currentwpm = " + score + " WHERE userid = '" + bean.getUsername() +
            "' AND challengeno = " + key);
                
        
        } 
                
        catch (Exception ex) { 
//          JOptionPane.showMessageDialog(null,"Challenge expired!"
//             ,"Challenge expired",JOptionPane.WARNING_MESSAGE);
          System.out.println(ex); 
                    
          } //some exception handling 
      
        finally { 
          if (stmt != null) { try { stmt.close(); } 
          catch (Exception e) {} stmt = null; } 
          if (currentCon != null) { 
            try { currentCon.close(); } 
            catch (Exception e) { } currentCon = null; } }
      
    }
    public static LinkedList<UserRank> fillAllUsers(String rankBy) {
      Statement stmt = null;
      LinkedList<UserRank> rank = new LinkedList<UserRank>();
      
      try { 
        currentCon = ConnectionManager.getConnection(); 
        stmt=currentCon.createStatement();
        rs = stmt.executeQuery("SELECT userid, " + rankBy + " from userstats order by " + rankBy + " desc");
        while (rs.next()) {
          
          rank.add(new UserRank(rs.getString(1), rs.getInt(2)));
//          UserBean bean = new UserBean();
//          allUsers.add(bean);
//          bean.setPhoneType((rs.getString(1)));
//          bean.setInputMethod(rs.getString(2));
//          bean.setDisplayName(rs.getString(3));
//          bean.setAvgSpeed(rs.getInt(4));
//          bean.setRacesCompleted(rs.getInt(5));
//          bean.setRacesWon(rs.getInt(6));
//          bean.setBestSpeed(rs.getInt(7));
//          bean.setTotalWordsTyped(rs.getInt(8));
//          bean.setUserName(rs.getString(9));
          
        }
      
        
      } 
                
        catch (Exception ex) { 
          System.out.println(ex); 
          } //some exception handling 
      
        finally { 
          if (stmt != null) { try { stmt.close(); } 
          catch (Exception e) {} stmt = null; } 
          if (currentCon != null) { 
            try { currentCon.close(); } 
            catch (Exception e) { } currentCon = null; } 
}
      return rank;      
    
      
    }

    public static LinkedList<UserRank> fillAllPhoneOrInput(String rankBy, String phoneOrInput) {
      Statement stmt = null;
      LinkedList<UserRank> rank = new LinkedList<UserRank>();
      
      try { 
        currentCon = ConnectionManager.getConnection(); 
        stmt=currentCon.createStatement();
        rs = stmt.executeQuery("SELECT " + phoneOrInput + ", avg(" + rankBy + ") from userstats group by " + phoneOrInput + " order by avg(" + rankBy + ") desc");
        while (rs.next()) {
          
          rank.add(new UserRank(rs.getString(1), rs.getInt(2)));
          
        }
      
        
      } 
                
        catch (Exception ex) { 
          System.out.println(ex); 
          } //some exception handling 
      
        finally { 
          if (stmt != null) { try { stmt.close(); } 
          catch (Exception e) {} stmt = null; } 
          if (currentCon != null) { 
            try { currentCon.close(); } 
            catch (Exception e) { } currentCon = null; } 
}
      return rank;      
    
      
    }

			
public static void expireChallenge(Challenge expiredChallenge,
    HashMap<String, UserBean> onlineUsers) {
Statement stmt = null;
  
        
    try { 
      currentCon = ConnectionManager.getConnection(); 
      stmt=currentCon.createStatement();
      stmt.executeUpdate("DELETE FROM challenges where no = " + expiredChallenge.key);
              
      } 
              
      catch (Exception ex) { 
        System.out.println(ex); 
        System.out.println("here2");
        } //some exception handling 
    
      finally { 
        if (stmt != null) { try { stmt.close(); } 
        catch (Exception e) {} stmt = null; } 
        if (currentCon != null) { 
          try { currentCon.close(); } 
          catch (Exception e) { } currentCon = null; } }
    
    
    float i = 0;
    String currentBest = null;
    HashMap<String, Float> participantsAndScores = expiredChallenge.participantsAndScores;
    Set<String> keySet = participantsAndScores.keySet();
    for (String participant : keySet) {
      
      float wpm = participantsAndScores.get(participant);
      if (wpm > i) {
        i = wpm;
        currentBest = participant;
      }
      
    }
  
    Online.broadcastExpireChallenge(participantsAndScores.keySet(), currentBest, i);
    int countParticipants = 0;
    for (String participant : keySet) {
      Float score = participantsAndScores.get(participant);
      if (score > 0)
        countParticipants++;
      
    }
    
    for (String participant : keySet) {
      
      try { 
        currentCon = ConnectionManager.getConnection(); 
        stmt=currentCon.createStatement();
        stmt.executeUpdate("DELETE FROM challengers where userid = '" + participant + "' and challengeno = " + expiredChallenge.key);
        rs = stmt.executeQuery("SELECT * FROM userstats where userid = '" + participant + "'");
        rs.next();
        String phonetype = rs.getString(1);
        String inputmethod = rs.getString(2);
        int avgspeed = rs.getInt(4);
        int racescompleted = rs.getInt(5);
        int raceswon = rs.getInt(6);  
        int bestspeed = rs.getInt(7);
        int totalwordstyped = rs.getInt(8);
        
        int passage = expiredChallenge.passage;
        rs = stmt.executeQuery("SELECT wordcount FROM passage where number = " + passage);
        rs.next();
        int wordcount = rs.getInt(1);
        
        Float score = participantsAndScores.get(participant);
        int newspeed = Math.round(score);
        bestspeed = Math.max(bestspeed, newspeed);
        if (participant.equals(currentBest) && countParticipants > 1) {
          raceswon++;
        }
                    
        int totalspeed = avgspeed * racescompleted;
        
        if (score > 0) {
          totalspeed += score;
          avgspeed = totalspeed / (racescompleted + 1);
          
          if (countParticipants > 1) {
            racescompleted++;}
          totalwordstyped += wordcount;
        }
        
        stmt.executeUpdate("UPDATE userstats SET avgspeed = " + avgspeed + ", racescompleted = " + racescompleted + ", raceswon = " + raceswon + ", bestspeed = " + bestspeed + ", totalwordstyped = " + totalwordstyped + " WHERE userid = '" + participant + "'" );
        
        if (onlineUsers.containsKey(participant)) {
          UserBean bean = onlineUsers.get(participant);
          bean.setAvgSpeed(avgspeed);
          bean.setBestSpeed(bestspeed);
          bean.setRacesCompleted(racescompleted);
          bean.setRacesWon(raceswon);
          bean.setTotalWordsTyped(totalwordstyped);
          
          Iterator<Challenge> iterator = bean.currentChallenges.iterator();
          while (iterator.hasNext()) {
              Challenge element = iterator.next();
              if (element.key == expiredChallenge.key) {
                  iterator.remove();
              }
          }
          
          
        }
        
      } 
                
        catch (Exception ex) { 
          System.out.println(ex); 
          System.out.println("here3");      
          } //some exception handling 
      
        finally { 
          if (stmt != null) { try { stmt.close(); } 
          catch (Exception e) {} stmt = null; } 
          if (currentCon != null) { 
            try { currentCon.close(); } 
            catch (Exception e) { } currentCon = null; } }
      
    }
    
       
  
  
}}
