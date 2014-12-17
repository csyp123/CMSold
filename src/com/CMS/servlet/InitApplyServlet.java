package com.CMS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ApplyService;
import com.CMS.Service.ClubService;
import com.CMS.Utils.UserUtil;
import com.CMS.entity.Club;
import com.CMS.entity.User;

/**
 * Servlet implementation class InitApplyServlet
 * 
 * @author walker cheng
 * 2014/11/26
 * initialize the uesr information for apply
 */
@WebServlet("/initApply")
public class InitApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=UserUtil.getUser(request);
		Boolean resultOfSearch;
		if(!(user.getClubId()==0)){
			request.setAttribute("errorMsg", "你已经加入了一个俱乐部，若要再次申请，请先退出先前俱乐部！");
			request.getRequestDispatcher("applydetails.jsp").forward(request, response);
			return;
		}else {
			ApplyService applyService=new ApplyService();
			resultOfSearch=applyService.searchWhetherApply(user.getUserId(), 0);
			if(resultOfSearch){
				request.setAttribute("errorMsg", "你已经发出了一个关于俱乐部的申请，若要再次申请，请先撤销先前的申请！");
				request.getRequestDispatcher("applydetails.jsp").forward(request, response);
				return;
			}else{
				Integer clubId=(Integer.parseInt(request.getParameter("clubId")));
				ClubService clubservice=new ClubService();
				Club club=new Club();
				club=clubservice.getClubByClubId(clubId);
				request.setAttribute("club", club);
			}
			
		}
		
		request.getRequestDispatcher("applydetails.jsp").forward(request, response);
		
	}

}
