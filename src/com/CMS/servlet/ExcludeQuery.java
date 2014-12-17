package com.CMS.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CMS.Service.ExcludeService;
import com.CMS.Utils.UserUtil;
import com.CMS.dto.ExcludeDto;
import com.CMS.entity.User;

/**
 * Servlet implementation class ExcludeQuery
 */
@WebServlet("/ExcludeQuery")
public class ExcludeQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcludeQuery() {
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
		String excludeResult=request.getParameter("excludeResult");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String day=request.getParameter("day");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp startTimestamp=null;
		ExcludeService excludeService=new ExcludeService();
		List<ExcludeDto> excludeQueryList;
		try{
			User user=UserUtil.getUser(request);
			if(!year.equals("-1")){
					if(month.length()==1)month="0"+month;
					if(day.length()==1)day="0"+day;
					StringBuilder startTime=new StringBuilder("");
					startTime.append(year);
					startTime.append("-");
					startTime.append(month);
					startTime.append("-");
					startTime.append(day);
					startTime.append(" 00:00:00");
					startTimestamp=new Timestamp(format.parse(startTime.toString()).getTime());
			}
			if(excludeResult.equals("-1")){
				if(startTimestamp==null){
					excludeQueryList=excludeService.getAllExclude(user);
				}
				else {
					excludeQueryList=excludeService.getExcludeByStartTime(user,startTimestamp);
				}
				
			}
			else {
				int state=Integer.parseInt(excludeResult);
				if(startTimestamp==null){
					excludeQueryList=excludeService.getExcludeByState(user,state);
				}
				else {
					excludeQueryList=excludeService.getExcludeByStartTimeAndState(user,startTimestamp,state);
				}
			}
			HttpSession session=request.getSession();
			session.setAttribute("excludeQueryList", excludeQueryList);
			request.getRequestDispatcher("excludequery.jsp").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
