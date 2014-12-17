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
 * 
 * @author joeyy
 *2014/11/25
 */
/*
 * function change userpassword
 * 2014/11/25
 */
@WebServlet("/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdatePasswordServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password1=request.getParameter("password1");
		String oldpassword=request.getParameter("password");
		PrintWriter out = response.getWriter();
		HttpSession usersession=request.getSession();
		User user=(User)usersession.getAttribute("user");
		UserService userservice = new UserService();
		user.setUserPassword(oldpassword);
		if (userservice.checklogin(user)!=null) {	
		user.setUserPassword(password1);
			if (userservice.updateUserInfo(user)) {
				out.println("{\"status\":1}");
			}else{
				out.println("{\"status\":0,\"msg\":\"faild to change password\"}");
			}
		}else{
			out.println("{\"status\":0,\"msg\":\"the old password is wrong\"}");
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
