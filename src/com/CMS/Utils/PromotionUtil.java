package com.CMS.Utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.CMS.entity.User;

public class PromotionUtil {
	public static String GetPromotionName(HttpServletRequest request){
		HttpSession usersession = request.getSession();
		User user=(User)usersession.getAttribute("user");
		String userName=user.getUserName();
		return DateUtils.getNowDate()+userName;	
	}

}
