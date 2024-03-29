package com.cafe24.vo;

import org.hibernate.validator.constraints.Length;

// 상품 이미지 테이블
public class ProductImageVo {

	private long imageNo;
	private long productNo;
	private String imageDetail;

	@Length(max=200, message="이미지 제목을 200자 이내로 입력해 주세요.")
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
	
	public String getImageDetail() {
		return imageDetail;
	}
	public void setImageDetail(String imageDetail) {
		this.imageDetail = imageDetail;
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
		return "ProductImageVo [imageNo=" + imageNo + ", productNo=" + productNo + ", imageDetail=" + imageDetail
				+ ", imageTitle=" + imageTitle + ", imageDescription=" + imageDescription + "]";
	}
	
}
