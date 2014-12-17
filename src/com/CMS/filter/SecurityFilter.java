package com.CMS.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.CMS.Utils.UserUtil;
import com.CMS.entity.User;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {
	List<String> list=new ArrayList<String>();

	
    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		User user=UserUtil.getUser(req);
		String uri=req.getRequestURI();

		boolean pass=(user!=null||uri.endsWith(".css")||uri.endsWith(".js")||list.contains(uri));
		if(!pass){
			req.setAttribute("securitymsg", "请登录后再浏览");
			System.out.println(uri+"has been blocked by SecurityFilter");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		String contextName="CMS2";
		list.add("/"+contextName+"/InitServlet");
		list.add("/"+contextName+"/login.jsp");
		list.add("/"+contextName+"/user_add.jsp");
		list.add("/"+contextName+"/userlogin");
		list.add("/"+contextName+"/registeruser");
		list.add("/"+contextName+"/init");
		list.add("/"+contextName+"/checkpassword");
		list.add("/"+contextName+"/assets/images/furley_bg1.png");
		
	}

}
