package com.cafe24.vo;

// 상품 이미지 테이블
public class ProductImageVo {

	private long imageNo;
	private long productNo;
	private String image;
	private String imageTitle;
	private String imageDescription;
	
	public long getImageNo() {
		return imageNo;
	}
	public void setImageNo(long imageNo) {
		this.imageNo = imageNo;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	
	@Override
	public String toString() {
		return "ProductImageVo [imageNo=" + imageNo + ", productNo=" + productNo + ", image=" + image + ", imageTitle="
				+ imageTitle + ", imageDescription=" + imageDescription + "]";
	}
	
}
