package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ApplyService;
import com.CMS.Utils.DateUtils;

/**
 * 
 * @author joeyy
 *2014/12/03
 *function rejectapply 
 */
@WebServlet("/rejectapply")
public class RejectApplyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RejectApplyServlet() {
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
		String checkRes=request.getParameter("checkRes");
		Integer applyId=Integer.valueOf(request.getParameter("applyId"));
		PrintWriter out = response.getWriter();
		ApplyService applyservice=new  ApplyService();
		if (applyservice.RejectApply(applyId,checkRes,DateUtils.getSysNow())) {
			out.println("{\"status\":1,\"msg\":\"succeed to reject\"}");
		}else{
			out.println("{\"status\":0,\"msg\":\"failed to reject\"}");
		}
		
		
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
