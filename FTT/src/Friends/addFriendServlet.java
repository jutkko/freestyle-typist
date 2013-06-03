package Friends;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		UserDAO.addFriend(user, (String)request.getParameter("friend"));
		response.sendRedirect("userLogged.jsp");
	}

}
