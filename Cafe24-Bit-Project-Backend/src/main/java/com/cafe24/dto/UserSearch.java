package com.cafe24.dto;

public class UserSearch {
	
	private String memberId;
	private String name;
	private boolean ismessage;
	private boolean ismail;
	private long ageStart;
	private long ageEnd;
	private String joinDateStart;
	private String joinDateEnd;
	
	private long buyPriceStart;
	private long buyPriceEnd;
	private long buyCountStart;
	private long buyCountEnd;
	private String orderDateStart;
	private String orderDateEnd;
	
	private long productNo;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsmessage() {
		return ismessage;
	}

	public void setIsmessage(boolean ismessage) {
		this.ismessage = ismessage;
	}

	public boolean isIsmail() {
		return ismail;
	}

	public void setIsmail(boolean ismail) {
		this.ismail = ismail;
	}

	public long getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(long ageStart) {
		this.ageStart = ageStart;
	}

	public long getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(long ageEnd) {
		this.ageEnd = ageEnd;
	}

	public String getJoinDateStart() {
		return joinDateStart;
	}

	public void setJoinDateStart(String joinDateStart) {
		this.joinDateStart = joinDateStart;
	}

	public String getJoinDateEnd() {
		return joinDateEnd;
	}

	public void setJoinDateEnd(String joinDateEnd) {
		this.joinDateEnd = joinDateEnd;
	}

	public long getBuyPriceStart() {
		return buyPriceStart;
	}

	public void setBuyPriceStart(long buyPriceStart) {
		this.buyPriceStart = buyPriceStart;
	}

	public long getBuyPriceEnd() {
		return buyPriceEnd;
	}

	public void setBuyPriceEnd(long buyPriceEnd) {
		this.buyPriceEnd = buyPriceEnd;
	}

	public long getBuyCountStart() {
		return buyCountStart;
	}

	public void setBuyCountStart(long buyCountStart) {
		this.buyCountStart = buyCountStart;
	}

	public long getBuyCountEnd() {
		return buyCountEnd;
	}

	public void setBuyCountEnd(long buyCountEnd) {
		this.buyCountEnd = buyCountEnd;
	}

	public String getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(String orderDateStart) {
		this.orderDateStart = orderDateStart;
	}

	public String getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(String orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "UserSearch [memberId=" + memberId + ", name=" + name + ", ismessage=" + ismessage + ", ismail=" + ismail
				+ ", ageStart=" + ageStart + ", ageEnd=" + ageEnd + ", joinDateStart=" + joinDateStart
				+ ", joinDateEnd=" + joinDateEnd + ", buyPriceStart=" + buyPriceStart + ", buyPriceEnd=" + buyPriceEnd
				+ ", buyCountStart=" + buyCountStart + ", buyCountEnd=" + buyCountEnd + ", orderDateStart="
				+ orderDateStart + ", orderDateEnd=" + orderDateEnd + ", productNo=" + productNo + "]";
	}
	
	
}
