package com.CMS.entity;

import java.sql.Timestamp;

public class ExcludeVoteRecord {
	int excludeId;
	int userId;
	Timestamp voteTime;
	
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
	public Timestamp getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(Timestamp voteTime) {
		this.voteTime = voteTime;
	}
	
}
