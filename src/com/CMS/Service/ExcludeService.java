package com.CMS.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.Dao.ClubDao;
import com.CMS.Dao.ExcludeDao;
import com.CMS.Dao.ExcludeVoteRecordDao;
import com.CMS.Dao.UserDao;
import com.CMS.Utils.HibernateUtil;
import com.CMS.dto.ExcludeDto;
import com.CMS.entity.Club;
import com.CMS.entity.Exclude;
import com.CMS.entity.ExcludeVoteRecord;
import com.CMS.entity.User;


public class ExcludeService {
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function check weather there is already an exclude for the user;
	 * 
	 */
	private boolean checkDupExclude(int clubId,User user){
		boolean result=false;
		ExcludeDao excludeDao=new ExcludeDao();
		int dup=excludeDao.getDupExclude(clubId,user.getUserId());
		if(dup>=1)result=true;
		return result;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function build an exclude for the user
	 * 
	 */
	public int produceExclude(int clubId,String userName,String reason){
		UserDao userDao=new UserDao();
		User user=userDao.getUserByName(userName);
		if(checkDupExclude(clubId,user)==true){
			return -1;
		}
		ClubDao clubDao=new ClubDao();
		ExcludeDao excludeDao=new ExcludeDao();
		Date date=new Date();
		Club club=clubDao.getClubByClubId(clubId);
		Exclude exclude=new Exclude();
		exclude.setClubId(clubId);
		exclude.setUserId(user.getUserId());
		exclude.setExcludeName(club.getClubName()+userName+date.getTime());
		exclude.setForVotes(0);
		exclude.setAgainstVotes(0);
		exclude.setNeutralVotes(0);
		exclude.setExcludeState(1);
		exclude.setExcludeReason(reason);
		exclude.setStartTime(new java.sql.Timestamp(date.getTime()));	
		Date expireTime=new Date();
		expireTime.setTime(expireTime.getTime()+7*24*60*60*1000);
		expireTime.setHours(24);
		expireTime.setMinutes(0);
		expireTime.setSeconds(0);
		exclude.setExpireTime(new java.sql.Timestamp(expireTime.getTime()));
		excludeDao.save(exclude);	
		return 1;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function contert Timestamp to String
	 * 
	 */
	private String convertTime(Timestamp time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date(time.getTime()));
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function convert data in dababase to message shown in web page
	 * 
	 */
	
	private List<ExcludeDto> convertToDto(List<Exclude> excludeListRaw,User user){
		List<ExcludeDto> excludeList=new ArrayList<ExcludeDto>();
		User excludeUser=null;
		Club excludeClub=null;
		UserDao userDao=new UserDao();
		ClubDao clubDao=new ClubDao();
		
		if(excludeListRaw!=null){
			for(Exclude exclude:excludeListRaw){
				ExcludeDto excludeDto=new ExcludeDto();
				excludeDto.setExcludeId(exclude.getExcludeId());
				excludeDto.setExcludeReason(exclude.getExcludeReason());
				excludeDto.setAgainstVotes(exclude.getAgainstVotes());
				excludeDto.setForVotes(exclude.getForVotes());
				excludeDto.setNeutralVotes(exclude.getNeutralVotes());
				int state=exclude.getExcludeState();
				switch(state){
				case 1:excludeDto.setState("进行中");break;
				case 2:excludeDto.setState("成功");break;
				case 3:excludeDto.setState("失败");break;
				case 4:excludeDto.setState("过期");break;
				default:;
				}
				if(state==2||state==3){
					excludeDto.setExpireTime(convertTime(exclude.getEndTime()));
				}
				else {
					excludeDto.setExpireTime(convertTime(exclude.getExpireTime()));
				}
				excludeDto.setStartTime(convertTime(new Timestamp(exclude.getStartTime().getTime())));
				excludeUser=userDao.getUserByUserId(exclude.getUserId());
				excludeClub=clubDao.getClubByClubId(exclude.getClubId());
				excludeDto.setClubName(excludeClub.getClubName());
				excludeDto.setUserName(excludeUser.getUserName());
				excludeList.add(excludeDto);
			}
		}
		return excludeList;
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function for query
	 */
	public List<ExcludeDto> getExcludeByStartTimeAndState(User user,Timestamp startTimestamp,int state){
		List<ExcludeDto> excludeList;
		ExcludeDao excludeDao=new ExcludeDao();
		List<Exclude> excludeListRaw=
				excludeDao.getExcludeByStartTimeAndState(user.getClubId(),startTimestamp,state);
		excludeList=convertToDto(excludeListRaw,user);
		return excludeList;	
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function query
	 */
	public List<ExcludeDto> getExcludeByState(User user,int state){
		List<ExcludeDto> excludeList;
		ExcludeDao excludeDao=new ExcludeDao();
		List<Exclude> excludeListRaw=excludeDao.getExcludeByState(user.getClubId(),state);
		excludeList=convertToDto(excludeListRaw,user);
		return excludeList;	
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function query
	 */
	public List<ExcludeDto> getAllExclude(User user){
		List<ExcludeDto> excludeList;
		ExcludeDao excludeDao=new ExcludeDao();
		List<Exclude> excludeListRaw=excludeDao.getAllExclude(user.getClubId());
		excludeList=convertToDto(excludeListRaw,user);
		return excludeList;	
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function query
	 */
	public List<ExcludeDto> getExcludeByStartTime(User user,Timestamp startTimestamp){
		List<ExcludeDto> excludeList;
		ExcludeDao excludeDao=new ExcludeDao();
		List<Exclude> excludeListRaw=excludeDao.getExcludeByStartTime(user.getClubId(),startTimestamp);
		excludeList=convertToDto(excludeListRaw,user);
		return excludeList;	
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function get exclude for the use to vote
	 */
	public List<ExcludeDto> getExclude(User user){
		List<ExcludeDto> excludeList=new ArrayList<ExcludeDto>();
		ExcludeDao excludeDao=new ExcludeDao();
		List<Exclude> excludeListRaw=excludeDao.getOnGoingExcludeByClubId(user.getClubId());
		User excludeUser=null;
		Club excludeClub=null;
		UserDao userDao=new UserDao();
		ClubDao clubDao=new ClubDao();
		ExcludeVoteRecordDao recordDao=new ExcludeVoteRecordDao();
		boolean dupVote;
		if(excludeListRaw!=null){
			for(Exclude exclude:excludeListRaw){
				dupVote=recordDao.checkDupVote(user.getUserId(),exclude.getExcludeId());
				if(dupVote==true)continue;
				ExcludeDto excludeDto=new ExcludeDto();
				excludeDto.setExcludeId(exclude.getExcludeId());
				excludeDto.setExcludeReason(exclude.getExcludeReason());
				excludeDto.setExpireTime(convertTime(exclude.getExpireTime()));
				excludeDto.setStartTime(convertTime(exclude.getStartTime()));
				excludeUser=userDao.getUserByUserId(exclude.getUserId());
				excludeClub=clubDao.getClubByClubId(exclude.getClubId());
				excludeDto.setClubName(excludeClub.getClubName());
				excludeDto.setUserName(excludeUser.getUserName());
				excludeList.add(excludeDto);
			}
		}
		return excludeList;	
	}
	/*
	 * @author Pete Peng
	 * @time 2014/12/12
	 * function: when a user vote ,add record in dababase. Determine weather the vote can
	 * be ended. If the vote can be ended , end the vote and make changes in database.
	 */
	public int addVote(int userId,int excludeId,int choice){
		ExcludeDao excludeDao=new ExcludeDao();
		Exclude exclude=excludeDao.getExcludeById(excludeId);
		Date now=new Date();
		Session session=null;
		Transaction tx=null;
		//判断当前时间有没有大于投票截止时间，若大于，则不能投票：
		if(exclude.getExpireTime().getTime()<now.getTime()){
			exclude.setExcludeState(4);
			excludeDao.save(exclude);
			return -1;
		}
		//根据用户投的是反对票，赞成票，弃权票，在数据库的相应列上加1：
		if(choice==1)exclude.setAgainstVotes(exclude.getAgainstVotes()+1);
		else if(choice==2)exclude.setForVotes(exclude.getForVotes()+1);
		else exclude.setNeutralVotes(exclude.getNeutralVotes()+1);
		excludeDao.save(exclude);
		//投票成功后，在投票记录表上增加一条记录，以避免重复投票：
		ExcludeVoteRecordDao evDao=new ExcludeVoteRecordDao();
		ExcludeVoteRecord evRecord=new ExcludeVoteRecord();
		evRecord.setExcludeId(excludeId);
		evRecord.setUserId(userId);
		evRecord.setVoteTime(new java.sql.Timestamp(now.getTime()));
		evDao.save(evRecord);
		//若当前投赞成票的人数超过俱乐部总人数的三分之二，则此次投票成功结束并执行驱逐俱乐部成员的动作
		UserDao userDao=new UserDao();
		int totalMember=userDao.countClubMember(exclude.getClubId());
		if(3*exclude.getForVotes()>2*totalMember){
			exclude.setExcludeState(2);
			exclude.setEndTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			excludeDao.save(exclude);
			User user=userDao.getUserByUserId(userId);
			user.setClubId(-1);
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			try {
				userDao.save(user,session);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		//若当前反对票数加弃权数大于或等于俱乐部总人数的三分之一，则此次投票以失败告终
		if(3*(exclude.getAgainstVotes()+exclude.getNeutralVotes())>=totalMember){
			exclude.setExcludeState(3);
			exclude.setEndTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			excludeDao.save(exclude);
		}
		return 1;
	}
}
