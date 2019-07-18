package com.cafe24.dto;

import java.util.List;

public class CartOptionUpdateDto {

	private long cartNo;
	private List<Long> productOptionDetailNo;
	
	public long getCartNo() {
		return cartNo;
	}
	public void setCartNo(long cartNo) {
		this.cartNo = cartNo;
	}
	public List<Long> getProductOptionDetailNo() {
		return productOptionDetailNo;
	}
	public void setProductOptionDetailNo(List<Long> productOptionDetailNo) {
		this.productOptionDetailNo = productOptionDetailNo;
	}
	
	
}
