package com.CMS.dto;

import java.sql.Timestamp;

public class ApplyDto {
	private Integer applyId;
	private String clubName;
	private Timestamp applyTime;
	private Integer applyStatus;
	private String managerPhone;
	private Integer userId;
	private String applyDes;
	private String checkRes;
	private Timestamp checkTime;
	private Integer requesterId;
	private Integer clubId;
	private Integer managerId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userPart;
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
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
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getApplyDes() {
		return applyDes;
	}
	public void setApplyDes(String applyDes) {
		this.applyDes = applyDes;
	}

	public String getCheckRes() {
		return checkRes;
	}
	public void setCheckRes(String checkRes) {
		this.checkRes = checkRes;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
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
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPart() {
		return userPart;
	}
	public void setUserPart(String userPart) {
		this.userPart = userPart;
	}
	

	

	

}
