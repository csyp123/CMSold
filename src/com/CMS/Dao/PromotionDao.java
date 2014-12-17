package com.CMS.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.dto.PromotionDto;
import com.CMS.entity.Promotion;
import com.CMS.entity.User;
/**
 * 
 * @author joeyy
 *
 */

public class PromotionDao extends BaseDao{
	/*
	 * 2014/12/11
	 * function SavePromotion
	 * @return true if save succeed,else return false
	 * @param Entity Promotion
	 * 
	 */

	public boolean savepromotion(Promotion promotion) {
		Session session=null;
		Transaction tx=null;
		try {
			session=getSession();
			tx=session.beginTransaction();
			session.save(promotion);
			tx.commit();
		} catch(Exception e){
			e.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return true;

}
	/*
	 * function GetOnGoingPromotionByClubId
	 * 2014/12/12
	 * @return list of onGoingPromotion 
	 * @param clubId of CurrentUser
	 * 
	 */
	public List<PromotionDto> GetOnGoingPromotionByClubId(Integer clubId) {
		Session session=null;
		Transaction tx=null;
		String hql="";
		List<PromotionDto> resultlist=new ArrayList<PromotionDto>();
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="select c.clubName,u.userName,p.promotionReason,p.startTime,p.expireTime,p.promotionId,p.endTime,p.promotionName "
					+ "from User u,Club c,Promotion p "
					+ "where p.clubId=? "
					+ "and p.clubId=c.clubId and p.recommenduserId=u.userId "
					+ "order by p.startTime desc";
			Query query=session.createQuery(hql);
			query.setInteger(0, clubId);
			List list=query.list();
			if(list!=null){
						for(int i=0;i<list.size();i++){
							Object[] row=(Object[])list.get(i);
							PromotionDto promotiondto=new PromotionDto();
								promotiondto.setClubName((String)row[0]);
								promotiondto.setRecommenduserName((String)row[1]);
								promotiondto.setPromotionReason((String)row[2]);
								promotiondto.setExpireTime((Timestamp)row[4]);
								promotiondto.setStartTime((Timestamp)row[3]);
								promotiondto.setPromotionId((Integer)row[5]);
								promotiondto.setEndTime((Timestamp)row[6]);
								promotiondto.setPromotionName((String)row[7]);
								resultlist.add(promotiondto);
						}
					}else{
						return null;
					}
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally{
			
			if(session!=null){
				session.close();
			}
		}
		
		return resultlist;
	}
	/*
	 * function GetOnGoingPromotionByClubId
	 * 2014/12/12
	 * @return list of User except ClubManager
	 * @param promotionId
	 * 
	 */
	public List<User> GetPromotionUserByPromotionId(Integer promotionId,Session session) throws Exception {
		List<User> userlist=new ArrayList<User>();
		String hql="";
		Integer managerId=null;
		Integer clubId=null;
		try{			
			hql="select u.userId from User u,Club c,Promotion p where p.promotionId=? and p.clubId=c.clubId and c.managerId=u.userId";		
			Query query=session.createQuery(hql);
			query.setInteger(0, promotionId);
			managerId=(Integer)query.uniqueResult();
			hql="select c.clubId from Club c,Promotion p where c.clubId =p.clubId and p.promotionId=:promotionId";
			Query query3 = session.createQuery(hql);
			query3.setInteger("promotionId", promotionId);
			clubId=(Integer) query3.uniqueResult();
			hql="from User u where u.userId <> ? and u.userType <> 0 and u.clubId=:clubId";
			Query query2=session.createQuery(hql);
			query2.setInteger(0, managerId);
			query2.setInteger("clubId", clubId);
			userlist=query2.list();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception(e);
			

		}
		return userlist;
		
	}
	/*
	 * function EndPromotion update PromotionInfoByromotionId
	 * 2014/12/12
	 * @return true if update succeed ,else return false
	 * @param promotionId,endtime
	 * 
	 */
	public boolean EndPromotion(Integer promotionId,Timestamp endtime) {
		Session session=null;
		Transaction tx=null;
		String hql="";
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="update Promotion p set p.endTime=:endtime,p.promotionState=2 where p.promotionId=:promotionId";
			Query query = session.createQuery(hql);
			query.setTimestamp("endtime", endtime);
			query.setInteger("promotionId", promotionId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return true;
	}
/*
 * function update PromotionInfo if promotion is end or failed
 * 2014/12/12
 */
	public void UpdatePromotion(Integer promotionId,Integer promotionState, Session session) throws Exception {
		String hql="";
		try {
			hql="update Promotion p set p.promotionState=:promotionState where p.promotionId=:promotionId";
			Query query = session.createQuery(hql);
			query.setInteger("promotionState", promotionState);
			query.setInteger("promotionId", promotionId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	/*
	 * function Check one club only can have one promotion
	 * 2014/12/15
	 */
	public Promotion CheckExist(Promotion promotion,Session session) {
		String hql="";
		Promotion p= new Promotion();
		try {
			hql="from Promotion p where p.clubId=:clubId";
			Query query = session.createQuery(hql);
			query.setInteger("clubId", promotion.getClubId());
			p=(Promotion) query.uniqueResult();
		} catch (Exception e) {
			
		}
		return p;
	}
}
