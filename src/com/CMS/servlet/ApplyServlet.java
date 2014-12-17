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
import com.CMS.entity.Apply;

/**
 * Servlet implementation class ApplyServlet
 * 
 * @author walker cheng
 * 2014/11/28
 * send apply information of joining in club
 */
@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer requesterId=Integer.parseInt(request.getParameter("requesterId"));
		String statement=request.getParameter("statement");
		Integer clubId=Integer.parseInt(request.getParameter("clubId"));
		Timestamp applytime=DateUtils.getSysNow();
		Apply apply=new Apply();
			apply.setRequesterId(requesterId);
			apply.setClubId(clubId);
			apply.setApplyTime(applytime);
			apply.setApplyDes(statement);
			apply.setApplyStatus(0);
			ApplyService applyService=new ApplyService();
			applyService.saveApply(apply);
		PrintWriter out =response.getWriter();
		out.println("{\"status\":1,\"url\":\"init?location=chengdu\"}");
	}

}
