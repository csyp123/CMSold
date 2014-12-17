package com.CMS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.UserService;
import com.CMS.entity.User;


/**
 * @author joeyy
 * 2014/11/19
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/registeruser")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userDept= request.getParameter("userDept");
		String password = request.getParameter("password");
		String userPhone= request.getParameter("userPhone");
		Integer userType=Integer.valueOf(request.getParameter("userType"));
		
		User user= new User();
		user.setUserPassword(password);
		user.setUserEmail(userEmail);
		user.setUserName(userName);
		user.setUserPart(userDept);
		user.setUserPhone(userPhone);
		user.setUserType(userType);
		UserService userservice = new UserService();
		try {
			if (userservice.save(user)) {
				request.getRequestDispatcher("login.jsp").forward(request, response);				
			}else{
				request.setAttribute("errmsg", "你注册的用户名已存在，请重设");
				request.getRequestDispatcher("user_add.jsp").forward(request, response);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
