package com.CMS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.ClubService;
import com.CMS.Utils.UserUtil;
import com.CMS.dto.ClubDto;
import com.CMS.entity.User;

/**
 * Servlet implementation class InitServlet
 */
/**
 * 
 * @author petep&joeyy
 *2014/11/25
 */
@WebServlet("/init")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ClubDto> clubList = new ArrayList<ClubDto>();
		String location=request.getParameter("location");
		Integer flag=0;
		HttpSession session = request.getSession();
		if(location==null||"".equals(location)){
			location="chengdu";
			flag=1;	
			User user=UserUtil.getUser(request);
			Integer usertype = user.getUserType();
			session.setAttribute("usertype",usertype);
			String addCookie=request.getParameter("addCookie");
			if(addCookie!=null&&addCookie.equals("1")){
				Cookie userNameCookie=new Cookie("userName",user.getUserName());
				Cookie passwdCookie=new Cookie("password",user.getUserPassword());
				userNameCookie.setPath("/");
				passwdCookie.setPath("/");
				userNameCookie.setMaxAge(30*24*3600);
				passwdCookie.setMaxAge(30*24*3600);
				response.addCookie(userNameCookie);
				response.addCookie(passwdCookie);
			}
		}
		ClubService clubservice = new ClubService();
		clubList =clubservice.getClubByLocation(location);
		
		
		session.setAttribute("clubList", clubList);	
		
		if (flag==1) {
		request.getRequestDispatcher("index.jsp").forward(request, response);	
		}else{
		request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
