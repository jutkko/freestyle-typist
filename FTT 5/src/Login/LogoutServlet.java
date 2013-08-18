package Login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("currentSessionUser");
		String username = user.getUsername();
		session.invalidate();
		@SuppressWarnings("unchecked")
		HashMap<String, UserBean> onlineUsers = 
				(HashMap <String, UserBean>) request.getServletContext().getAttribute("online users");
		onlineUsers.remove(username);
		for (String onlineFriend : user.onlineFriends) {
			UserBean onlineFriendBean = onlineUsers.get(onlineFriend);
			onlineFriendBean.removeOnlineFriend(username);
		}
		Online.broadcastOnline(user, false);
		response.sendRedirect("test.jsp");
	}
	



}
