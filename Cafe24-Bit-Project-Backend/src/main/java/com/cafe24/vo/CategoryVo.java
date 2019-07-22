package com.cafe24.vo;

// 상품 카테고리 테이블
public class CategoryVo {

	private long categoryNo;
	private String categoryName;
	private long groupno;
	private long orderno;
	private long depth;
	private long parentno;
	
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getGroupno() {
		return groupno;
	}
	public void setGroupno(long groupno) {
		this.groupno = groupno;
	}
	public long getOrderno() {
		return orderno;
	}
	public void setOrderno(long orderno) {
		this.orderno = orderno;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public long getParentno() {
		return parentno;
	}
	public void setParentno(long parentno) {
		this.parentno = parentno;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", groupno=" + groupno
				+ ", orderno=" + orderno + ", depth=" + depth + ", parentno=" + parentno + "]";
	}
	
}
