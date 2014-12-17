package com.CMS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.Service.ClubService;
import com.CMS.dto.ClubDto;

/**
 * Servlet implementation class InitDeleteClubServlet
 * @author walker cheng 
 * 2014/12/15
 * get the list of club for delete club
 */
@WebServlet("/initDeleteClub")
public class InitDeleteClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDeleteClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clubLocation=request.getParameter("clubLocation");
		List<ClubDto> clubList=new ArrayList<ClubDto>();
		ClubService clubService=new ClubService();
		if(clubLocation==null||clubLocation.equals("")){
			clubLocation="chengdu";
		}
		clubList=clubService.getClubByLocation(clubLocation);
		request.setAttribute("clubList",clubList);
		request.setAttribute("clubLocation", clubLocation);
		request.getRequestDispatcher("deleteclub.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
