package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.PromotionService;
import com.CMS.Utils.UserUtil;
import com.CMS.dto.PromotionDto;
import com.CMS.entity.User;

@WebServlet("/vote")

public class GetPromotionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public GetPromotionServlet() {
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
		User user=UserUtil.getUser(request);
		PromotionService promotionservice=new PromotionService();
		List<PromotionDto> promotionlist=promotionservice.GetOnGoingPromotionByClubId(user.getClubId());
		HttpSession session=request.getSession();
		session.setAttribute("promotionlist", promotionlist);
		request.getRequestDispatcher("promotionvote.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
