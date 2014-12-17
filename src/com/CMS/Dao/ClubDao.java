package com.CMS.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.dto.ClubDto;
import com.CMS.entity.Club;
/**
 * @author petep
 */
/*
 * @author petep
 * function getClubByLocation
 * 2014/11/25
 */
public class ClubDao extends BaseDao{
	public List<ClubDto> getClubByLocation(String location){
		List<ClubDto> clubList=new ArrayList<ClubDto>();
		Session session=null;
		Transaction tx=null;
		String hql="";
		try{
			
			session=getSession();			
			tx=session.beginTransaction();	
			hql="select c.clubId,c.clubName,u.userName,u.userPhone,c.clubLocation from Club c,User u "
					+ "where c.clubLocation=:location and c.managerId=u.userId";		
			Query query=session.createQuery(hql);
			query.setString("location", location);
			List result=query.list();
			for(int i=0;i<result.size();i++){
				Object[] row=(Object[])result.get(i);
				ClubDto clubDto=new ClubDto();
				clubDto.setClubId((Integer)row[0]);
				clubDto.setClubName((String)row[1]);
				clubDto.setManagerName((String)row[2]);
				clubDto.setManagerPhone((String)row[3]);
				clubDto.setClubLocation((String)row[4]);
				clubList.add(clubDto);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
		}
		finally{
			session.close();
		}
		return clubList;
	}
/*
 * @Author Walker Cheng
 * function getclub
 * 2014/11/28
 * 
 */
	public Club getClubByClubId(Integer clubId){
		Session session=null;
		Transaction tx=null;
		Club club=null;
		try {
			session=getSession();
			tx=session.beginTransaction();
			club=(Club)session.get(Club.class, clubId);
			
		} catch(Exception e){
			e.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return club;
	}
	/*
	 * @author joeyy
	 * 2014/12/12
	 * function CountClubMemberByPromotionId
	 * @return The number of the club
	 * @param promotionId 
	 */
	public Integer CountClubMemberByPromotionId(Integer promotionId,Session session) throws Exception {
		String hql="";
		Integer countmember;
		try {
			hql="select count(*) from User u,Promotion p where u.clubId=p.clubId and p.promotionId=:promotionId";
			Query query=session.createQuery(hql);
			query.setInteger("promotionId", promotionId);
			countmember=((java.lang.Long)query.uniqueResult()).intValue();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		
		return countmember;
	}
	
	/**
	 * @author walker cheng
	 * 2014/12/11
	 * function search the club by clubName and clubLocation
	 */
	public List<ClubDto> searchClubByClubNameAndClubLocation(ClubDto clubDto){
		List<ClubDto> clubList=new ArrayList<ClubDto>();
		Session session=null;
		Transaction tx=null;
		String hql="";
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="select clubId, clubName,managerId,clubDescription,clubLocation from Club where clubName=? and clubLocation=?";
			Query query=session.createQuery(hql);
			query.setString(0,clubDto.getClubName());
			query.setString(1, clubDto.getClubLocation());
			List list=query.list();
			for(int i=0;i<list.size();i++){
				Object[] objects=(Object[])list.get(i);
				ClubDto clubDto2=new ClubDto();
				clubDto2.setClubId((Integer)objects[0]);
				clubDto2.setClubName((String)objects[1]);
				clubDto2.setManagerId((Integer)objects[2]);
				clubDto2.setClubDescription((String)objects[3]);
				clubDto2.setClubLocation((String)objects[4]);
				clubList.add(clubDto2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return clubList;
	}
	
	/*
	 * @Author Walker Cheng
	 * function add new club and return clubId
	 * 2014/12/11
	 * 
	 */
		public Integer addClub(ClubDto clubDto){
			Session session=null;
			Transaction tx=null;
			Club club=new Club();
			Integer clubId=0;
			try {
				session=getSession();
				tx=session.beginTransaction();
				club.setClubName(clubDto.getClubName());
				club.setClubLocation(clubDto.getClubLocation());
				club.setClubDescription(clubDto.getClubDescription());
				club.setManagerId(clubDto.getManagerId());
				session.save(club);
				clubId= club.getClubId();
				tx.commit();
			} catch(Exception e){
				e.printStackTrace();
				if(tx != null) {
					tx.rollback();
				}
			}finally{
				if(session!=null){
					session.close();
				}
			}
			return clubId;
		}
		
		
		
		/*
		 * @Author Walker Cheng
		 * function delete the club
		 * 2014/12/12
		 * 
		 */
			public Boolean deleteClub(Club club,Session session)throws Exception{
				try {
					session.delete(club);
				}finally{
					
				}
				return true;
			}
			/*
			 * @author joeyy
			 * 2014/12/12
			 * function GetClubIdByPromotionId
			 * @return clubID
			 * @param promotionId
			 * 
			 */
		public Integer GetClubIdByPromotionId(Integer promotionId) {
				Session session=null;
				Transaction tx=null;
				String hql="";
				Integer clubId=null;
				try {
					session=getSession();
					tx=session.beginTransaction();
					hql="select c.clubId from Club c,Promotion p where p.promotionId=:promotionId and "
							+ "p.clubId = c.clubId";	
					Query query =session.createQuery(hql);
					query.setInteger("promotionId", promotionId);
					clubId=(Integer) query.uniqueResult();
					tx.commit();
				} catch(Exception e){
					e.printStackTrace();
					if(tx != null) {
						tx.rollback();
					}
				}finally{
					if(session!=null){
						session.close();
					}
				}
				return clubId;
				
			}
		/*
		 * @author joeyy
		 * 2014/12/12
		 * function UpdateClubInfo for change manager
		 * @return true if update succeed ,else return false
		 * @param Entity club
		 * 
		 */
		public boolean UpdateClubInfoChangeManager(Club club) {
			Session session=null;
			Transaction tx=null;
			try {
				session=getSession();
				tx=session.beginTransaction();
				session.update(club);
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
}
