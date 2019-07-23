package com.cafe24.vo;

import org.hibernate.validator.constraints.Length;

// 상품 옵션, 상품 옵션 상세 테이블
public class ProductOptionVo {

	private long optionNo;
	private long productNo;
	
	@Length(max=255, message="상품 옵션의 이름을 255자 내로 입력해 주세요.")
	private String optionName;
	
	private long optionDetailNo;
	
	@Length(max=255, message="상품 옵션의 값을 255자 내로 입력해 주세요.")
	private String optionValue;
	
	public long getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(long optionNo) {
		this.optionNo = optionNo;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
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
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	
	@Override
	public String toString() {
		return "ProductOptionVo [optionNo=" + optionNo + ", productNo=" + productNo + ", optionName=" + optionName
				+ ", optionDetailNo=" + optionDetailNo + ", optionValue=" + optionValue + "]";
	}
	
}
