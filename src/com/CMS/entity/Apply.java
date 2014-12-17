package com.CMS.entity;

import java.sql.Timestamp;

public class Apply {
	private Integer applyId;
	private Timestamp applyTime;
	private Integer applyStatus;
	private String applyDes;
	private Integer requesterId;
	private Integer clubId;
	private Timestamp checkTime;
	private String checkRes;
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApplyDes() {
		return applyDes;
	}
	public void setApplyDes(String applyDes) {
		this.applyDes = applyDes;
	}
	public Integer getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(Integer requesterId) {
		this.requesterId = requesterId;
	}
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckRes() {
		return checkRes;
	}
	public void setCheckRes(String checkRes) {
		this.checkRes = checkRes;
	}
	
	
	

}
