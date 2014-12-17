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

import com.CMS.Dao.UserDao;
import com.CMS.Service.ExcludeService;
import com.CMS.Service.UserService;
import com.CMS.Utils.UserUtil;
import com.CMS.entity.User;

@WebServlet("/BuildExcludeServlet")
public class BuildExcludeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BuildExcludeServlet() {
        super();
    }

    /*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function get club member list for club manager to choose to be excluded;
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User admin=UserUtil.getUser(request);
		int clubId=admin.getClubId();
		UserService userService=new UserService();
		User user=UserUtil.getUser(request);
		List<User> userList=userService.getUserByClubId(clubId,user.getUserId());
		HttpSession session=request.getSession();
		session.setAttribute("userList",userList);
		request.getRequestDispatcher("exclude.jsp").forward(request,response);
	}

	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function for club manager to build a exclude
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User admin=UserUtil.getUser(request);
		int clubId=admin.getClubId();
		response.setCharacterEncoding("UTF-8");
		System.out.println("In excludeServlet");
		List<String> excludeList=new ArrayList();
		String excludeUserName=request.getParameter("excludeUser");
		System.out.println(excludeUserName);
		String reason=request.getParameter("reason");
		ExcludeService excludeService=new ExcludeService();
		PrintWriter out=response.getWriter();	
		if(excludeService.produceExclude(clubId,excludeUserName,reason)==1){
			out.println("{\"msg\":\"发布投票成功 \"}");	
		}
		else{
			out.println("{\"msg\":\"对"+excludeUserName+"的投票正在进行\"}");	
		}
	}

}
