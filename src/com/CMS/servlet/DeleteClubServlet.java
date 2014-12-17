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

import org.hibernate.mapping.Array;

import com.CMS.Service.ClubService;
import com.CMS.dto.ClubDto;

/**
 * Servlet implementation class DeleteClubServlet
 */
@WebServlet("/deleteClub")
public class DeleteClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClubServlet() {
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
		String clubLocation=request.getParameter("clubLocation");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		PrintWriter out =response.getWriter();
		Boolean result;
		ClubDto clubDto=new ClubDto();
		ClubDto clubDto1=new ClubDto();
		ClubService clubService=new ClubService();
		List<ClubDto> resultList=new ArrayList<ClubDto>();
		clubDto.setClubName(clubName);
		clubDto.setClubLocation(clubLocation);
		resultList=clubService.searchClubByClubNameAndClubLocation(clubDto);
		clubDto1=resultList.get(0);
		Integer memberNumber=clubService.queryMemberNumber(clubDto1);
		if(memberNumber==1){
			result=clubService.deleteClub(clubDto1);
			if(result){
				out.println("{\"status\":1,\"url\":\"init\"}");
			}else{
				out.println("{\"status\":0,\"msg\":\"俱乐部删除失败！\"}");
			}
		}else {
			out.println("{\"status\":0,\"msg\":\"你选择的俱乐部不满足删除条件！\"}");
		}
	
	}
}
