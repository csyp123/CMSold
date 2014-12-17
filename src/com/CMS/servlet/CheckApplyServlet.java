package com.CMS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.ApplyService;
import com.CMS.dto.ApplyDto;
import com.CMS.entity.User;


/**
 * @author joeyy
 *2014/12/3
 */


@WebServlet("/checkapply")
public class CheckApplyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CheckApplyServlet() {
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
		Integer pageIndex;
		Integer applyStatus;
		if ("0".equals(request.getParameter("pageIndex"))||"".equals(request.getParameter("pageIndex"))) {
			pageIndex=0;
		}else{
		pageIndex=(Integer.valueOf(request.getParameter("pageIndex"))-1)*10;
		}
		if ("".equals(request.getParameter("applyStatus"))||request.getParameter("applyStatus")==null) {
			applyStatus=-1;
		}else{
			applyStatus = Integer.valueOf(request.getParameter("applyStatus"));
		}
		List<ApplyDto> checkapplylist = new ArrayList<ApplyDto>();
		ApplyService applyservice = new ApplyService();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		Integer managerId=user.getUserId();
		checkapplylist = applyservice.getApplyByManagerId(managerId, pageIndex,applyStatus);
		Integer checkapplysize = checkapplylist.size();
		request.setAttribute("checkapplysize", checkapplysize);
		session.setAttribute("checkapplylist", checkapplylist);
		request.setAttribute("pageIndex", pageIndex);
		request.getRequestDispatcher("checkapply.jsp").forward(request, response);	


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
