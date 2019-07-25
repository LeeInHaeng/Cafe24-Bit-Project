package com.cafe24.dto;

import java.util.List;

// 수정 가능 사항 : 마일리지, 유저의 상태
public class AdminCheckedUserUpdateDto {

	private long mileage;
	private String status;
	
	private List<String> userid;

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getUserid() {
		return userid;
	}

	public void setUserid(List<String> userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "AdminCheckedUserUpdateDto [mileage=" + mileage + ", status=" + status + ", userid=" + userid + "]";
	}
	
}
