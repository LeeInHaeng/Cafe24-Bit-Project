package com.cafe24.vo;

import javax.validation.constraints.NotEmpty;
import com.cafe24.validator.constraints.OrderStatusValid;

// 주문 상품 테이블
public class OrderProductVo {

	private long orderNo;
	
	private long productNo;
	private long quantity;
	private String shippingStatus;
	
	@OrderStatusValid
	private String orderStatus;
	
	@NotEmpty
	private String orderStatusChangeReason;
	
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderStatusChangeReason() {
		return orderStatusChangeReason;
	}
	public void setOrderStatusChangeReason(String orderStatusChangeReason) {
		this.orderStatusChangeReason = orderStatusChangeReason;
	}
	
	@Override
	public String toString() {
		return "OrderProductVo [orderNo=" + orderNo + ", productNo=" + productNo + ", quantity=" + quantity
				+ ", shippingStatus=" + shippingStatus + ", orderStatus=" + orderStatus + ", orderStatusChangeReason="
				+ orderStatusChangeReason + "]";
	}
}
