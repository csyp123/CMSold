package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ExcludeService;
import com.CMS.Utils.UserUtil;
import com.CMS.entity.User;

@WebServlet("/ExcludeVoteServlet")
public class ExcludeVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ExcludeVoteServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function: for club member to vote a exclude.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=UserUtil.getUser(request);
		String a=request.getParameter("excludeId");
		String b=request.getParameter("choice");
		int excludeId=Integer.parseInt(request.getParameter("excludeId"));
		int choice=Integer.parseInt(request.getParameter("choice"));
		ExcludeService excludeService=new ExcludeService();
		int result=excludeService.addVote(user.getUserId(),excludeId,choice);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		if(result==1){
			out.println("{\"msg\":\"已投票\"}");
		}
		else {
			out.println("{\"msg\":\"已过期\"}");
		}
	}

}
