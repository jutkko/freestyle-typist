package Login;

import java.util.LinkedList;
import java.util.List;

public class UserBean {

	 private String username; 
	 
	 private String password;
	 
	 public List<String> friends = new LinkedList<String> ();
	 
	 public List<String> waitingFriends = new LinkedList<String> ();
	 
	 public boolean valid; 
	 
	 public boolean taken;
	 
	 public String getPassword() {
		 return password; } 
	 
	 public void addFriends(String friendId) {
		 friends.add(friendId);
	 }
	 
	 public void addWaitingFriends(String friendId) {
		 waitingFriends.add(friendId);
	 }
	 
	 public void setPassword(String newPassword)
	 { password = newPassword; } 
	 
	 public String getUsername() 
	 	 { return username; } 
	 
	 public void setUserName(String newUsername) 
	 { username = newUsername; }

	 public boolean isValid() 
	 { return valid; } 
	 
	 public void setValid(boolean newValid) 
	 { valid = newValid; }

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean b) {
		taken = b;		
	} 
	 
	 
}
