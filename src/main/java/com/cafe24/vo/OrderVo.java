package com.cafe24.vo;

// 주문 테이블
public class OrderVo {

	private long orderNo;
	private String memberId;
	private long nonmemberMac;
	private String reciever;
	private String recieverAddress;
	private String message;
	private long totalPrice;
	private String paymethod;
	private String orderDate;
	private String payStatus;
	
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public long getNonmemberMac() {
		return nonmemberMac;
	}
	public void setNonmemberMac(long nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getRecieverAddress() {
		return recieverAddress;
	}
	public void setRecieverAddress(String recieverAddress) {
		this.recieverAddress = recieverAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Override
	public String toString() {
		return "OrderVo [orderNo=" + orderNo + ", memberId=" + memberId + ", nonmemberMac=" + nonmemberMac
				+ ", reciever=" + reciever + ", recieverAddress=" + recieverAddress + ", message=" + message
				+ ", totalPrice=" + totalPrice + ", paymethod=" + paymethod + ", orderDate=" + orderDate
				+ ", payStatus=" + payStatus + "]";
	}
	
}
