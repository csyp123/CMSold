package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ApplyService;
import com.CMS.dto.ApplyDto;
/**
 * 
 * @author joeyy
 *2014/12/03
 *function display checkappldeatils for checkapplydetails.jsp
 */
@WebServlet("/checkapplydetails")
public class CheckApplyDetailsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CheckApplyDetailsServlet() {
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
		Integer applyId=Integer.valueOf(request.getParameter("applyId"));
		ApplyService applyservice = new ApplyService();
		List<ApplyDto> checkapplydetails=applyservice.getApplyDetails(applyId);
		request.setAttribute("checkapplydetails", checkapplydetails);
		request.getRequestDispatcher("checkapplydetails.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
