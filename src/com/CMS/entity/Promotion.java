package com.CMS.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Promotion {
	private int promotionId;
	private int clubId;
	private int userId;
	private int promotionState;
	private int promotionVotes;
	private String promotionName;
	private String promotionReason;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp expireTime;
	private int recommenduserId;
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPromotionState() {
		return promotionState;
	}
	public void setPromotionState(int promotionState) {
		this.promotionState = promotionState;
	}
	public int getPromotionVotes() {
		return promotionVotes;
	}
	public void setPromotionVotes(int promotionVotes) {
		this.promotionVotes = promotionVotes;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getPromotionReason() {
		return promotionReason;
	}
	public void setPromotionReason(String promotionReason) {
		this.promotionReason = promotionReason;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}
	public int getRecommenduserId() {
		return recommenduserId;
	}
	public void setRecommenduserId(int recommenduserId) {
		this.recommenduserId = recommenduserId;
	}
	

	
	
}
