package com.cafe24.dto;

import java.util.List;

public class AdminCheckedProductsDisplayUpdateDto {

	private List<Long> productNo;
	
	private boolean isdisplay;
	private boolean issell;
	private boolean isdisplayMain;
	
	public List<Long> getProductNo() {
		return productNo;
	}
	public void setProductNo(List<Long> productNo) {
		this.productNo = productNo;
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
		return "AdminCheckedProductsDisplayUpdateDto [productNo=" + productNo + ", isdisplay=" + isdisplay + ", issell="
				+ issell + ", isdisplayMain=" + isdisplayMain + "]";
	}
	
}
