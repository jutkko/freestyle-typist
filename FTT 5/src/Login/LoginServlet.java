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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try { UserBean user = new UserBean(); 
		 
		 String username = request.getParameter("un");
		user.setUserName(username); 
		 
		 user.setPassword(request.getParameter("pw"));
		 
		 user = UserDAO.login(user); 
		 if (user.isValid()) 
		 
		 { HttpSession session = request.getSession(true);

		 session.setAttribute("currentSessionUser",user); 
		 
		 
		 ServletContext application = request.getServletContext();
		 
		@SuppressWarnings("unchecked")
    HashMap<String, UserBean> onlineUsers = 
				 (HashMap<String, UserBean>) application.getAttribute("online users");
		
		onlineUsers.put(username, user);
		
		for (String friend : user.friends) {
    	if (onlineUsers.containsKey(friend)) {
    		user.addOnlineFriend(friend);
    		UserBean friendBean = onlineUsers.get(friend);
    		friendBean.addOnlineFriend(username);
    	}
		}
		
		
		Online.broadcastOnline(user, true);
session.setAttribute("loadUserLogged", true);
		System.out.println("got here");	 
		response.sendRedirect("userLogged.jsp");
		 
		 //logged-in page 
		 }
		 
		 else {
			 //HttpSession session = request.getSession(true);
//			 JOptionPane.showMessageDialog(null,"Login failed! Try again=)"
//					 ,"Login failure",JOptionPane.WARNING_MESSAGE);
			 
		//	 session.setAttribute("login fail",true); 
			 
			 response.sendRedirect("loginPage.jsp"); 
		 }
		 
		 //error page 
		 } 
		 
		 catch (Throwable theException) { System.out.println(theException); } } 
		 
		/*
		LoginService login = new LoginService();
						
		String result = login.authenticate 
		((String) request.getParameter("un"), (String) request.getParameter("pw"));
		response.getWriter().println(result);
		*/ 
	}

