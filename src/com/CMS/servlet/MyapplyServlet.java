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
 * Servlet implementation class MyapplyServlet
 */
/**
 * 
 * 
 * @author joeyy
 *2014/11/26
 */
@WebServlet("/myapply")
public class MyapplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyapplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		List<ApplyDto> applylist = new ArrayList<ApplyDto>();
		ApplyService applyservice = new ApplyService();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		Integer userId=user.getUserId();
		applylist = applyservice.getApplyByUserId(userId,pageIndex,applyStatus);
		Integer applysize = applylist.size();
		request.setAttribute("applysize", applysize);
		session.setAttribute("applylist", applylist);
		request.setAttribute("pageIndex", pageIndex);
		request.getRequestDispatcher("myapply.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
