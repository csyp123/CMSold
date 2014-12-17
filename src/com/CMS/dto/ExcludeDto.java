package com.CMS.dto;


public class ExcludeDto {
	private int excludeId;
	private String userName;
	private String clubName;
	private String excludeReason;
	private String startTime;
	private String expireTime;
	private String state;
	private int forVotes;
	private int againstVotes;
	private int neutralVotes;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getExcludeReason() {
		return excludeReason;
	}
	public void setExcludeReason(String excludeReason) {
		this.excludeReason = excludeReason;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
}
