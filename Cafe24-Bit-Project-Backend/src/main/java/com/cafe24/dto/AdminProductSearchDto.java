package com.cafe24.dto;

public class AdminProductSearchDto {

	private String productName;
	
	private String categoryName;
	
	private String regDateStart;
	private String regDateEnd;
	
	private boolean isdisplay;
	private boolean issell;
	private boolean isdisplayMain;
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRegDateStart() {
		return regDateStart;
	}

	public void setRegDateStart(String regDateStart) {
		this.regDateStart = regDateStart;
	}

	public String getRegDateEnd() {
		return regDateEnd;
	}

	public void setRegDateEnd(String regDateEnd) {
		this.regDateEnd = regDateEnd;
	}

	public boolean isIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(boolean isdisplay) {
		this.isdisplay = isdisplay;
	}

	public boolean isIssell() {
		return issell;
	}

	public void setIssell(boolean issell) {
		this.issell = issell;
	}

	public boolean isIsdisplayMain() {
		return isdisplayMain;
	}

	public void setIsdisplayMain(boolean isdisplayMain) {
		this.isdisplayMain = isdisplayMain;
	}

	@Override
	public String toString() {
		return "AdminProductSearchDto [productName=" + productName + ", categoryName=" + categoryName
				+ ", regDateStart=" + regDateStart + ", regDateEnd=" + regDateEnd + ", isdisplay=" + isdisplay
				+ ", issell=" + issell + ", isdisplayMain=" + isdisplayMain + "]";
	}
}
