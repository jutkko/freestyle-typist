package Login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
		
		 UserBean user = new UserBean(); 
		 String username = request.getParameter("un");
		 user.setUserName(username);
		 user.setPassword(request.getParameter("pw"));
		 String displayName = request.getParameter("displayname");
		 if (!displayName.trim().equals("")) {
			 user.setDisplayName(displayName);
		 }
		
		 user.setPhoneType(request.getParameter("phonetype"));
		 user.setInputMethod(request.getParameter("inputmethod"));
		 
		 
//		 if (request.getParameter("displayname") != null) {
//			 System.out.println("display is ");
//		 }
//		 
		
		 user = UserDAO.register(user); 
		 
		 if (!user.isTaken()) 
		 
		 { HttpSession session = request.getSession(true);
		 
		 ServletContext application = request.getServletContext();
		 
		 session.setAttribute("currentSessionUser",user); 
		 
		 HashMap<String, UserBean> onlineUsers = 
				 (HashMap<String, UserBean>) application.getAttribute("online users");
		
		onlineUsers.put(username, user);

		 response.sendRedirect("userLogged.jsp"); 
		 
		 //logged-in page 
		 }
		 
		 else {
			 //HttpSession session = request.getSession(true);
			 //session.setAttribute("reg fail", true);
			 JOptionPane.showMessageDialog(null,"Oops username already taken!! Try again=)"
					 ,"Registration failure",JOptionPane.WARNING_MESSAGE);
			 response.sendRedirect("register.jsp"); 
		 }
		 
		 //error page 
		 } 
		 
		 catch (Throwable theException) { System.out.println(theException); } } 
		 
	}


