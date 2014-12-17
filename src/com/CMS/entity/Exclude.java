package com.CMS.entity;

import java.sql.Timestamp;

public class Exclude {
	private int excludeId;
	private int userId;
	private int clubId;
	private String excludeReason;
	private String excludeName;
	private int excludeState;
	private Timestamp startTime;
	private int forVotes;
	private int againstVotes;
	private int neutralVotes;
	private Timestamp expireTime;
	private Timestamp endTime;
	
	
	public int getForVotes() {
		return forVotes;
	}
	public void setForVotes(int forVotes) {
		this.forVotes = forVotes;
	}
	public int getAgainstVotes() {
		return againstVotes;
	}
	public void setAgainstVotes(int againstVotes) {
		this.againstVotes = againstVotes;
	}
	public int getNeutralVotes() {
		return neutralVotes;
	}
	public void setNeutralVotes(int neutralVotes) {
		this.neutralVotes = neutralVotes;
	}
	public int getExcludeId() {
		return excludeId;
	}
	public void setExcludeId(int excludeId) {
		this.excludeId = excludeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getExcludeReason() {
		return excludeReason;
	}
	public void setExcludeReason(String excludeReason) {
		this.excludeReason = excludeReason;
	}
	public String getExcludeName() {
		return excludeName;
	}
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}
	public int getExcludeState() {
		return excludeState;
	}
	public void setExcludeState(int excludeState) {
		this.excludeState = excludeState;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
}
