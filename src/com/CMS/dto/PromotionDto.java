package com.CMS.dto;

import java.sql.Timestamp;

public class PromotionDto {
	private Integer promotionId;
	private Integer clubId;
	private Integer userId;
	private Integer promotionState;
	private Integer promotionVotes;
	private String promotionName;
	private String promotionReason;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp expireTime;
	private Integer recommenduserId;
	private String clubName;
	private Integer managerId;
	private String clubDescription;
	private String clubLocation;
	private String recommenduserName;
	public Integer getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPromotionState() {
		return promotionState;
	}
	public void setPromotionState(Integer promotionState) {
		this.promotionState = promotionState;
	}
	public Integer getPromotionVotes() {
		return promotionVotes;
	}
	public void setPromotionVotes(Integer promotionVotes) {
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
	public Integer getRecommenduserId() {
		return recommenduserId;
	}
	public void setRecommenduserId(Integer recommenduserId) {
		this.recommenduserId = recommenduserId;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getClubDescription() {
		return clubDescription;
	}
	public void setClubDescription(String clubDescription) {
		this.clubDescription = clubDescription;
	}
	public String getClubLocation() {
		return clubLocation;
	}
	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}
	public String getRecommenduserName() {
		return recommenduserName;
	}
	public void setRecommenduserName(String recommenduserName) {
		this.recommenduserName = recommenduserName;
	}

	
	

}
