package com.CMS.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.Dao.BaseDao;
import com.CMS.Dao.ClubDao;
import com.CMS.Dao.UserDao;
import com.CMS.dto.ClubDto;
import com.CMS.entity.Club;
/**
 * @author petep
 * 2014/11/25 
 */
/*
 * function getClubByLocation for main view 
 * 2014/11/25
 */
public class ClubService extends BaseDao{
	ClubDao clubDao=new ClubDao();
	public List<ClubDto> getClubByLocation(String location){
		return clubDao.getClubByLocation(location);
	}
/*
 * @Author Walker Cheng
 * function getClub 
 * 2014/11/28 
 */
	public Club getClubByClubId(Integer clubId){
		return clubDao.getClubByClubId(clubId);
	}
	/*
	 * @Author Walker Cheng
	 * function search club by clubName and clubLocation
	 * 2014/12/11
	 */
	public List<ClubDto> searchClubByClubNameAndClubLocation(ClubDto clubDto){
		return clubDao.searchClubByClubNameAndClubLocation(clubDto);
	}
	/*
	 * @Author Walker Cheng
	 * function add the new club and return the clubId;
	 * 2014/12/11
	 */
	public Integer addClub(ClubDto clubDto){
		return clubDao.addClub(clubDto);
	}
	/**
	 * @author walker cheng
	 * function delete the club 
	 * 2014/12/12
	 */
	public Boolean deleteClub(ClubDto clubDto){
		Session session=null;
		Transaction tx=null;
		Club club=new Club();
		UserDao userDao=new UserDao();
		Boolean result;
		Boolean result1;
		club.setClubName(clubDto.getClubName());
		club.setClubId(clubDto.getClubId());
		club.setClubLocation(clubDto.getClubLocation());
		club.setClubDescription(clubDto.getClubDescription());
		club.setManagerId(clubDto.getManagerId());
		try {
			session=getSession();
			tx=session.beginTransaction();
			result=clubDao.deleteClub(club,session);
			result1=userDao.updateUserInfoById(clubDto.getManagerId(), session);
			if(result&&result1){
				tx.commit();
			}else {
				if(tx!=null){
					tx.rollback();
				}
				return false;
			}
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
	/**
	 * @author walker cheng
	 * function query the number of member
	 * 2014/12/15
	 */
	public Integer queryMemberNumber(ClubDto clubDto){
		Session session=null;
		Transaction tx=null;
		Integer clubId=clubDto.getClubId();
		UserDao userDao=new UserDao();
		Integer memberNumber=0;
		try {
			session=getSession();
			tx=session.beginTransaction();
			memberNumber=userDao.queryMemberNumber(clubId, session);
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return 0;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return memberNumber;
	}
}
