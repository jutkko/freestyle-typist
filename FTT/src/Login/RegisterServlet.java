package Login;

import java.io.IOException;
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
		 user.setUserName(request.getParameter("un")); 
		 user.setPassword(request.getParameter("pw"));
		 
		 user = UserDAO.register(user); 
		 
		 if (!user.isTaken()) 
		 
		 { HttpSession session = request.getSession(true);

		 session.setAttribute("currentSessionUser",user); 
		 
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


