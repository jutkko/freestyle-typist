package Login;

	import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
	
	import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
	
	
	
	/**
	 * Example web socket servlet for chat.
	 */
	@WebServlet("/online")
	public class Online extends WebSocketServlet {
	
	    private static final long serialVersionUID = 1L;
      public static HashMap<String, String> pairs = new HashMap<String, String>();

      public static void broadcastRealTime(String username, String string)
      {
        for (ChatMessageInbound connection : connections) {
          if (username.equals(connection.nickname) || string.equals(connection.nickname)) {
            
          try {
                   
            CharBuffer buffer = CharBuffer.wrap("rea");
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }}
        
      }

	    public static void broadcastChallengeSent(String username, String[] challengeFriends)
      {
	      for (ChatMessageInbound connection : connections) {
          if (username.equals(connection.nickname)) {
            
          try {
            String m = "";
            for (String participant : challengeFriends) {
                if (m.equals("")) {m = participant;}   else {
                m += ", " + participant;}
            }
        
            CharBuffer buffer = CharBuffer.wrap("cha" + "Your challenge with " + m + " has been sent!  ");
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }}
        
        
      }
	    
	    public static void broadcastExpireChallenge(Set<String> participants, String currentBest, float i)
      {
	      for (ChatMessageInbound connection : connections) {
          if (participants.contains(connection.nickname)) {
            
          try {
            String m = "";
            for (String participant : participants) {
              if (!participant.equals(connection.nickname)) { 
                if (m.equals("")) {m = participant;}   else {
                m += ", " + participant;}
              }
            }
            String k = "";
            Integer winScore = Math.round(i);
            if (currentBest != null) k = currentBest + " won with " + winScore + "WPM";
                
            CharBuffer buffer = CharBuffer.wrap("cha" + "Your challenge with " + m + " has expired! " +
            		k );
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }}
        
      }

	    
	    public static void broadcastChallengeFriendScore(
          Set<String> participants, String username, float score1, boolean b)
      {
         
	      for (ChatMessageInbound connection : connections) {
          if (participants.contains(connection.nickname) && !connection.nickname.equals(username)) {
            
          try {
            CharBuffer buffer;
            if (b) {
              buffer = CharBuffer.wrap("sco" + username + " has just set a new high score of " + score1 + " WPM in a challenge with you!");
            }else {
              buffer = CharBuffer.wrap("sco" + username + " has just typed " + score1 + " WPM in a challenge with you!");
          
            }
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }
        }
      }

	    
	    public static void broadcastChallengeFriend(String challengeFriend,
          String username)
      {
	      for (ChatMessageInbound connection : connections) {
          if (connection.nickname.equals(challengeFriend)) {
            
          try {
                CharBuffer buffer = CharBuffer.wrap("cha" + username + " has valiantly challenged you! =O");
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }
        }
      }
	    
	    public static void broadcastAcceptedFriend(String friend, String username)
      {
        
        for (ChatMessageInbound connection : connections) {
          if (connection.nickname.equals(friend)) {
            
          try {
                CharBuffer buffer = CharBuffer.wrap("acc" + username + " has accepted your friend request!");
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }
        }
        
      }
	    public static void broadcastAddFriend(UserBean user, String friend)
      {
        for (ChatMessageInbound connection : connections) {
          if (connection.nickname.equals(friend)) {
            
          try {
            //String on = online ? "" : "off";
                CharBuffer buffer = CharBuffer.wrap("add" + user.getUsername() + " has sent you a friend request!");
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }
        }

        
      }
	    public static void broadcastOnline(UserBean user, boolean online) {
            for (ChatMessageInbound connection : connections) {
            	if (user.onlineFriends.contains(connection.nickname)) {
            		
            	try {
            		String on = online ? "" : "off";
                    CharBuffer buffer = CharBuffer.wrap(on + user.getUsername());
                    connection.getWsOutbound().writeTextMessage(buffer);
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                    System.out.println(ignore);
                }
            	}
            }
        }
	    
	    
//	    private static final String GUEST_PREFIX = "Guest";
//	
//	    private final AtomicInteger connectionIds = new AtomicInteger(0);
	    private final static Set<ChatMessageInbound> connections =
	            new CopyOnWriteArraySet<ChatMessageInbound>();
	
	    @Override
	    protected StreamInbound createWebSocketInbound(String subProtocol,
	            HttpServletRequest request) {
	        //return new ChatMessageInbound(connectionIds.incrementAndGet());
	    	return new ChatMessageInbound();
	    }
	
	    private final class ChatMessageInbound extends MessageInbound {
	
	    	private String nickname;
	       private boolean test = false;

//	        private ChatMessageInbound(int id) {
//	            this.nickname = GUEST_PREFIX + id;
//	        }
//	
	        @Override
	        protected void onOpen(WsOutbound outbound) {
	            connections.add(this);
//	            String message = String.format("* %s %s",
//                    nickname, "has joined.");
	            //broadcast(message);
	        }
	
	        @Override
	        protected void onClose(int status) {
	            connections.remove(this);
//	            String message = String.format("* %s %s",
//	                    nickname, "has disconnected.");
//	            broadcast(message);
	        }
	
	        @Override
	        protected void onBinaryMessage(ByteBuffer message) throws IOException {
	            throw new UnsupportedOperationException(
	                    "Binary message not supported.");
	        }
	
          @Override
          protected void onTextMessage(CharBuffer message) throws IOException {
              // Never trust the client
//              String filteredMessage = String.format("%s: %s",
//                      nickname, message.toString());
            if (test == false) {
            nickname = message.toString();
            test = true;
            } else {
            String opponent = pairs.get(nickname);
            
            broadcastFight(opponent, message.toString());
            }
              //broadcast("");
          }

	        private void broadcast(String message) {
	            for (ChatMessageInbound connection : connections) {
	                try {
	                    CharBuffer buffer = CharBuffer.wrap(message);
	                    connection.getWsOutbound().writeTextMessage(buffer);
	                } catch (IOException ignore) {
	                    ignore.printStackTrace();
	                    System.out.println(ignore);
	                }
	            }
	        }
	    }

      public void broadcastFight(String opponent, String string)
      {
        for (ChatMessageInbound connection : connections) {
          if (connection.nickname.equals(opponent)) {
            
          try {
            //String on = online ? "" : "off";
                CharBuffer buffer = CharBuffer.wrap("upd" + string);
                connection.getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                System.out.println(ignore);
            }
          }
        }
        
      }
	
	}