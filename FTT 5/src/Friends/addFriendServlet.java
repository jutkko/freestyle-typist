package Friends;

import java.io.IOException;
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
 * Servlet implementation class addFriendServlet
 */
@WebServlet("/addFriend")
public class addFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
		String friend = (String)request.getParameter("friendRequest");
		Map <String, Object> mapForReq = new HashMap<String, Object>();
		boolean added = UserDAO.addFriend(user, friend, mapForReq);
		if (added) {
			user.addWaitingFriends(friend);
			@SuppressWarnings("unchecked")
      HashMap<String, UserBean> onlineUsers = 
					(HashMap<String, UserBean>)
					request.getServletContext().getAttribute("online users");
			
			UserBean onlineFriend = onlineUsers.get(friend);
			if (onlineFriend != null) {
			onlineFriend.addFriendRequest(user.getUsername());
			}
			Online.broadcastAddFriend(user, friend);
		}
		mapForReq.put("isValid", added);
		write(response, mapForReq);
	}

  private void write(HttpServletResponse response, Map<String, Object> map) throws IOException
  {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new Gson().toJson(map));
  }

}
