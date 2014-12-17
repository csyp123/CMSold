package com.CMS.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/**
 * 
 * @author joeyy
 *2014/11/17
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	private static Configuration cfg = null;
	private static ServiceRegistry serviceRegistry = null;

	static {
		try {
			  cfg = new Configuration().configure();
			  serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			  sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	/**
	 * 获得当前应用SessionFactory实例
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * 获得一个新的Session实例
	 */
	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
}
