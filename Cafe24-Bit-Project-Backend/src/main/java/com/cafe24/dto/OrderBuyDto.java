package com.cafe24.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class OrderBuyDto {

	private long orderNo;
	private String memberId;

	private String nonmemberMac;
	private String nonmemberPhone;
	private String nonmemberPass;
	private String nonmemberName;
	
	@Length(max=255, message="환불 계좌 은행 이름을 255자 내로 입력해 주세요.")
	private String nonmemberRefundName;
	@Length(max=255, message="환불 계좌 번호를 255자 내로 입력해 주세요.")
	private String nonmemberRefundNumber;
	
	@NotEmpty(message="받는사람은 필수 입력 항목 입니다.")
	@Length(max=255, message="받는사람을 255자 내로 입력해 주세요.")
	private String reciever;
	
	@NotEmpty(message="받는사람의 주소는 필수 입력 항목 입니다.")
	@Length(max=255, message="받는사람의 주소를 255자 내로 입력해 주세요.")
	private String recieverAddress;
	
	@Length(max=255, message="배송 메세지를 255자 내로 입력해 주세요.")
	private String message;
	private long totalPrice;
	
	@NotEmpty
	private String paymethod;
	private String orderDate;
	
	@NotEmpty
	private String payStatus;
	
	List<ProductOptionDto> productOptionDto;

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

	public String getNonmemberMac() {
		return nonmemberMac;
	}

	public void setNonmemberMac(String nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
	}

	public String getNonmemberPhone() {
		return nonmemberPhone;
	}

	public void setNonmemberPhone(String nonmemberPhone) {
		this.nonmemberPhone = nonmemberPhone;
	}

	public String getNonmemberPass() {
		return nonmemberPass;
	}

	public void setNonmemberPass(String nonmemberPass) {
		this.nonmemberPass = nonmemberPass;
	}

	public String getNonmemberName() {
		return nonmemberName;
	}

	public void setNonmemberName(String nonmemberName) {
		this.nonmemberName = nonmemberName;
	}

	public String getNonmemberRefundName() {
		return nonmemberRefundName;
	}

	public void setNonmemberRefundName(String nonmemberRefundName) {
		this.nonmemberRefundName = nonmemberRefundName;
	}

	public String getNonmemberRefundNumber() {
		return nonmemberRefundNumber;
	}

	public void setNonmemberRefundNumber(String nonmemberRefundNumber) {
		this.nonmemberRefundNumber = nonmemberRefundNumber;
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

	public List<ProductOptionDto> getProductOptionDto() {
		return productOptionDto;
	}

	public void setProductOptionDto(List<ProductOptionDto> productOptionDto) {
		this.productOptionDto = productOptionDto;
	}

	@Override
	public String toString() {
		return "OrderBuyDto [orderNo=" + orderNo + ", memberId=" + memberId + ", nonmemberMac=" + nonmemberMac
				+ ", nonmemberPhone=" + nonmemberPhone + ", nonmemberPass=" + nonmemberPass + ", nonmemberName="
				+ nonmemberName + ", nonmemberRefundName=" + nonmemberRefundName + ", nonmemberRefundNumber="
				+ nonmemberRefundNumber + ", reciever=" + reciever + ", recieverAddress=" + recieverAddress
				+ ", message=" + message + ", totalPrice=" + totalPrice + ", paymethod=" + paymethod + ", orderDate="
				+ orderDate + ", payStatus=" + payStatus + ", productOptionDto=" + productOptionDto + "]";
	}

}
