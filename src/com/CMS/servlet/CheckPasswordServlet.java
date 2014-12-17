package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckPasswordServlet
 */
/**
 * @author joeyy
 *2014/11/17
 *checkpasswordajasxservlet 
 */
@WebServlet("/checkpassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String password1=request.getParameter("password1");
		String password2=request.getParameter("password2");
		if (!password1.equals(password2)) {
			out.println("{\"status\":0,\"msg\":\"两次输入密码不一致\"}");
		}else if("".equals(password2)||password2==null){
			out.println("{\"status\":0,\"msg\":\"不能为空\"}");
		}else{
			out.println("{\"status\":1}");
			
			
		}
		
	}

}
