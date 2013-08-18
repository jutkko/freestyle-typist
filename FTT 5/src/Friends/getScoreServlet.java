package Friends;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Login.Challenge;
import Login.Online;
import Login.UserBean;
import Login.UserDAO;

/**
 * Servlet implementation class getScoreServlet
 */
@WebServlet("/getScore")
public class getScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float score1 = new Float(request.getParameter("score"));
		System.out.println(score1);
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("currentSessionUser");
		
		boolean expired = true;
		Challenge thisChallenge = null;
		Map<String, Object> map = new HashMap<String, Object>();
		for (Challenge challenge: bean.currentChallenges) {
			if (challenge.key == (Integer)session.getAttribute("challenge")) {
				thisChallenge = challenge;
				expired = false;
				break;
			}
			
		}
		if (expired) {
			System.out.println("Challenge expired!");
//			response.sendRedirect("userLogged.jsp");
			map.put("expired", true);
			write(response, map);
			return;
		}
		int key = thisChallenge.key;
		float currentScore = thisChallenge.participantsAndScores.get(bean.getUsername());
		float score;
		if (score1 > currentScore)
		{
		  score = score1;
		  Online.broadcastChallengeFriendScore(thisChallenge.participantsAndScores.keySet(), bean.getUsername(), score1, true);
		  map.put("improved", true);
		} else {
		  score = currentScore;
		  Online.broadcastChallengeFriendScore(thisChallenge.participantsAndScores.keySet(), bean.getUsername(), score1, false);
      map.put("improved", false);
		}
//		float score = score1 > currentScore ? score1 : currentScore; 
		
		UserDAO.updateScore(bean, score, key);
		
		HashMap<String, Float> ps = thisChallenge.participantsAndScores;
		@SuppressWarnings("unchecked")
    HashMap<String, UserBean> online = (HashMap<String, UserBean>)request.getServletContext().getAttribute("online users");
		//ps.put (bean.getUsername(), score);
		
		
		for (String participant : ps.keySet()) {
			
			
			if (online.containsKey(participant) ) {
				HashSet<Challenge> challenges = online.get(participant).currentChallenges;
				for (Challenge challenge : challenges) {
					if (challenge.key == key) {
						challenge.participantsAndScores.put(bean.getUsername(), score);
						break;
					}
				}
			}
			
		}
		map.put("expired", false);
    write(response, map);
//		response.sendRedirect("userLogged.jsp");
	}

  private void write(HttpServletResponse response, Map<String, Object> map) throws IOException
  {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new Gson().toJson(map));    
  }

}
