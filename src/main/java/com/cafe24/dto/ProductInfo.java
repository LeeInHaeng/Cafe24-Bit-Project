package com.cafe24.dto;

public class ProductInfo {

	private Long productNo;
	private Long quantity;
	private Long productOptionDetailNo;
	
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getProductOptionDetailNo() {
		return productOptionDetailNo;
	}
	public void setProductOptionDetailNo(Long productOptionDetailNo) {
		this.productOptionDetailNo = productOptionDetailNo;
	}
	
	@Override
	public String toString() {
		return "ProductInfo [productNo=" + productNo + ", quantity=" + quantity + ", productOptionDetailNo="
				+ productOptionDetailNo + "]";
	}
	
}
