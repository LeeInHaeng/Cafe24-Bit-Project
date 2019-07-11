package com.cafe24.vo;

// 상품, 상품 관리 테이블
public class ProductVo {

	private long productNo;
	private long productCategoryNo;
	private long productManageNo;
	private String title;
	private String image;
	private long price;
	private long mileageAdd;
	private String description;
	private String descriptionDetail;
	private long shippingPrice;
	
	private boolean isdisplay;
	private boolean issell;
	private String regDate;
	private String endDate;

	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public long getProductCategoryNo() {
		return productCategoryNo;
	}
	public void setProductCategoryNo(long productCategoryNo) {
		this.productCategoryNo = productCategoryNo;
	}
	public long getProductManageNo() {
		return productManageNo;
	}
	public void setProductManageNo(long productManageNo) {
		this.productManageNo = productManageNo;
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
	public String getDescriptionDetail() {
		return descriptionDetail;
	}
	public void setDescriptionDetail(String descriptionDetail) {
		this.descriptionDetail = descriptionDetail;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "ProductVo [productNo=" + productNo + ", productCategoryNo=" + productCategoryNo + ", productManageNo="
				+ productManageNo + ", title=" + title + ", image=" + image + ", price=" + price + ", mileageAdd="
				+ mileageAdd + ", description=" + description + ", descriptionDetail=" + descriptionDetail
				+ ", shippingPrice=" + shippingPrice + ", isdisplay=" + isdisplay + ", issell=" + issell + ", regDate="
				+ regDate + ", endDate=" + endDate + "]";
	}
}
