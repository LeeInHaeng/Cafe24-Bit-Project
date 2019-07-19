package com.cafe24.dto;

import java.util.List;

public class OrderPageDto {
	
	private String memberId;
	private String nonmemberMac;
	
	List<ProductOptionDto> productOptionDto;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getNonmemberMac() {
		return nonmemberMac;
	}

	public void setNonmemberMac(String nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
	}

	public List<ProductOptionDto> getProductOptionDto() {
		return productOptionDto;
	}

	public void setProductOptionDto(List<ProductOptionDto> productOptionDto) {
		this.productOptionDto = productOptionDto;
	}
	
}
