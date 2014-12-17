package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.UserService;
import com.CMS.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
/**
 * 
 * 
 * @author joeyy
 *2014/11/24
 */
@WebServlet("/userlogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		HttpSession usersession=request.getSession();
		PrintWriter out = response.getWriter();
		User user= new User();
		user.setUserName(userName);
		user.setUserPassword(password);
		UserService userservice = new UserService();
		//judge if user login success or failed
		if (userservice.checklogin(user)!=null) {
			user=userservice.checklogin(user);
			usersession.setAttribute("user", user);
			out.println("{\"status\":1,\"msg\":\"login success\"}");
		}else{
			out.println("{\"status\":0,\"msg\":\"bad credit\"}");
		}
		
	}

}
