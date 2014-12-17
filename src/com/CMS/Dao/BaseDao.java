package com.CMS.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * @author joeyy
 * 2014/11/19
 * function get Hibernateseesion
 */

import com.CMS.Utils.HibernateUtil;
public class BaseDao {
	
	private SessionFactory sessionFactory;
	public Session getSession(){
		sessionFactory=HibernateUtil.getSessionFactory();
		Session session = null;
		try {
			//通过hibernateUtils获取session
			session = HibernateUtil.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
		
	}
	

}
