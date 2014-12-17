package com.CMS.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.CMS.entity.ExcludeVoteRecord;

public class ExcludeVoteRecordDao extends BaseDao{
	public void save(ExcludeVoteRecord record){
		Session session=null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.save(record);
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
	public boolean checkDupVote(int userId,int excludeId){
		Session session=null;
		Transaction tx = null;
		String hql="";
		Boolean result=false;
		try {
			session = getSession();
			tx = session.beginTransaction();
			hql="from ExcludeVoteRecord where userId=:userId and excludeId=:excludeId";
			Query query=session.createQuery(hql);
			query.setInteger("userId", userId);
			query.setInteger("excludeId", excludeId);
			int recordNum=query.list().size();
			tx.commit();
			if(recordNum==0)result=false;
			else result=true;
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
