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

import com.CMS.Service.ClubService;
import com.CMS.Service.UserService;
import com.CMS.dto.ClubDto;
import com.CMS.dto.SearchDto;
import com.CMS.entity.User;

/**
 * Servlet implementation class AddClubServlet
 * @author walker cheng
 * 2014/12/10
 * add the new club 
 */
@WebServlet("/addClub")
public class AddClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClubServlet() {
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
		String clubName=request.getParameter("clubName");
		String managerName=request.getParameter("managerName");
		String clubLocation=request.getParameter("clubLocation");
		String clubDes=request.getParameter("clubDes");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		PrintWriter out=response.getWriter();
		User user=new User();
		List<ClubDto> clubList=new ArrayList<ClubDto>();
		UserService userService=new UserService();
		ClubDto clubDto=new ClubDto();
		ClubService clubService=new ClubService();
		user=userService.getUserByName(managerName);
		if(user==null){
			out.println("{\"status\":0,\"msg\":\"你输入的负责人不存在\"}");
		}else{
			if(user.getUserType()==0){
				out.println("{\"status\":0,\"msg\":\"你输入的负责人已是其他俱乐部的负责人！请重新选择\"}");
			}else{
				clubDto.setManagerId(user.getUserId());
				clubDto.setClubName(clubName);
				clubDto.setClubLocation(clubLocation);
				clubDto.setClubDescription(clubDes);
				clubDto.setManagerName(managerName);
				clubList=clubService.searchClubByClubNameAndClubLocation(clubDto);
				if(clubList.size()!=0){
					out.println("{\"status\":0,\"msg\":\"该俱乐部已经存在！\"}");
				}else {
					Integer clubId=clubService.addClub(clubDto);
					user.setClubId(clubId);
					user.setUserType(0);
					Boolean resultBoolean=userService.updateUserInfo(user);
					if(resultBoolean){
						out.println("{\"status\":1,\"url\":\"init\"}");
					}else{
						out.println("{\"status\":0,\"msg\":\"负责人信息更改失败！\"}");
					}
				}
			}
		}
		
	}

}
