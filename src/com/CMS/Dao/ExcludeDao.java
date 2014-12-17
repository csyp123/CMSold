package com.CMS.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.entity.Exclude;

public class ExcludeDao extends BaseDao{
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public void save(Exclude exclude){
		Session session=null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(exclude);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public List<Exclude> getOnGoingExcludeByClubId(int clubId){
		Session session=null;
		Transaction tx=null;
		List<Exclude> resultList=new ArrayList<Exclude>();
		List<Exclude> resultListRaw=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId and e.excludeState=:state";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			query.setInteger("state", 1);
			resultListRaw=query.list();
			Date now=new Date();
			if(resultListRaw!=null){
				for(Exclude exclude : resultListRaw){	
					if(exclude.getExpireTime().getTime()>now.getTime()){
						resultList.add(exclude);
						continue;
					}
					exclude.setExcludeState(4);
					save(exclude);
				}
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public List<Exclude> getExcludeByStartTimeAndState(int clubId,Timestamp startTimestamp,int state){
		Session session=null;
		Transaction tx=null;
		List<Exclude> resultList=new ArrayList<Exclude>();
		List<Exclude> resultListRaw=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId and e.excludeState=:state order by e.startTime desc";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			query.setInteger("state", state);
			resultListRaw=query.list();
			if(resultListRaw!=null){
				for(Exclude exclude : resultListRaw){	
					if(exclude.getStartTime().getTime()>=startTimestamp.getTime()){
						resultList.add(exclude);
					}
				}
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public List<Exclude> getExcludeByState(int clubId,int state){
		Session session=null;
		Transaction tx=null;
		List<Exclude> resultList=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId and e.excludeState=:state order by e.startTime desc";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			query.setInteger("state",state);
			resultList=query.list();
			
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public List<Exclude> getExcludeByStartTime(int clubId,Timestamp startTimestamp){
		Session session=null;
		Transaction tx=null;
		List<Exclude> resultList=new ArrayList<Exclude>();
		List<Exclude> resultListRaw=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId order by e.startTime desc";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			resultListRaw=query.list();
			if(resultListRaw!=null){
				for(Exclude exclude : resultListRaw){	
					if(exclude.getStartTime().getTime()>=startTimestamp.getTime()){
						resultList.add(exclude);
					}
				}
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public List<Exclude> getAllExclude(int clubId){
		Session session=null;
		Transaction tx=null;
		List<Exclude> resultList=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId order by e.startTime desc";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			resultList=query.list();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 */
	public Exclude getExcludeById(int excludeId){
		Session session=null;
		Transaction tx=null;
		Exclude exclude=null;
		String hql="";
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.excludeId=:excludeId";
			Query query=session.createQuery(hql);
			query.setInteger("excludeId", excludeId);
			exclude=(Exclude)query.uniqueResult();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return exclude;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function to avoid two or more exclude for the same person exist on the same time;
	 */
	public int getDupExclude(int clubId, int userId) {
		Session session=null;
		Transaction tx=null;
		Exclude exclude=null;
		String hql="";
		int result=0;
		try{
			session=getSession();
			tx=session.beginTransaction();
			hql="from Exclude e where e.clubId=:clubId and e.userId=:userId and e.excludeState=:state";
			Query query=session.createQuery(hql);
			query.setInteger("clubId", clubId);
			query.setInteger("userId", userId);
			query.setInteger("state",1);
			result=query.list().size();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}
}
