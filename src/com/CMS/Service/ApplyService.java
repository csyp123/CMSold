package com.CMS.Service;

import java.sql.Timestamp;
import java.util.List;

import com.CMS.Dao.ApplyDao;
import com.CMS.dto.ApplyDto;
import com.CMS.entity.Apply;
/**
 * 
 * @author joeyy
 *2014/11/26
 */
/* @Author Joeyy
 * 2014/11/26
 * function getApplyByUserId for myapply.jsp
 */
public class ApplyService {
	ApplyDao applydao=new ApplyDao();
	
	public List<ApplyDto> getApplyByUserId(Integer userId,Integer pageIndex, Integer applyStatus){
		return applydao.getApplyByUserId(userId,pageIndex,applyStatus);	
	}
/*
 * @author WalkerCheng
 * function saveApply
 * 2014/11/28
 */
	public boolean saveApply(Apply apply){
		return applydao.saveApply(apply);
	}
/*
 * @Author Joeyy
 * function getApplyDetails
 * 2014/12/01
 */
	public List<ApplyDto> getApplyDetails(Integer applyId) {
		return applydao.getApplyDetails(applyId);
	}
/*
 * @Author Joeyy
 * function CancelApply
 * 2014/12/01
 * @return 
 * @param 
 */
	public boolean CancelApply(Integer applyId) {
		return applydao.CancelApply(applyId);
		
	}
/*
 * @Author Joeyy
 * function getApplyByManagerId for checkapply
 * 2014/12/01
 */
	public List<ApplyDto> getApplyByManagerId(Integer managerId, Integer pageIndex, Integer applyStatus) {
		return applydao.getApplyByManagerId(managerId,pageIndex,applyStatus);
		
	}
/*
 * @Author Joeyy
 * function PassApply
 * 2014/12/03
 */
	public boolean PassApply(Integer applyId,Timestamp checkTime,Integer userId,Integer clubId) {
		if (applydao.PassApplyInserUser(userId,clubId)&&applydao.PassApply(applyId,checkTime)) {
			return true;	
		}else{
			return false;
		}
	}
/*
 * @Author Joeyy
 * function rejectApply
 * 2014/12/03
 */
	public boolean RejectApply(Integer applyId, String checkRes,
			Timestamp checkTime) {
		return applydao.RejectApply(applyId,checkRes,checkTime);
	}
	
	/*
	 * @author WalkerCheng
	 * function search whether have applied
	 * 2014/11/28
	 */

	public boolean searchWhetherApply(Integer userId,Integer applyStatus){
		return applydao.searchWhetherApply(userId,applyStatus);
	}


	

}
