package com.cafe24.dto;

import java.util.List;

public class ProductOptionDto {

	private long productNo;
	private List<Long> productOptionDetailNo;
	private long quantity;
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public List<Long> getProductOptionDetailNo() {
		return productOptionDetailNo;
	}
	public void setProductOptionDetailNo(List<Long> productOptionDetailNo) {
		this.productOptionDetailNo = productOptionDetailNo;
	}
	
	@Override
	public String toString() {
		return "ProductOptionDto [productNo=" + productNo + ", productOptionDetailNo=" + productOptionDetailNo
				+ ", quantity=" + quantity + "]";
	}
	
}
