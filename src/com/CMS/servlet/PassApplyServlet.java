package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ApplyService;
import com.CMS.Utils.DateUtils;
@WebServlet("/passapply")
/**
 * 
 * 
 * @author joeyy
 *2014/12/03
 *function PassapplyByapplyId
 */
public class PassApplyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public PassApplyServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer applyId=Integer.valueOf(request.getParameter("applyId")); 
		Integer userId=Integer.valueOf(request.getParameter("userId")); 
		Integer clubId=Integer.valueOf(request.getParameter("userId")); 
		PrintWriter out = response.getWriter();
		ApplyService applyservice = new ApplyService();
		if (applyservice.PassApply(applyId,DateUtils.getSysNow(),userId,clubId)) {
			out.println("{\"status\":1,\"msg\":\"succeed to pass\"}");
		}else{
			out.println("{\"status\":0,\"msg\":\"failed to pass\"}");	
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
