package com.CMS.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.Dao.ClubDao;
import com.CMS.Dao.PromotionDao;
import com.CMS.Dao.PromotionVoteRecordDao;
import com.CMS.Dao.UserDao;
import com.CMS.Utils.DateUtils;
import com.CMS.Utils.HibernateUtil;
import com.CMS.dto.JudgePromotionDto;
import com.CMS.dto.PromotionDto;
import com.CMS.entity.Club;
import com.CMS.entity.Promotion;
import com.CMS.entity.PromotionVoteRecord;
import com.CMS.entity.User;
/**
 * 
 * @author joeyy
 *
 */

public class PromotionService {
	PromotionDao promotiondao = new PromotionDao();
	PromotionVoteRecordDao promotionvotedao = new PromotionVoteRecordDao();
	ClubDao clubdao = new ClubDao();
	UserDao userdao = new UserDao();
	Session session = null;
	Transaction tx =null;
	/*
	 * function producepromotion
	 * 2014/12/11
	 * @return true if save is succeed,else return false
	 * @param Entity promotion
	 */
	public boolean producePromotion(Promotion promotion){
		session=HibernateUtil.getSession();
		tx=session.beginTransaction();
		if (promotiondao.CheckExist(promotion,session)!=null) {
			return false;	
		}else{	
		return promotiondao.savepromotion(promotion);
		}
	}
	/*
	 * function GetOnGoingPromotionById and update promotion
	 * 2014/12/11
	 * @return promotion infomation 
	 * @param clubId of loginuser
	 */
	public List<PromotionDto> GetOnGoingPromotionByClubId(Integer clubId){
		List<PromotionDto> resultlist= promotiondao.GetOnGoingPromotionByClubId(clubId);
			//取出返回的list中的结束时间和过期时间
			for (int i = 0; i < resultlist.size(); i++) {
				Timestamp nowtime=DateUtils.getSysNow();
				Timestamp endtime = resultlist.get(i).getEndTime();
				Timestamp expiretime =resultlist.get(i).getExpireTime();
				Integer promotionId=resultlist.get(i).getPromotionId();
				//如果存在endtime或者选举已过期则从list中移除这一条并将状态设置为对应的状态
				if (endtime!=null||expiretime.getTime()<=nowtime.getTime()) {
					if (endtime!=null) {
						session=HibernateUtil.getSession();
						tx=session.beginTransaction();
						try {
							Integer promotionState=2;
							promotiondao.UpdatePromotion(promotionId,promotionState,session);
						} catch (Exception e) {
							e.printStackTrace();
							if (tx!=null) {
								tx.rollback();
							}
						}
						tx.commit();
					}if(expiretime.getTime()<=nowtime.getTime()){
						session=HibernateUtil.getSession();
						tx=session.beginTransaction();
						try {
							Integer promotionState=4;
							promotiondao.UpdatePromotion(promotionId,promotionState,session);
						} catch (Exception e) {
							e.printStackTrace();
							if (tx!=null) {
								tx.rollback();
							}
						}
						tx.commit();
					}
					resultlist.remove(i);
				}
			}
		
		return resultlist;
	}
	/*
	 * function GetPromotionUserByPromotionId 
	 * 2014/12/09
	 * @return userlist except club's manager
	 * @param promotionId 
	 */
	public List<User> GetPromotionUserByPromotionId(Integer promotionId) {
		List<User> userlist=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			userlist=promotiondao.GetPromotionUserByPromotionId(promotionId,session);
			tx.commit();
			
		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userlist;
		
	}
	/*function SavePromotion 
	 * 2014/12/09
	 * @return true if save is succeed,else return false
	 * @param Entity PromotionVoteRecord used to record voteuserId,voteduserId and promotionId
	 * 
	 */
	public boolean SavePromotionRecord(PromotionVoteRecord pvr) {
		return promotionvotedao.SavePromotion(pvr);
		
	}
	/*
	 * function judge if promotion is exist
	 * 2014/12/08
	 * @return true if it is exist,else return false
	 * @param Entity PromotionVoteRecord
	 */
	public boolean isExist(PromotionVoteRecord pvr) throws Exception {
		List<PromotionVoteRecord> result=new ArrayList<PromotionVoteRecord>();
	    result  = promotionvotedao.isExist(pvr);
	    if (result.isEmpty()) {
	    	return false;			
		}else{
			return true;
		}
	}
	/*
	 * function judge promotion's result and update information by this result
	 * 2014/12/12
	 * @return result is  processed or keep going
	 * @param Entity PromotionVoteRecord
	 * 
	 */
	public String JudgePromotion(PromotionVoteRecord pvr) throws Exception {
		//每次投票完成后对投票结果进行判断
		/*
		 * 如果得票数第一的人的票数-得票第二的人的票数>没有投票的人那么前者则当选负责人
		 * 如果没有满足上诉条件则继续本次投票
		 */
		Integer countclubmember;
		//对（得票数第一的人的票数）和（对应的人）两项数据进行封装
		JudgePromotionDto judge1=new JudgePromotionDto();
		//对（得票数第二的人的票数）和（对应的人）两项数据进行封装
		JudgePromotionDto judge2=new JudgePromotionDto();
		try {
			session=HibernateUtil.getSession();
			//取出该次选举对应的俱乐部的总人数
			countclubmember=clubdao.CountClubMemberByPromotionId(pvr.getPromotionId(),session);
			//对此次数据操作添加事务
			tx=session.beginTransaction();
			//取出得票数和对应的得票人用List集合存储
			List<JudgePromotionDto> judgeresult= promotionvotedao.JudgeVoteCount(pvr,session);	
			//如果有两个人以上都有票数
			if (judgeresult.size()>=2) {
				judge1=judgeresult.get(0);
				judge2=judgeresult.get(1);
				//如果得票数第一的人的票数-得票第二的人的票数>没有投票的人那么前者则当选负责人
				if (judge1.getCountPromotion()-judge2.getCountPromotion()>(countclubmember-(judge1.getCountPromotion()+judge2.getCountPromotion()))) {
					try {
					//将得票第一的人的userType设为0
					userdao.UpUserType(judge1.getVoteduserId());
					Integer oldmanagerId = userdao.GetManagerIdByPromotionId(pvr.getPromotionId());
					//将原俱乐部负责人的userType设为1
					userdao.downUserType(oldmanagerId);	
					//将promotion的endtime设为当前操作时间
					promotiondao.EndPromotion(pvr.getPromotionId(),DateUtils.getSysNow());
					//从promotionvoterecord中删除对应promotionId的所有数据
					promotionvotedao.delete(pvr.getPromotionId());
					Integer clubId= clubdao.GetClubIdByPromotionId(pvr.getPromotionId());
					Club club= new Club();
					club.setClubId(clubId);
					club.setManagerId(judge1.getVoteduserId());
					//将clubinfo表中的俱乐部负责人修改成新负责人
					clubdao.UpdateClubInfoChangeManager(club);	
					} catch (Exception e) {
						if (tx!=null) {
							tx.rollback();	
						}
						e.printStackTrace();
					}
					tx.commit();
					return "投票结果已处理";
				}// 如果没有满足上诉条件则继续本次投票
				else{
					return "选举继续";
				}
			}//如果只有一个人得票
			else if(judgeresult.size()==1){
				judge1=judgeresult.get(0);
				judge2=null;
				//如果得票数大于总人数的50%则直接当选
				if (judge1.getCountPromotion()>countclubmember*0.5) {
					try {
						//将得票第一的人的userType设为0
						userdao.UpUserType(judge1.getVoteduserId());
						Integer oldmanagerId = userdao.GetManagerIdByPromotionId(pvr.getPromotionId());
						//将原俱乐部负责人的userType设为1
						userdao.downUserType(oldmanagerId);	
						//将promotion的endtime设为当前操作时间
						promotiondao.EndPromotion(pvr.getPromotionId(),DateUtils.getSysNow());
						//从promotionvoterecord中删除对应promotionId的所有数据
						promotionvotedao.delete(pvr.getPromotionId());
						Integer clubId= clubdao.GetClubIdByPromotionId(pvr.getPromotionId());
						Club club= new Club();
						club.setClubId(clubId);
						club.setManagerId(judge1.getVoteduserId());
						//将clubinfo表中的俱乐部负责人修改成新负责人
						clubdao.UpdateClubInfoChangeManager(club);
					} catch (Exception e) {
						if (tx!=null) {
							tx.rollback();
							e.printStackTrace();
						}
					}
					tx.commit();
					return "投票结果已处理";
				}// 如果没有满足上诉条件则继续本次投票
				else{
					return "选举继续";
				}
			}else{
				return "选举继续";
			}
			

		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			throw new Exception(e);
		}
		
	}
		

}
