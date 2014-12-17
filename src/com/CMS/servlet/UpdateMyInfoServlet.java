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

import com.CMS.Service.UserService;
import com.CMS.Utils.UserUtil;
import com.CMS.dto.SearchDto;
import com.CMS.entity.User;

/**
 * Servlet implementation class EditMyInfoServlet
 * @author walker cheng
 * 2014/12/08
 * edit your information
 */
@WebServlet("/updateMyInfo")
public class UpdateMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		UserService userService=new UserService();
		List reslut=new ArrayList<SearchDto>();
		reslut=userService.searchUserById(userId);
		SearchDto userDtoInfo=new SearchDto();
		userDtoInfo=(SearchDto)reslut.get(0);
		request.setAttribute("userDtoInfo",userDtoInfo);
		request.getRequestDispatcher("updatemyinfo.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userPhone=request.getParameter("userPhone");
		String userEmail=request.getParameter("userEmail");
		String userPart=request.getParameter("userPart");
		PrintWriter out=response.getWriter();
		User user=UserUtil.getUser(request);
		user.setUserName(userName);
		user.setUserPhone(userPhone);
		user.setUserEmail(userEmail);
		user.setUserPart(userPart);
		UserService userService=new UserService();
		Boolean result=userService.updateUserInfo(user);
		if(result){
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			out.println("{\"status\":1,\"url\":\"init?location=chengdu\"}");
			
		}else{
			out.println("{\"status\":0,\"msg\":\"信息更改失败！\"}");
		}
	}
}
