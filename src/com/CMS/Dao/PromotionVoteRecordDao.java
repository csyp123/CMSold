package com.CMS.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.dto.JudgePromotionDto;
import com.CMS.entity.PromotionVoteRecord;

public class PromotionVoteRecordDao extends BaseDao{

	public boolean SavePromotion(PromotionVoteRecord pvr) {	
		Session session=null;
		Transaction tx=null;
		try {
			session=getSession();
			tx=session.beginTransaction();
			session.save(pvr);
			tx.commit();			
		} catch(Exception e){
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return true;
	}

	public List<PromotionVoteRecord> isExist(PromotionVoteRecord pvr) throws Exception {
		
		Session session=null;
		Transaction tx=null;
		String hql="";
		List<PromotionVoteRecord> result=new ArrayList<PromotionVoteRecord>();
		try {
			session=getSession();
			tx=session.beginTransaction();
			hql="from PromotionVoteRecord p where p.promotionId=:promotionId and p.voteuserId=:voteId";
			Query query = session.createQuery(hql);
			query.setInteger("promotionId", pvr.getPromotionId());
			query.setInteger("voteId", pvr.getVoteuserId());
			result=query.list();
		} catch(Exception e){
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new Exception(e);
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}

	public List<JudgePromotionDto> JudgeVoteCount(PromotionVoteRecord pvr,Session session) throws Exception {
		String hql="";
		List result=null;
		List<JudgePromotionDto> judgeresult=new ArrayList<JudgePromotionDto>();
		try {
			hql="select count(*) as countvote ,voteduserId from PromotionVoteRecord where promotionId=:promotionId group by voteduserId order by countvote DESC";
			Query query = session.createQuery(hql);
			query.setInteger("promotionId", pvr.getPromotionId());
			result = query.list();
			for(int i=0;i<result.size();i++){
				Object[] row=(Object[])result.get(i);
				JudgePromotionDto jpDto=new JudgePromotionDto();
				jpDto.setCountPromotion(((java.lang.Long)row[0]).intValue());
				jpDto.setVoteduserId((Integer)row[1]);
				judgeresult.add(jpDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}				
		return judgeresult;
	}

	public boolean delete(Integer promotionId) throws Exception {
		Session session=null;
		Transaction tx=null;
		String hql="";
		try {
			session= getSession();
			tx=session.beginTransaction();
			hql="delete from PromotionVoteRecord pvr where pvr.promotionId=:promotionId";
			Query query = session.createQuery(hql);
			query.setInteger("promotionId", promotionId);
			Integer status=query.executeUpdate();
			tx.commit();
			if (status==1) {
				return true;		
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		
	}
}
