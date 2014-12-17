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

import com.CMS.Service.ClubService;
import com.CMS.Service.UserService;
import com.CMS.dto.SearchDto;
import com.CMS.entity.Club;
import com.CMS.entity.User;

/**
 * Servlet implementation class SearchUser
 * @author walker cheng
 * 2014/12/01
 * search user 
 */
@WebServlet("/searchuser")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType=request.getParameter("userType");
		String userName=request.getParameter("userName");
		List<SearchDto> resultList=new ArrayList<SearchDto>();
		SearchDto searchDto=null;
		UserService userService=new UserService();
		HttpSession httpSession=request.getSession();
		if(userName.equals("*")){
			if(userType.equals("0")){
				resultList=userService.search("",0);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "暂时没有负责人的相关信息！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else {
					httpSession.setAttribute("resultList",resultList);
				}
			}else if(userType.equals("1")){
				resultList=userService.search("",1);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "暂时没有普通用户的相关信息！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else {
					httpSession.setAttribute("resultList",resultList);
				}
			}else if(userType.equals("10")){
				resultList=userService.search("",10);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "暂时没有系统管理员的相关信息！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else {
					httpSession.setAttribute("resultList",resultList);
				}
			}else {
				request.setAttribute("errorMsg", "请输入你想查询的用户类型！");
				request.getRequestDispatcher("search.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("searchlist.jsp").forward(request, response);
			
		}else if(userName.equals("")){
			request.setAttribute("errorMsg", "请输入用户名后在查询！");
			request.getRequestDispatcher("search.jsp").forward(request, response);
			return;
		}else {
			if(userType.equals("0")){
				resultList=userService.search(userName,-1);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "你查询的用户不存在，请核对后在输入！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else{
					searchDto=resultList.get(0);
					if(searchDto.getUserType()!=0){
						request.setAttribute("resultList", resultList);
						request.setAttribute("errorUserType", "您查询的用户并非俱乐部负责人！");
					}else{
						request.setAttribute("resultList", resultList);
					}
				}
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}else if(userType.equals("1")){
				resultList=userService.search(userName,-1);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "你查询的用户不存在，请核对后在输入！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else{
					searchDto=resultList.get(0);
					if(searchDto.getUserType()!=1){
						request.setAttribute("resultList", resultList);
						request.setAttribute("errorUserType", "您查询的用户并非普通用户！");
					}else{
						request.setAttribute("resultList", resultList);
					}
				}
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}else if(userType.equals("10")){
				resultList=userService.search(userName,-1);
				if(resultList.size()==0){
					request.setAttribute("errorMsg", "你查询的用户不存在，请核对后在输入！");
					request.getRequestDispatcher("search.jsp").forward(request, response);
					return;
				}else{
					searchDto=resultList.get(0);
					if(searchDto.getUserType()!=10){
						request.setAttribute("resultList", resultList);
						request.setAttribute("errorUserType", "您查询的用户并非系统管理员！");
					}else{
						request.setAttribute("resultList", resultList);
					}
				}
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}
		}
		
	}

}
