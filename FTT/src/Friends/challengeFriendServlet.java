package Friends;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Login.Challenge;
import Login.Online;
import Login.UserBean;
import Login.UserDAO;

/**
 * Servlet implementation class challengeFriendServlet
 */
@WebServlet("/challengeFriend")
public class challengeFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
		
		String[] challengeFriends = request.getParameterValues("json[]");
		if (challengeFriends == null) {
			response.sendRedirect("userLogged.jsp");
			return;
		}
    if (challengeFriends.length == 1 && user.onlineFriends.contains(challengeFriends[0])) {
      Online.pairs.put(user.getUsername(), challengeFriends[0]);
      Online.pairs.put(challengeFriends[0], user.getUsername());
      Online.broadcastRealTime(user.getUsername(), challengeFriends[0]);
      Online.broadcastChallengeSent(user.getUsername(), challengeFriends);
      Online.broadcastChallengeFriend(challengeFriends[0], user.getUsername());
      return;
    }
		Online.broadcastChallengeSent(user.getUsername(), challengeFriends);
		int passage = 2;
		ServletContext application = request.getServletContext();
		Object challengeCount = application.getAttribute("challenge count");
		if (challengeCount == null) {
			application.setAttribute("challenge count", 1);
		} else {
			application.setAttribute("challenge count", (Integer)challengeCount + 1);
		}
		
		Integer challengeCountInt = challengeCount == null ? 0 : (Integer)challengeCount; 
		
		
		String text = 
				UserDAO.challengeFriends(challengeFriends, challengeCountInt, user.getUsername(), passage);
		
		HashMap<String, Float> participantsAndScores = new HashMap<String, Float>();
		participantsAndScores.put(user.getUsername(), (float) 0);
		for (String challengeFriend : challengeFriends)  {
			participantsAndScores.put(challengeFriend, (float) 0);
			Online.broadcastChallengeFriend(challengeFriend, user.getUsername());
		}
		final Challenge newChallenge = new Challenge(passage, participantsAndScores, challengeCountInt + 1, text);
		user.addChallenge(newChallenge);
		
		for (String challengeFriend : challengeFriends)  {
			if (user.onlineFriends.contains(challengeFriend)) {
				@SuppressWarnings("unchecked")
        UserBean challengeBean = ((HashMap<String, UserBean>) application.
						getAttribute("online users")).get(challengeFriend);
				challengeBean.addChallenge(newChallenge);
			}
		}
		
	
		Timer timer = new Timer();
		@SuppressWarnings("unchecked")
    final HashMap<String, UserBean> onlineUsers = (HashMap<String, UserBean>)request.getServletContext().getAttribute("online users");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				
				UserDAO.expireChallenge(newChallenge, onlineUsers);
				//Online.broadcastExpireChallenge(newChallenge.participantsAndScores.keySet());
				
			}
			
		}, 180000);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
