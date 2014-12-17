package com.CMS.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.CMS.entity.User;

/**
 * 
 * 
 * @author joeyy
 *
 */

public class UserUtil {
	/*
	 * function getUser
	 * 2014/11/24
	 */
	public static User getUser(HttpServletRequest request){
		HttpSession usersession = request.getSession();
		User user=(User)usersession.getAttribute("user");
		return user;	
	}
}