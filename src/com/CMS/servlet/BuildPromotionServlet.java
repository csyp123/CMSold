package com.CMS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Dao.UserDao;
import com.CMS.Service.PromotionService;
import com.CMS.Utils.DateUtils;
import com.CMS.Utils.PromotionUtil;
import com.CMS.Utils.UserUtil;
import com.CMS.entity.Promotion;
import com.CMS.entity.User;

@WebServlet("/changeadmin")
public class BuildPromotionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public BuildPromotionServlet() {
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
		User admin=UserUtil.getUser(request);
		int clubId=admin.getClubId();
		int userId=admin.getUserId();
		UserDao userDao=new UserDao();
		List<User> userList=userDao.getUserByClubId(clubId,userId);
		HttpSession session=request.getSession();
		session.setAttribute("userList",userList);
		request.getRequestDispatcher("changeadmin.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		Promotion promotion = new Promotion();
		PrintWriter out=response.getWriter();
		//设置选举开始时间和过期时间
		Calendar expireTime=Calendar.getInstance();
		promotion.setStartTime(DateUtils.getSysNow());
		//获取当前时间+10天为选举过期时间
		int nowDay =expireTime.get(Calendar.DAY_OF_MONTH);
		expireTime.set(Calendar.DATE, nowDay+10);
		promotion.setExpireTime(DateUtils.SwitchSqlDate(expireTime));
		
		User admin=UserUtil.getUser(request);
		String promotionReson=request.getParameter("promotionReson");
		Integer recommenduserId=Integer.valueOf(request.getParameter("recommenduserId"));
		promotion.setClubId(admin.getClubId());
		promotion.setPromotionName(PromotionUtil.GetPromotionName(request));
		promotion.setPromotionReason(promotionReson);
		promotion.setPromotionState(0);
		promotion.setRecommenduserId(recommenduserId);
		PromotionService promotionservice = new PromotionService();
		if (promotionservice.producePromotion(promotion)) {
			out.println("{\"status\":1}");
			
		}else{
			out.println("{\"status\":0,\"msg\":\"failed to initiate\"}");
		}
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
