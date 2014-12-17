package com.CMS.servlet;

import java.io.IOException;
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
 *2014/12/1
 *function display applydetails
 */

@WebServlet("/applydetails")

public class ApplyDetailsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ApplyDetailsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer applyId=Integer.valueOf(request.getParameter("applyId"));
		ApplyService applyservice = new ApplyService();
		List<ApplyDto> applydetails=applyservice.getApplyDetails(applyId);
		request.setAttribute("applydetails", applydetails);
		request.getRequestDispatcher("userapplydetails.jsp").forward(request, response);
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
