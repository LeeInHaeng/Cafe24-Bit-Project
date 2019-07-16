package com.cafe24.vo;

public class ProductQuantityVo {

	private long quantityNo;
	private long productNo;
	private String optionCode;
	private long realQuantity;
	private long availableQuantity;
	
	public long getQuantityNo() {
		return quantityNo;
	}
	public void setQuantityNo(long quantityNo) {
		this.quantityNo = quantityNo;
	}
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
	public long getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(long realQuantity) {
		this.realQuantity = realQuantity;
	}
	public long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	@Override
	public String toString() {
		return "ProductQuantityVo [quantityNo=" + quantityNo + ", productNo=" + productNo + ", optionCode=" + optionCode
				+ ", realQuantity=" + realQuantity + ", availableQuantity=" + availableQuantity + "]";
	}
}
