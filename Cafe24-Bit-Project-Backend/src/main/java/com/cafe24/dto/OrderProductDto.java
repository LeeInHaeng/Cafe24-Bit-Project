package com.cafe24.dto;

import java.util.List;

import com.cafe24.vo.ProductOptionVo;

public class OrderProductDto {

	private long productNo;
	private String title;
	private String image;
	private long price;
	private long mileageAdd;
	private String description;
	private long shippingPrice;
	
	private boolean isdisplay;
	private boolean issell;
	
	private long quantity;
	
	private List<ProductOptionVo> productOptionVo;

	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(long shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public boolean isIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(boolean isdisplay) {
		this.isdisplay = isdisplay;
	}

	public boolean isIssell() {
		return issell;
	}

	public void setIssell(boolean issell) {
		this.issell = issell;
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

	@Override
	public String toString() {
		return "OrderProductDto [productNo=" + productNo + ", title=" + title + ", image=" + image + ", price=" + price
				+ ", mileageAdd=" + mileageAdd + ", description=" + description + ", shippingPrice=" + shippingPrice
				+ ", isdisplay=" + isdisplay + ", issell=" + issell + ", quantity=" + quantity + ", productOptionVo="
				+ productOptionVo + "]";
	}
	
}
