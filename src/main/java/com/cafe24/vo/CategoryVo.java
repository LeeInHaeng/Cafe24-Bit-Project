package com.cafe24.vo;

// 상품 카테고리 테이블
public class CategoryVo {

	private long categoryNo;
	private String bigClassifyName;
	private String midClassifyName;
	private String smallClassifyName;
	
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getBigClassifyName() {
		return bigClassifyName;
	}
	public void setBigClassifyName(String bigClassifyName) {
		this.bigClassifyName = bigClassifyName;
	}
	public String getMidClassifyName() {
		return midClassifyName;
	}
	public void setMidClassifyName(String midClassifyName) {
		this.midClassifyName = midClassifyName;
	}
	public String getSmallClassifyName() {
		return smallClassifyName;
	}
	public void setSmallClassifyName(String smallClassifyName) {
		this.smallClassifyName = smallClassifyName;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [categoryNo=" + categoryNo + ", bigClassifyName=" + bigClassifyName + ", midClassifyName="
				+ midClassifyName + ", smallClassifyName=" + smallClassifyName + "]";
	}
	
}
