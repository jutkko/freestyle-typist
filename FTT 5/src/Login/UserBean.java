package Login;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class UserBean {

	 private String username; 
	 
	 private String password;
	 
	 public List<String> friends = new LinkedList<String> ();
	 
	 public List<String> onlineFriends = new LinkedList<String> ();
	 
	 public List<String> waitingFriends = new LinkedList<String> ();
	 
	 public List<String> friendRequests = new LinkedList<String> ();
	 
	 public boolean valid; 
	 
	 public boolean taken;

	private String displayName = null;

	private String phoneType;

	private String inputMethod;

	private int avgSpeed;

	private int bestSpeed;

	private int racesWon = 0;

	private int racesCompleted = 0;

	private int totalWordsTyped = 0;
	 
	public HashSet<Challenge> currentChallenges = new HashSet<Challenge>();

	
	 
	 public void addOnlineFriend(String onlineFriend) {
		 onlineFriends.add(onlineFriend);
	 }
	 
	 public String getPassword() {
		 return password; }
	 
	 public String getPhoneType() {
		 return phoneType;
	 }
	 
	 public String getInputMethod() {
		 return inputMethod; } 
	 
	 public String getDisplayName() {
		 return displayName; } 
	 
	 public int getAvgSpeed() {
		 return avgSpeed; }
	 
	 public int getBestSpeed() {
		 return bestSpeed; }
	 
	 public int getRacesWon() {
		 return racesWon; }
	 
	 public int getRacesCompleted() {
		 return racesCompleted; }

	 public int getTotalWordsTyped() {
		 return totalWordsTyped; }
	 
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

	public void addFriendRequest(String friend) {
		friendRequests.add(friend);
		
	}

	public void removeFriendRequest(String friend) {
		friendRequests.remove(friend);
		
	}

	public void removeWaitingFriend(String friend) {
		waitingFriends.remove(friend);
		
	}

	public void setDisplayName(String parameter) {
		displayName = parameter;
		
	}

	public void setPhoneType(String parameter) {
		phoneType = parameter;
		
	}
	
	public void setInputMethod(String parameter) {
		inputMethod = parameter;
		
	}

	public void setAvgSpeed(int avgSpeed) {
		this.avgSpeed = avgSpeed;  
		
	}

	public void setRacesCompleted(int racesCompleted) {
		this.racesCompleted = racesCompleted;
		
	}

	public void setRacesWon(int int1) {
		racesWon = int1;
		
	}

	public void setBestSpeed(int int1) {
		bestSpeed = int1;
		
	}

	public void setTotalWordsTyped(int int1) {
		totalWordsTyped = int1;
		
	}

	public void removeOnlineFriend(String username2) {
		onlineFriends.remove(username2);
		
	}
	
	 public void addChallenge(Challenge newChallenge) {
	    currentChallenges.add(newChallenge);
	    
	  }

	
	
	 
	 
}
