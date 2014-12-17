package com.CMS.servlet;

import java.io.IOException;
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

@WebServlet("/GetExcludeServlet")
public class GetExcludeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetExcludeServlet() {
        super();
    }
    /*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function get exclude that the current user has not voted
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=UserUtil.getUser(request);
		ExcludeService excludeService=new ExcludeService();
		List<ExcludeDto> excludeList=excludeService.getExclude(user);
		HttpSession session=request.getSession();
		session.setAttribute("excludeList", excludeList);
		request.getRequestDispatcher("excludevote.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
