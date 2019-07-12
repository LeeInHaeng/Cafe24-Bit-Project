package com.cafe24.vo;

import java.util.List;

// 장바구니, 장바구니 상품 옵션 테이블
public class CartVo {

	private long cartNo;
	private String memberId;
	private String nonmemberMac;
	private long productNo;
	private long quantity;
	
	private List<Long> productOptionDetailNo;

	public long getCartNo() {
		return cartNo;
	}

	public void setCartNo(long cartNo) {
		this.cartNo = cartNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}

	public String getNonmemberMac() {
		return nonmemberMac;
	}

	public void setNonmemberMac(String nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public List<Long> getProductOptionDetailNo() {
		return productOptionDetailNo;
	}

	public void setProductOptionDetailNo(List<Long> productOptionDetailNo) {
		this.productOptionDetailNo = productOptionDetailNo;
	}

	@Override
	public String toString() {
		return "CartVo [cartNo=" + cartNo + ", memberId=" + memberId + ", productNo=" + productNo + ", nonmemberMac="
				+ nonmemberMac + ", quantity=" + quantity + ", productOptionDetailNo=" + productOptionDetailNo + "]";
	}
	
}
