package com.cafe24.dto;

import java.util.List;

import com.cafe24.vo.ProductOptionVo;
import com.cafe24.vo.ProductVo;

public class CartDetailDto {

	private long cartNo;
	private String memberId;
	private String nonmemberMac;
	private long productNo;
	private long quantity;
	
	private List<ProductOptionVo> productOptionVo;
	
	private String title;
	private String image;
	private long price;
	private long mileageAdd;
	private long shippingPrice;
	
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
	public String getNonmemberMac() {
		return nonmemberMac;
	}
	public void setNonmemberMac(String nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
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
	public List<ProductOptionVo> getProductOptionVo() {
		return productOptionVo;
	}
	public void setProductOptionVo(List<ProductOptionVo> productOptionVo) {
		this.productOptionVo = productOptionVo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getMileageAdd() {
		return mileageAdd;
	}
	public void setMileageAdd(long mileageAdd) {
		this.mileageAdd = mileageAdd;
	}
	public long getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(long shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	
	
}
