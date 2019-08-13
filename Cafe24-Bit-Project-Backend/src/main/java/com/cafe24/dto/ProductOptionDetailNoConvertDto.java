package com.cafe24.dto;

public class ProductOptionDetailNoConvertDto {

	private long productNo;
	private String optionCode;
	
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	
	@Override
	public String toString() {
		return "ProductOptionDetailNoConvertDto [productNo=" + productNo + ", optionCode=" + optionCode + "]";
	}

}
