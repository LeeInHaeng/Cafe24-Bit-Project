package com.cafe24.vo;

// 상품 옵션, 상품 옵션 상세 테이블
public class ProductOptionVo {

	private long optionNo;
	private String optionName;
	
	private long optionDetailNo;
	private long productNo;
	private String optionValue;
	private long realQuantity;
	private long availableQuantity;
	
	public long getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(long optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public long getOptionDetailNo() {
		return optionDetailNo;
	}
	public void setOptionDetailNo(long optionDetailNo) {
		this.optionDetailNo = optionDetailNo;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public long getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(long realQuantity) {
		this.realQuantity = realQuantity;
	}
	public long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	@Override
	public String toString() {
		return "ProductOptionVo [optionNo=" + optionNo + ", optionName=" + optionName + ", optionDetailNo="
				+ optionDetailNo + ", productNo=" + productNo + ", optionValue=" + optionValue + ", realQuantity="
				+ realQuantity + ", availableQuantity=" + availableQuantity + "]";
	}
	
}
