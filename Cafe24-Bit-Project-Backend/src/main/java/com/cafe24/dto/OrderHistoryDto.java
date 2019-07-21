package com.cafe24.dto;

import java.util.List;

import com.cafe24.vo.OrderProductVo;

public class OrderHistoryDto {

	private String reciever;
	private String recieverAddress;
	private String message;
	private long totalPrice;
	private String paymethod;
	private String orderDate;
	private String payStatus;

	List<OrderProductVo> orderProductVo;
	List<OrderProductDto> orderProductDto;
	
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
	public List<OrderProductVo> getOrderProductVo() {
		return orderProductVo;
	}
	public void setOrderProductVo(List<OrderProductVo> orderProductVo) {
		this.orderProductVo = orderProductVo;
	}
	public List<OrderProductDto> getOrderProductDto() {
		return orderProductDto;
	}
	public void setOrderProductDto(List<OrderProductDto> orderProductDto) {
		this.orderProductDto = orderProductDto;
	}
	@Override
	public String toString() {
		return "OrderHistoryDto [reciever=" + reciever + ", recieverAddress=" + recieverAddress + ", message=" + message
				+ ", totalPrice=" + totalPrice + ", paymethod=" + paymethod + ", orderDate=" + orderDate
				+ ", payStatus=" + payStatus + ", orderProductVo=" + orderProductVo + ", orderProductDto="
				+ orderProductDto + "]";
	}
	
}
