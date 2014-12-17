package com.CMS.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.dto.SearchDto;
import com.CMS.entity.User;


/**
 * @author joeyy
 *
 */
/*
 * function register
 * 2014/11/19
 */
public class UserDao extends BaseDao{
	public void save(User user,Session session) throws Exception{
		try {
			session.save(user);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	/*
	 *@author Pete
	 * function for exclude
	 * 2014/12/1
	 */
		public List<User> getUserByClubId(int clubId,int userId){
			Session session=null;
			Transaction tx=null;
			List<User> resultList=null;
			String hql="";
			try{
				session=getSession();
				tx=session.beginTransaction();
				hql="from User u where u.clubId = :clubId and u.userId <> :userId";
				Query query=session.createQuery(hql);
				query.setInteger("clubId", clubId);
				query.setInteger("userId", userId);
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
		 * @author Pete
		 * function for exclude
		 * 2014/12/2
		 */
		public User getUserByName(String userName){
			Session session=null;
			Transaction tx=null;
			String hql="";
			User user=null;
			try{
				session=getSession();
				tx=session.beginTransaction();
				hql="from User u where u.userName=:userName";
				Query query=session.createQuery(hql);
				query.setString("userName", userName);
				user= (User)query.uniqueResult();
			}catch(Exception e){
				if(tx!=null){
					tx.rollback();
				}
			}finally{
				if(session!=null){
					session.close();
				}
			}
			return user;
		}
		/*
		 * @author Pete
		 * function for exclude
		 * 2014/12/2
		 */
		public User getUserByUserId(int userId){
			Session session=null;
			Transaction tx=null;
			String hql="";
			User user=null;
			try{
				session=getSession();
				tx=session.beginTransaction();
				hql="from User u where u.userId=:userId";
				Query query=session.createQuery(hql);
				query.setInteger("userId", userId);
				user= (User)query.uniqueResult();
			}catch(Exception e){
				if(tx!=null){
					tx.rollback();
				}
			}finally{
				if(session!=null){
					session.close();
				}
			}
			return user;
		}
/*
 * function login
 * 2014/11/24
 */
	public User checklogin(User user) {
		Session session=null;
		Transaction tx = null;
		List<User> resultlist=null;
		String hql="";
		String userName = user.getUserName();
		String userPassword = user.getUserPassword();
		try {
			session= getSession();
			tx=session.beginTransaction();
			hql="from User u where u.userName =? and u.userPassword =?";
			Query query = session.createQuery(hql);
			query.setString(0, userName);
			query.setString(1, userPassword);
			resultlist=query.list();
			return (User)resultlist.get(0);

		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	/*
	 * @author Pete
	 * function for exclude
	 * 2014/12/2
	 */
	public int countClubMember(int clubId){
		int count=-1;
		Session session=null;
		Transaction tx = null;
		String hql="";
		try {
			session= getSession();
			tx=session.beginTransaction();
			hql="from User u where u.clubId =:clubId";
			Query query = session.createQuery(hql);
			query.setInteger("clubId", clubId);
			count=query.list().size();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return count;
	}
/*
 * function change userpassword
 * 2014/11/25	
 */
	public boolean updateUserInfo(User user) {
		Session session=null;
		Transaction tx = null;
		try {
			session=getSession();
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	public boolean downUserType(Integer userId) {
		Session session=null;
		Transaction tx = null;
		String hql="";
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="update User u set u.userType=1 where u.userId=:userId";
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return true;
	}		
	public Integer GetManagerIdByPromotionId(Integer promotionId) {
		
		Session session=null;
		Transaction tx=null;
		String hql="";
		Integer userId=null;
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="select u.userId from User u,Club c,Promotion p where p.clubId=c.clubId and c.managerId=u.userId and p.promotionId=:promotionId";
			Query query = session.createQuery(hql);
			query.setInteger("promotionId", promotionId);
			userId=(Integer) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return userId;
	}
	public boolean UpUserType(Integer userId) {
		Session session=null;
		Transaction tx=null;
		String hql="";
		User user =new User();
		
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="update User u set u.userType=0 where u.userId=:userId";
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/**
	 * @author walker cheng
	 * 2014/12/01
	 * function search uesr by userName and userType
	 */
	public List<SearchDto> search(String userName,Integer userType){
		Session session=null;
		Transaction tx=null;
		List<SearchDto> resultList=new ArrayList<SearchDto>();
		String hql=null;
		Query query;
		try {
			session=getSession();
			tx=session.beginTransaction();
			if(userName==""){
				hql="Select u.userEmail,u.userPhone,u.userPart,u.userType,c.clubId,c.clubName,c.clubLocation,u.userName,u.userId from User u ,Club c where u.userType=?  and u.clubId=c.clubId";
				query=session.createQuery(hql);
				query.setInteger(0, userType);
			}else{	
				hql="Select u.userEmail,u.userPhone,u.userPart,u.userType,c.clubId,c.clubName,c.clubLocation,u.userName,u.userId from User u ,Club c where u.userName=?  and u.clubId=c.clubId";
				query=session.createQuery(hql);
				query.setString(0, userName);	
			}
			 List list=query.list();
				for(int i=0;i<list.size();i++){
					Object [] listObjects=(Object [])list.get(i);
					SearchDto searchDto=new SearchDto();
					searchDto.setUserEmail((String)listObjects[0]);
					searchDto.setUserPhone((String)listObjects[1]);
					searchDto.setUserPart((String)listObjects[2]);
					searchDto.setUserType((Integer)listObjects[3]);
					searchDto.setClubId((Integer)listObjects[4]);
					searchDto.setClubName((String)listObjects[5]);
					searchDto.setClubLocation((String)listObjects[6]);
					searchDto.setUserName((String)listObjects[7]);
					searchDto.setUserId((Integer)listObjects[8]);
					resultList.add(searchDto);
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
		
		return resultList;
	}
	/**
	 * @author walker cheng
	 * 2014/12/10
	 * function search uesr by userId
	 */
	public List<SearchDto> searchUserById(Integer userId){
		Session session=null;
		Transaction tx=null;
		List<SearchDto> resultList=new ArrayList<SearchDto>();
		String hql=null;
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="Select u.userEmail,u.userPhone,u.userPart,u.userType,c.clubId,c.clubName,c.clubLocation,u.userName,u.userId from User u ,Club c where u.userId=?  and u.clubId=c.clubId";
			Query query=session.createQuery(hql);
			query.setInteger(0, userId);
			 List list=query.list();
				for(int i=0;i<list.size();i++){
					Object [] listObjects=(Object [])list.get(i);
					SearchDto searchDto=new SearchDto();
					searchDto.setUserEmail((String)listObjects[0]);
					searchDto.setUserPhone((String)listObjects[1]);
					searchDto.setUserPart((String)listObjects[2]);
					searchDto.setUserType((Integer)listObjects[3]);
					searchDto.setClubId((Integer)listObjects[4]);
					searchDto.setClubName((String)listObjects[5]);
					searchDto.setClubLocation((String)listObjects[6]);
					searchDto.setUserName((String)listObjects[7]);
					searchDto.setUserId((Integer)listObjects[8]);
					resultList.add(searchDto);
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
		
		return resultList;
	}
	
	
	/*
	 * @Author Walker Cheng
	 * function update the user information by userId bucause of delete club
	 * 2014/12/12
	 * 
	 */
		public Boolean updateUserInfoById(Integer userId,Session session)throws Exception{
			String hql="update User set userType=?,clubId=? where userId=?";
			try {
				Query query=session.createQuery(hql);
				query.setInteger(0,1 );
				query.setInteger(1, 0);
				query.setInteger(2, userId);
				int count=query.executeUpdate();
				if(count==0){
					return false;
				}
				
			}finally{
				
			}
			return true;
		}
		/*
		 * @author joeyy
		 * 2014/12/15
		 * function check if user is exist
		 * @return true if user is exist ,else return false 
		 * @param Entity User
		 */
		public User checkexist(User user,Session session) throws Exception {
			String hql="";
			try {
				hql="from User u where u.userName=:userName";
				Query query=session.createQuery(hql);
				query.setString("userName", user.getUserName());
				return (User) query.uniqueResult();
				
			} catch (Exception e) {
				throw new Exception(e);
			}
			
			
		}
		/*
		 * @Author Walker Cheng
		 * function query the number of member
		 * 2014/12/15
		 * 
		 */
			public Integer queryMemberNumber(Integer clubId,Session session)throws Exception{
				List resultList=new ArrayList();
				String hql="select userId from User where clubId=?";
				Integer number=0;
				try {
					Query query=session.createQuery(hql);
					query.setInteger(0,clubId);
					resultList=query.list();
					number=resultList.size();
				}finally{
					
				}
				return number;
			}
	
}
		
	
