package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.PromotionService;
import com.CMS.Utils.UserUtil;
import com.CMS.entity.PromotionVoteRecord;
import com.CMS.entity.User;
/**
 * 
 * 
 * @author joeyy
 *2014/12/12
 */
@WebServlet("/onpromotion")
public class OnPromotionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public OnPromotionServlet() {
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
		List<User> promotionuserlist=new ArrayList<User>();
		Integer promotionId=Integer.valueOf(request.getParameter("promotionId"));
		String clubName=request.getParameter("clubName");
		clubName = new String(clubName.getBytes("iso8859-1"),"UTF-8");
		PromotionService promotionservice= new PromotionService();
		promotionuserlist = promotionservice.GetPromotionUserByPromotionId(promotionId);
		HttpSession session=request.getSession();
		session.setAttribute("promotionuserlist",promotionuserlist);
		request.setAttribute("clubName", clubName);
		request.setAttribute("promotionId", promotionId);
		request.getRequestDispatcher("promotion.jsp").forward(request,response);	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Integer voteduser=Integer.valueOf(request.getParameter("promotionuser"));
		Integer voteuser=UserUtil.getUser(request).getUserId();
		Integer promotionId=Integer.valueOf(request.getParameter("promotionId"));
		PromotionService promotionservice = new PromotionService();
		PromotionVoteRecord pvr = new PromotionVoteRecord();
		pvr.setPromotionId(promotionId);
		pvr.setVoteduserId(voteduser);
		pvr.setVoteuserId(voteuser);
		try {
			if (promotionservice.isExist(pvr)) {
				out.println("{\"msg\":\"failed to vote,you already have voted\"}");	
				return;
			}else{
				if (promotionservice.SavePromotionRecord(pvr)) {
					promotionservice.JudgePromotion(pvr);
					out.println("{\"msg\":\"succeed to vote\"}");		
				}else{
					out.println("{\"msg\":\"failed to vote\"}");					
				}				
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		
	}

}
