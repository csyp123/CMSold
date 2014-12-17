package com.CMS.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.Dao.UserDao;
import com.CMS.Utils.HibernateUtil;
import com.CMS.dto.SearchDto;
import com.CMS.entity.User;

public class UserService {
	UserDao userdao = new UserDao();
/**
 * @author joeyy
 * @throws Exception
 */
/*
 * 2014/11/19
 * function register
 */
	public boolean save(User user) throws Exception {
		Session session=null;
		Transaction tx= null;
		session=HibernateUtil.getSession();
		tx=session.beginTransaction();
		try {
			if (userdao.checkexist(user,session)!=null) {
				return false;
			}else{
			userdao.save(user,session);
			tx.commit();
			return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx!=null) {
				tx.rollback();
			}
		}finally{
			session.close();
		}
		return false;
	}
/*
 * 2014/11/24
 * function login
 */
	public User checklogin(User user) {
		return userdao.checklogin(user);
}
/*
 * 2014/11/25
 * function change userpassword
 */
	public boolean updateUserInfo(User user) {
		return userdao.updateUserInfo(user);
	}
	/**
	 * @author walker cheng
	 * 2014/12/02
	 * get the user information by userName
	 */
	public List<SearchDto> search(String userName,Integer userType){
		return userdao.search(userName,userType);
	}
	public List<User> getUserByClubId(int clubId, Integer userId) {
		return userdao.getUserByClubId(clubId, userId);
	}
	
	/**
	 * @author walker cheng
	 * 2014/12/11
	 * get the user information by userName
	 */
	public User getUserByName(String userName){
		return userdao.getUserByName(userName);
	}
	/**
	 * @author walker cheng
	 * 2014/12/02
	 * get the user information by userId
	 */
	public List<SearchDto> searchUserById(Integer userId){
		return userdao.searchUserById(userId);
	}
}
