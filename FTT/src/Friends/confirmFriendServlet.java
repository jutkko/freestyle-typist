package Friends;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Login.Online;
import Login.UserBean;
import Login.UserDAO;

/**
 * Servlet implementation class confirmFriendServlet
 */
@WebServlet("/confirmFriend")
public class confirmFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
		String[] friends = request.getParameterValues("friend");
		Map<String, Object> map = new HashMap<String, Object>();
		if (friends == null) {
		  map.put("isValid", false);
		  write(response, map);
			return;
		}
		for (String friend : friends) {
		UserDAO.confirmFriend(user, friend);
		
		user.addFriends(friend);
		Online.broadcastAcceptedFriend(friend, user.getUsername());
		user.removeFriendRequest(friend);
    System.out.println("here2");

		@SuppressWarnings("unchecked")
    HashMap<String, UserBean> onlineUsers = 
				(HashMap<String, UserBean>)
				request.getServletContext().getAttribute("online users");
			
		UserBean onlineFriend = onlineUsers.get(friend);
		if (onlineFriend != null) {
			String username = user.getUsername();
			onlineFriend.addFriends(username);
			onlineFriend.removeWaitingFriend(username);
		}
		}
		map.put("isValid", true);
		write(response, map);
	}

  private void write(HttpServletResponse response, Map<String, Object> map) throws IOException
  {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new Gson().toJson(map));
  }

}
